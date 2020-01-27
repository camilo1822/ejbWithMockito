/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nequi.uts601.ejb.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nequi.uts601.ejb.service.ServiceBean;

/**
 *
 * @author nb
 */
public class MyBeanTest {

    private static EJBContainer c;

    public MyBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        c = EJBContainer.createEJBContainer(properties);
        System.out.println("Opening the container");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        c.close();
        System.out.println("Closing the container");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addNumbers method, of class MyBean.
     */
    @Test
    public void testAddNumbers() throws Exception {
        System.out.println("addNumbers");
        int numberA = 1;
        int numberB = 2;

        // Create the instance using the container context to look up the bean
        // in the directory that contains the built classes
        ServiceBean instance = (ServiceBean) c.getContext().lookup(
                "java:global/classes/COCheckAvailabilityServiceSeiya!com.nequi.uts601.ejb.service.co.seiya.checkavailability.COCheckAvailabilityServiceSeiya");
        String reposne = instance.executeOperation("add");
        System.out.println("xoxoxox " + reposne);

    }
}