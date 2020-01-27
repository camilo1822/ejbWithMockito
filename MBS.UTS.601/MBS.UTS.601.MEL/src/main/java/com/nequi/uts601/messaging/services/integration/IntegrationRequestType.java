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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para integrationRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType name="integrationRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://nequi.co/message/integration/services/}RequestHeaderType"/>
 *         &lt;element name="body" type="{http://nequi.co/message/integration/services/}RequestBodyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "integrationRequestType", propOrder = {
    "header",
    "body"
})
public class IntegrationRequestType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected RequestHeaderType header;
    @XmlElement(required = true)
    protected RequestBodyType body;

    /**
     * Obtiene el valor de la propiedad header.
     * 
     * @return
     *     possible object is
     *     {@link RequestHeaderType }
     *     
     */
    public RequestHeaderType getHeader() {
        return header;
    }

    /**
     * Establece el valor de la propiedad header.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestHeaderType }
     *     
     */
    public void setHeader(RequestHeaderType value) {
        this.header = value;
    }

    /**
     * Obtiene el valor de la propiedad body.
     * 
     * @return
     *     possible object is
     *     {@link RequestBodyType }
     *     
     */
    public RequestBodyType getBody() {
        return body;
    }

    /**
     * Establece el valor de la propiedad body.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestBodyType }
     *     
     */
    public void setBody(RequestBodyType value) {
        this.body = value;
    }

}
