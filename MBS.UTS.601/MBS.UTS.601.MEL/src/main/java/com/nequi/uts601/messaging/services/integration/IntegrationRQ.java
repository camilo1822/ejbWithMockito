//
// Este archivo se genera mediante JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Cualquier modificación de este archivo se perderá después de volver a compilar el esquema de origen.
// Generado en: PM.09.24 en 02:07:05 PM COT 
//


package com.nequi.uts601.messaging.services.integration;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://nequi.co/message/integration/services/}integrationRequest"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "integrationRequest"
})
@XmlRootElement(name = "integrationRQ", namespace = "http://nequi.co/message/integration/services/")
public class IntegrationRQ
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected IntegrationRequestType integrationRequest;

    /**
     * Obtiene el valor de la propiedad integrationRequest.
     * 
     * @return
     *     possible object is
     *     {@link IntegrationRequestType }
     *     
     */
    public IntegrationRequestType getIntegrationRequest() {
        return integrationRequest;
    }

    /**
     * Establece el valor de la propiedad integrationRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegrationRequestType }
     *     
     */
    public void setIntegrationRequest(IntegrationRequestType value) {
        this.integrationRequest = value;
    }

}
