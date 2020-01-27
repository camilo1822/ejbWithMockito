package com.nequi.uts601.ejb.test;

import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nequi.uts601.ejb.service.co.seiya.checkavailability.COCheckAvailabilityServiceSeiya;

public class ATest {

    private static EJBContainer container;

    @Inject
    private COCheckAvailabilityServiceSeiya aBean;

    @PersistenceContext
    private EntityManager em;

    @Resource
    private DataSource ds;

    @BeforeClass
    public static void start() throws NamingException {
        container = EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void shutdown() {
        if (container != null) {
            container.close();
        }
    }

    @Before
    public void inject() throws NamingException {
        container.getContext().bind("inject", this);
    }

    @After
    public void reset() throws NamingException {
        container.getContext().unbind("inject");
    }

    @Test
    public void aTest() {
        System.out.println("asd");
    }
}
