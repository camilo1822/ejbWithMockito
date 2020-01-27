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
 * <p>Clase Java para integrationResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType name="integrationResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://nequi.co/message/integration/services/}ResponseHeaderType"/>
 *         &lt;element name="body" type="{http://nequi.co/message/integration/services/}ResponseBodyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "integrationResponseType", propOrder = {
    "header",
    "body"
})
public class IntegrationResponseType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected ResponseHeaderType header;
    @XmlElement(required = true)
    protected ResponseBodyType body;

    /**
     * Obtiene el valor de la propiedad header.
     * 
     * @return
     *     possible object is
     *     {@link ResponseHeaderType }
     *     
     */
    public ResponseHeaderType getHeader() {
        return header;
    }

    /**
     * Establece el valor de la propiedad header.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseHeaderType }
     *     
     */
    public void setHeader(ResponseHeaderType value) {
        this.header = value;
    }

    /**
     * Obtiene el valor de la propiedad body.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBodyType }
     *     
     */
    public ResponseBodyType getBody() {
        return body;
    }

    /**
     * Establece el valor de la propiedad body.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBodyType }
     *     
     */
    public void setBody(ResponseBodyType value) {
        this.body = value;
    }

}
