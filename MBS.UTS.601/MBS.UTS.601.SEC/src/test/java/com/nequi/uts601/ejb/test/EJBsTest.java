package com.nequi.uts601.ejb.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;

import com.nequi.uts601.ejb.service.co.seiya.checkavailability.COCheckAvailabilityServiceSeiya;

public class EJBsTest {
    
    static Context context;
    
    /**
     * Inicialización del contenedor de EJBs.
     * @throws NamingException 
     */
    @org.junit.BeforeClass
    public static void setUpBeforeAllTest() throws NamingException  {
            Properties props = new Properties();
    props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
            "org.apache.openejb.client.LocalInitialContextFactory");
    
    context = new InitialContext(props);

    }
    
    /**
     * Parada del contenedor de EJBs.
     * @throws NamingException
     */
    @org.junit.AfterClass
    public static void tearDownAfterAllTest() throws NamingException {
    context.close();
    }

    /**
     *  Invocación a través del interfaz local
     * @throws NamingException 
     */
    @org.junit.Test
    public void dummyGreeterBean_sayHi_loacal() throws NamingException  {
        COCheckAvailabilityServiceSeiya greeter = (COCheckAvailabilityServiceSeiya) context.lookup("CallServiceUtil");
            String greeting = greeter.executeOperation("");
            Assert.assertEquals("a", greeting);
    }
    
    /**
     * Invocación a través del interfaz remoto
     * @throws NamingException 
     */
    // @org.junit.Test
    // public void dummyGreeterBean_sayHi_remote() throws NamingException {
    // GreeterRemote greeter = (GreeterRemote)
    // context.lookup("DummyGreeterBeanRemote");
    // String greeting = greeter.sayHi();
    // Assert.assertEquals(DummyGreeterBean.DEFAULT_GREETING, greeting);
    // }      

}
