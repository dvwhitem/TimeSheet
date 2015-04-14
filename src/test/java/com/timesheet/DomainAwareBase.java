package com.timesheet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;

import java.sql.Connection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-beans.xml"})
public class DomainAwareBase {

    @Autowired
    DataSource dataSource;

    @Before
    public void deleteAllDomainEntities() throws Exception {
        ClassPathResource resource = new ClassPathResource("sql/cleanup.sql");
        Connection connection = DataSourceUtils.getConnection(dataSource);
        ScriptUtils.executeSqlScript(connection, new EncodedResource(resource, "UTF-8"));
    }

    @Test
    public void executeScript() {}
}
