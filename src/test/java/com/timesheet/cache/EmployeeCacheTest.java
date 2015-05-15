package com.timesheet.cache;

import com.timesheet.DomainAwareBase;
import com.timesheet.config.CacheConfig;
import com.timesheet.config.PersistenceConfig;
import com.timesheet.config.PropertiesConfig;
import com.timesheet.config.TransactionConfig;
import com.timesheet.domain.Employee;
import com.timesheet.service.EmployeeService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by vitaliy on 14.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfig.class, TransactionConfig.class,
        PersistenceConfig.class, CacheConfig.class})
public class EmployeeCacheTest extends DomainAwareBase {

    private String cacheName = "employee";

    @Autowired
    private EmployeeService employeeService;


    @Test
    public void testList() {
        assertEquals(0, employeeService.findAll().size());

        List<Employee> employees = Arrays.asList(
                new Employee("John", "engineer"),
                new Employee("Garry", "manager"),
                new Employee("Robert", "tester")
        );

        for(Employee employee: employees) {
            employeeService.save(employee);
        }

        assertTrue(employeeService.findAll().size() == 3);
    }

    @Test
    public void testEhCacheDirect() throws InterruptedException {
        CacheManager manager = CacheManager.getInstance();
        Cache cache = manager.getCache(cacheName);
        checkCache(cache);
    }

    private void checkCache(Cache cache) throws InterruptedException {
        assertNull("Cache should initially be empty", cache.get(cacheName));
        cache.put(new Element(cacheName, employeeService.save(new Employee("William", "producer"))));
        assertNotNull("Should not have timed out", cache.get(cacheName));
        Element element = cache.get(cacheName);
        System.out.println(element.getObjectValue());
        System.out.println(cache.getSize());
        assertNotNull("Cache initialized ", cache.getCacheManager());
        assertEquals("Check status", Status.STATUS_ALIVE, cache.getStatus());
        cache.getCacheManager().shutdown();
        assertEquals("Shutdown", Status.STATUS_SHUTDOWN, cache.getStatus());
    }
}
