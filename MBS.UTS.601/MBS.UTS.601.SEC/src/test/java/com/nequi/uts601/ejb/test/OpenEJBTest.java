//package com.nequi.uts601.ejb.test;
//
//import javax.ejb.EJB;
//import javax.ejb.embeddable.EJBContainer;
//
//import org.apache.openejb.core.ivm.naming.NamingException;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import com.nequi.uts601.ejb.service.co.seiya.checkavailability.COCheckAvailabilityServiceSeiya;
//
//import junit.framework.TestCase;
//
//
//public class OpenEJBTest  extends TestCase {
//
//    private static EJBContainer container;
//    @EJB
//    COCheckAvailabilityServiceSeiya coCheckAvailabilityServiceSeiya;
//
//    @BeforeClass
//    public static void start() {
//        container = EJBContainer.createEJBContainer();
//    }
//
//    @Before
//    public void inject() throws NamingException {
//        try {
//            container.getContext().bind("inject", this);
//        } catch (javax.naming.NamingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void test() {
//        System.out.println("asd");
//        Assert.assertNotNull(
//                coCheckAvailabilityServiceSeiya.executeOperation(""));
//    }
//
//    @AfterClass
//    public static void stop() {
//        container.close();
//    }
//
//}
