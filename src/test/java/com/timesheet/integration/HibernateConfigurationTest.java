package com.timesheet.integration;

import  static org.junit.Assert.*;

import com.timesheet.config.PersistenceConfig;
import com.timesheet.config.PropertiesConfig;
import com.timesheet.config.TransactionConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

/**
 * Created by vitaliy on 10.04.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfig.class, TransactionConfig.class, PersistenceConfig.class})
public class HibernateConfigurationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testHibernateConfiguration() {
        assertNotNull(dataSource);
    }
}
