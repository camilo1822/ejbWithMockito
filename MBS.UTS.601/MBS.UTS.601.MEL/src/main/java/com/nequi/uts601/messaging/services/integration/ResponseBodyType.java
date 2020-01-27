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
 * <p>Clase Java para ResponseBodyType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResponseBodyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://nequi.co/message/integration/services/email401/operations}emailGeneralResponse"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseBodyType", propOrder = {
    "emailGeneralResponse"
})
public class ResponseBodyType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "http://nequi.co/message/integration/services/email401/operations")
    protected EmailGeneralResponseType emailGeneralResponse;

    /**
     * Obtiene el valor de la propiedad emailGeneralResponse.
     * 
     * @return
     *     possible object is
     *     {@link EmailGeneralResponseType }
     *     
     */
    public EmailGeneralResponseType getEmailGeneralResponse() {
        return emailGeneralResponse;
    }

    /**
     * Establece el valor de la propiedad emailGeneralResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailGeneralResponseType }
     *     
     */
    public void setEmailGeneralResponse(EmailGeneralResponseType value) {
        this.emailGeneralResponse = value;
    }

}
