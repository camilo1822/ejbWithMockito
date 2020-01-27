/**
 * 
 */
package com.nequi.uts601.ejb.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.nequi.uts601.messaging.services.integration.IntegrationRS;
import com.nequi.uts601.messaging.services.integration.IntegrationResponseType;
import com.nequi.uts601.messaging.services.integration.ResponseHeaderType;
import com.nequi.uts601.messaging.services.integration.StatusType;
import com.nequi.uts601.messaging.services.seiya.ContentType;
import com.nequi.uts601.messaging.services.seiya.RequestHeaderType;

public class IntegrationUtilTest {

    @Test
    public void testGetIntegrationRQ() {
        RequestHeaderType header = new RequestHeaderType();
        header.setMessageID("123456789");
        assertNotNull(com.nequi.uts601.ejb.util.IntegrationUtil
                .getIntegrationRQ(header, "C001", "Prueba", "Prueba", "1.0.0"));
    }

    @Test
    public void testSuccessResponse() {
        IntegrationRS integrationRS = new IntegrationRS();
        IntegrationResponseType integrationResponseType = new IntegrationResponseType();
        ResponseHeaderType responseHeaderType = new ResponseHeaderType();
        StatusType statusType = new StatusType();
        statusType.setCode("0");
        responseHeaderType.setStatus(statusType);
        integrationResponseType.setHeader(responseHeaderType);
        integrationRS.setIntegrationResponse(integrationResponseType);
        assertTrue(com.nequi.uts601.ejb.util.IntegrationUtil
                .successResponse(integrationRS));
    }

    @Test
    public void testFailResponse() {
        IntegrationRS integrationRS = new IntegrationRS();
        IntegrationResponseType integrationResponseType = new IntegrationResponseType();
        ResponseHeaderType responseHeaderType = new ResponseHeaderType();
        StatusType statusType = new StatusType();
        statusType.setCode("500");
        responseHeaderType.setStatus(statusType);
        integrationResponseType.setHeader(responseHeaderType);
        integrationRS.setIntegrationResponse(integrationResponseType);
        assertFalse(com.nequi.uts601.ejb.util.IntegrationUtil
                .successResponse(integrationRS));
    }

    @Test
    public void testGetEmailGeneralRequestType() {
        RequestHeaderType header = new RequestHeaderType();
        header.setMessageID("123456789");
        assertNotNull(com.nequi.uts601.ejb.util.IntegrationUtil
                .getEmailGeneralRequestType("M111", "correo@falso.com", "pdf",
                        Arrays.asList("file"), null, Arrays.asList("names"),
                        Arrays.asList("correo@prueba.com")));
    }

    @Test
    public void testGetListContentType() {
        List<ContentType> contents = new ArrayList<>();
        ContentType contentType = new ContentType();
        contentType.setKey("prueba");
        contentType.setValue("prueba");
        contents.add(contentType);
        assertNotNull(com.nequi.uts601.ejb.util.IntegrationUtil
                .getListContentType(contents));
    }
}
