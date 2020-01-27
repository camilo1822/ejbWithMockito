/**
 * 
 */
package com.nequi.uts601.ejb.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.nequi.cmm.consumer.exception.CommonUtilException;
import com.nequi.cmm.consumer.util.UtilJSON;
import com.nequi.mdw.common.jpa.exception.JPAException;
import com.nequi.mdw.common.jpa.model.entities.Atributo;
import com.nequi.mdw.common.jpa.model.entities.Parametro;
import com.nequi.mdw.common.jpa.model.entities.ParametroAtributo;
import com.nequi.mdw.common.jpa.service.ParameterJPAService;
import com.nequi.uts601.ejb.exception.MBSException;
import com.nequi.uts601.ejb.service.co.seiya.checkavailability.CheckAvailabilityToMock;
import com.nequi.uts601.ejb.util.CommonUtil;
import com.nequi.uts601.messaging.services.seiya.ChannelType;
import com.nequi.uts601.messaging.services.seiya.CheckAvailabilityRQType;
import com.nequi.uts601.messaging.services.seiya.ContainerType;
import com.nequi.uts601.messaging.services.seiya.DestinationType;
import com.nequi.uts601.messaging.services.seiya.MessageRQ;
import com.nequi.uts601.messaging.services.seiya.MessageRS;
import com.nequi.uts601.messaging.services.seiya.RequestBodyType;
import com.nequi.uts601.messaging.services.seiya.RequestHeaderType;
import com.nequi.uts601.messaging.services.seiya.RequestMessageType;

public class MockitoTest {

    @InjectMocks
    private CheckAvailabilityToMock checkAvailabilityToMock = new CheckAvailabilityToMock();

    @Mock
    private ParameterJPAService parameterJPAService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeSuccesTest() {

        try {
            // Creamos mock de la clase CheckAvailabilityToMock
            CheckAvailabilityToMock checkAvailabilityMocked = Mockito
                    .mock(CheckAvailabilityToMock.class);

            MessageRS messageRS = CommonUtil.responseErrorMessage(null, "0",
                    "MDW", "APP", null, null);

            /*
             * Le decimos que cuando el metodo executeOperation sea llamado con
             * cualquier parametro, responda success.
             */
            Mockito.when(checkAvailabilityMocked.executeOperation(Mockito.any(),
                    Mockito.any(), Mockito.any()))
                    .thenReturn(CommonUtil.generateResponseAsString(messageRS,
                            null, "12321321", "3222222222"));
            String response = checkAvailabilityMocked.executeOperation("", null,
                    null);
            MessageRS messageRs = UtilJSON
                    .parsePayloadWithJaxbAnnotation(response, MessageRS.class);
            assertEquals("0", messageRs.getResponseMessage().getHeader()
                    .getStatus().getCode());
        } catch (MBSException e) {
            System.out.println("Error " + e);
        } catch (CommonUtilException e) {
            System.out.println("Error " + e);
        }
    }

    @Test(expected = NullPointerException.class)
    public void executeSuccesGetParametersList() {

        try {

            List<Parametro> parameterList = new ArrayList<>();
            Parametro parametro = new Parametro();
            parametro.setNombre("Prueba");
            parametro.setValor("prueba");
            parameterList.add(parametro);
            /*
             * Mockeamos el comportamiento de la respuesta de un metodo en
             * especifico de la clase, por eso en este caso usamos
             * CheckAvailabilityToMock y no creamos mock de toda esta
             */
            Mockito.when(parameterJPAService.getRegionParameter(Mockito.any(),
                    Mockito.any(), Mockito.any())).thenReturn(parameterList);

            // armamos RQ
            MessageRQ messageRQ = new MessageRQ();
            RequestMessageType requestMessageType = new RequestMessageType();
            RequestHeaderType header = new RequestHeaderType();
            header.setMessageID("234454");
            header.setRequestDate("2020-01-25");
            DestinationType destinationType = new DestinationType();
            destinationType.setServiceName("FinancialServices");
            destinationType.setServiceOperation("checkAvailability");
            destinationType.setServiceRegion("C001");
            destinationType.setServiceVersion("1.0.0");
            header.setDestination(destinationType);
            requestMessageType.setHeader(header);
            RequestBodyType requestBodyType = new RequestBodyType();
            CheckAvailabilityRQType checkAvailabilityRQType = new CheckAvailabilityRQType();
            checkAvailabilityRQType.setOperation("essaAvailability");
            requestBodyType.setCheckAvailabilityRQ(checkAvailabilityRQType);
            requestMessageType.setBody(requestBodyType);
            messageRQ.setRequestMessage(requestMessageType);
            String rq = UtilJSON.parseObjectToString(messageRQ);

            String response = checkAvailabilityToMock.executeOperation(rq, null,
                    null);
            System.out.println(response);
            MessageRS messageRs = UtilJSON
                    .parsePayloadWithJaxbAnnotation(response, MessageRS.class);
            assertEquals("456", messageRs.getResponseMessage().getHeader()
                    .getStatus().getCode());
        } catch (MBSException e) {
            System.out.println("Error " + e);
        } catch (CommonUtilException e) {
            System.out.println("Error " + e);
        } catch (JPAException e) {
            System.out.println("Error " + e);
        }
    }

    @Test
    public void validateAvailabilitTestSuccess() {

        try {

            List<Parametro> parameterList = new ArrayList<>();
            Parametro parametro = new Parametro();
            parametro.setNombre("essaAvailability");
            parametro.setValor("essaAvailability");
            parametro.setParametroId("1961");

            List<ParametroAtributo> parametroAtributoList = new ArrayList<>();
            ParametroAtributo parametroAtributo = new ParametroAtributo();
            parametroAtributo.setValor("07:00:00");
            parametroAtributo.setParametro(parametro);
            Atributo atributo = new Atributo();
            atributo.setAtributoId("22");
            atributo.setNombre("initialHour");
            parametroAtributo.setAtributo(atributo);
            parametroAtributoList.add(parametroAtributo);

            parametroAtributo = new ParametroAtributo();
            parametroAtributo.setValor("21:59:00");
            parametroAtributo.setParametro(parametro);
            atributo = new Atributo();
            atributo.setAtributoId("23");
            atributo.setNombre("finalHour");
            parametroAtributo.setAtributo(atributo);
            parametroAtributoList.add(parametroAtributo);

            parametro.setParametroAtributos(parametroAtributoList);
            parameterList.add(parametro);
            Mockito.when(parameterJPAService.getRegionParameter(Mockito.any(),
                    Mockito.any(), Mockito.any())).thenReturn(parameterList);

            // armamos RQ
            MessageRQ messageRQ = new MessageRQ();
            RequestMessageType requestMessageType = new RequestMessageType();
            RequestHeaderType header = new RequestHeaderType();
            header.setMessageID("234454");
            header.setRequestDate("2020-01-25");
            DestinationType destinationType = new DestinationType();
            destinationType.setServiceName("FinancialServices");
            destinationType.setServiceOperation("essaAvailability");
            destinationType.setServiceRegion("C001");
            destinationType.setServiceVersion("1.0.0");
            header.setDestination(destinationType);
            ChannelType channelType = new ChannelType();
            channelType.setId("Channl_Test");
            channelType.setName("Channl_Test");
            header.setChannel(channelType);
            requestMessageType.setHeader(header);
            RequestBodyType requestBodyType = new RequestBodyType();
            CheckAvailabilityRQType checkAvailabilityRQType = new CheckAvailabilityRQType();
            checkAvailabilityRQType.setOperation("essaAvailability");
            requestBodyType.setCheckAvailabilityRQ(checkAvailabilityRQType);
            requestMessageType.setBody(requestBodyType);
            messageRQ.setRequestMessage(requestMessageType);
            String rq = UtilJSON.parseObjectToString(messageRQ);

            String response = checkAvailabilityToMock.executeOperation(rq, null,
                    null);
            MessageRS messageRs = UtilJSON
                    .parsePayloadWithJaxbAnnotation(response, MessageRS.class);
            assertEquals("0", messageRs.getResponseMessage().getHeader()
                    .getStatus().getCode());
        } catch (MBSException e) {
            System.out.println("Error " + e);
        } catch (CommonUtilException e) {
            System.out.println("Error " + e);
        } catch (JPAException e) {
            System.out.println("Error " + e);
        }
    }

    @Test(expected = NullPointerException.class)
    public void validateAvailabilitTestFailed() {

        try {

            List<Parametro> parameterList = new ArrayList<>();
            Parametro parametro = new Parametro();
            parametro.setNombre("essaAvailability");
            parametro.setValor("essaAvailability");
            parametro.setParametroId("1961");

            List<ParametroAtributo> parametroAtributoList = new ArrayList<>();
            ParametroAtributo parametroAtributo = new ParametroAtributo();
            parametroAtributo.setValor("03:00:00");
            parametroAtributo.setParametro(parametro);
            Atributo atributo = new Atributo();
            atributo.setAtributoId("22");
            atributo.setNombre("initialHour");
            parametroAtributo.setAtributo(atributo);
            parametroAtributoList.add(parametroAtributo);

            parametroAtributo = new ParametroAtributo();
            parametroAtributo.setValor("03:00:01");
            parametroAtributo.setParametro(parametro);
            atributo = new Atributo();
            atributo.setAtributoId("23");
            atributo.setNombre("finalHour");
            parametroAtributo.setAtributo(atributo);
            parametroAtributoList.add(parametroAtributo);

            parametro.setParametroAtributos(parametroAtributoList);
            parameterList.add(parametro);
            Mockito.when(parameterJPAService.getRegionParameter(Mockito.any(),
                    Mockito.any(), Mockito.any())).thenReturn(parameterList);

            // armamos RQ
            MessageRQ messageRQ = new MessageRQ();
            RequestMessageType requestMessageType = new RequestMessageType();
            RequestHeaderType header = new RequestHeaderType();
            header.setMessageID("234454");
            header.setRequestDate("2020-01-25");
            DestinationType destinationType = new DestinationType();
            destinationType.setServiceName("FinancialServices");
            destinationType.setServiceOperation("essaAvailability");
            destinationType.setServiceRegion("C001");
            destinationType.setServiceVersion("1.0.0");
            header.setDestination(destinationType);
            ChannelType channelType = new ChannelType();
            channelType.setId("Channl_Test");
            channelType.setName("Channl_Test");
            header.setChannel(channelType);
            ContainerType containerType = new ContainerType();
            containerType.setId("Channl_Test");
            containerType.setName("Channl_Test");
            header.setContainer(containerType);
            requestMessageType.setHeader(header);
            RequestBodyType requestBodyType = new RequestBodyType();
            CheckAvailabilityRQType checkAvailabilityRQType = new CheckAvailabilityRQType();
            checkAvailabilityRQType.setOperation("essaAvailability");
            requestBodyType.setCheckAvailabilityRQ(checkAvailabilityRQType);
            requestMessageType.setBody(requestBodyType);
            messageRQ.setRequestMessage(requestMessageType);
            String rq = UtilJSON.parseObjectToString(messageRQ);

            checkAvailabilityToMock.executeOperation(rq, null, null);
        } catch (MBSException e) {
            System.out.println("Error " + e);
        } catch (CommonUtilException e) {
            System.out.println("Error " + e);
        } catch (JPAException e) {
            System.out.println("Error " + e);
        }
    }
}
