//package com.nequi.uts601.ejb.test;
//
//import static org.junit.Assert.assertNotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.openejb.core.ivm.naming.NamingException;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//import com.nequi.cmm.consumer.exception.CommonUtilException;
//import com.nequi.cmm.consumer.util.UtilJSON;
//import com.nequi.mdw.common.jpa.exception.JPAException;
//import com.nequi.mdw.common.jpa.model.entities.Parametro;
//import com.nequi.uts601.ejb.exception.MBSException;
//import com.nequi.uts601.ejb.service.co.seiya.checkavailability.CheckAvailabilityToMock;
//import com.nequi.uts601.messaging.services.seiya.CheckAvailabilityRQType;
//import com.nequi.uts601.messaging.services.seiya.DestinationType;
//import com.nequi.uts601.messaging.services.seiya.MessageRQ;
//import com.nequi.uts601.messaging.services.seiya.RequestBodyType;
//import com.nequi.uts601.messaging.services.seiya.RequestHeaderType;
//import com.nequi.uts601.messaging.services.seiya.RequestMessageType;
//
//public class HelloWorldTest {
//
//    // private static final String PERSISTENCE_UNIT = "entityTest";
//    //
//    // private static EntityManagerFactory emf;
//    //
//    // @BeforeClass
//    // public static void setUpClass() {
//    // emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
//    //
//    // }
//    //
//    // private EntityManager em;
//    //
//    // @Before
//    // public void setUp() throws Exception {
//    // em = emf.createEntityManager();
//    // }
//
//    @Test
//    public void inyectTest() throws NamingException {
//
//        try {
//
//            MessageRQ messageRQ = new MessageRQ();
//            RequestMessageType requestMessageType = new RequestMessageType();
//            RequestHeaderType header = new RequestHeaderType();
//            header.setMessageID("234454");
//            header.setRequestDate("2020-01-25");
//            DestinationType destinationType = new DestinationType();
//            destinationType.setServiceName("FinancialServices");
//            destinationType.setServiceOperation("checkAvailability");
//            destinationType.setServiceRegion("C001");
//            destinationType.setServiceVersion("1.0.0");
//            header.setDestination(destinationType);
//            requestMessageType.setHeader(header);
//            RequestBodyType requestBodyType = new RequestBodyType();
//            CheckAvailabilityRQType checkAvailabilityRQType = new CheckAvailabilityRQType();
//            checkAvailabilityRQType.setOperation("essaAvailability");
//            requestBodyType.setCheckAvailabilityRQ(checkAvailabilityRQType);
//            requestMessageType.setBody(requestBodyType);
//            messageRQ.setRequestMessage(requestMessageType);
//            String rq = UtilJSON.parseObjectToString(messageRQ);
//
//            // llamamos operacion
//
//            CheckAvailabilityToMock helloWorld = Mockito.spy(new CheckAvailabilityToMock());
//            RequestHeaderType requestHeader = new RequestHeaderType();
//            List<Parametro> parameterList = new ArrayList<>();
//            Parametro parametro = new Parametro();
//            parametro.setNombre("Prueba");
//            parametro.setValor("prueba");
//            parameterList.add(parametro);
//            Mockito.doReturn(parameterList).when(helloWorld)
//                    .getParameterList(requestHeader, null);
//
//            String response = helloWorld.executeOperation(rq, null, null);
//            System.out.println("xoxoxoxooxoxox " + response);
//            assertNotNull(response);
//
//        } catch (MBSException e) {
//            System.out.println("Error: " + e);
//        } catch (CommonUtilException e) {
//            System.out.println("Error: " + e);
//        } catch (JPAException e) {
//            System.out.println("Error: " + e);
//        }
//
//    }
//
//    // @Test
//    // public void ejbContainerTest() throws NamingException {
//    //
//    // try {
//    // Map<String, Object> properties = new HashMap<String, Object>();
//    // properties.put(EJBContainer.MODULES, new File("target/classes"));
//    // EJBContainer ejbC = EJBContainer.createEJBContainer(properties);
//    //
//    // Context ctx = ejbC.getContext();
//    // HelloWorld helloWorld = (HelloWorld) ctx.lookup(
//    // "java:global/classes/HelloWorld!com.nequi.uts601.ejb.service.co.seiya.checkavailability.HelloWorld");
//    //
//    // MessageRQ messageRQ = new MessageRQ();
//    // RequestMessageType requestMessageType = new RequestMessageType();
//    // RequestHeaderType header = new RequestHeaderType();
//    // header.setMessageID("234454");
//    // header.setRequestDate("2020-01-25");
//    // DestinationType destinationType = new DestinationType();
//    // destinationType.setServiceName("FinancialServices");
//    // destinationType.setServiceOperation("checkAvailability");
//    // destinationType.setServiceRegion("C001");
//    // destinationType.setServiceVersion("1.0.0");
//    // header.setDestination(destinationType);
//    // requestMessageType.setHeader(header);
//    // RequestBodyType requestBodyType = new RequestBodyType();
//    // CheckAvailabilityRQType checkAvailabilityRQType = new
//    // CheckAvailabilityRQType();
//    // checkAvailabilityRQType.setOperation("essaAvailability");
//    // requestBodyType.setCheckAvailabilityRQ(checkAvailabilityRQType);
//    // requestMessageType.setBody(requestBodyType);
//    // messageRQ.setRequestMessage(requestMessageType);
//    // String rq = UtilJSON.parseObjectToString(messageRQ);
//    //
//    // // llamamos operacion
//    // String response = helloWorld.executeOperation(rq, null, null);
//    // System.out.println("xoxoxoxooxoxox " + response);
//    // assertNotNull(response);
//    //
//    // ejbC.close();
//    //
//    // } catch (MBSException e) {
//    // System.out.println("Error: " + e);
//    // } catch (javax.naming.NamingException e) {
//    // System.out.println("Error: " + e);
//    // } catch (CommonUtilException e) {
//    // System.out.println("Error: " + e);
//    // }
//    //
//    // }
//}
