//
// Este archivo se genera mediante JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Cualquier modificación de este archivo se perderá después de volver a compilar el esquema de origen.
// Generado en: AM.09.25 en 10:24:53 AM COT 
//


package com.nequi.uts601.messaging.services.seiya;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para securityType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType name="securityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="publicKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primeModulus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseGenerator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "securityType", propOrder = {
    "publicKey",
    "primeModulus",
    "baseGenerator"
})
public class SecurityType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected String publicKey;
    protected String primeModulus;
    protected String baseGenerator;

    /**
     * Obtiene el valor de la propiedad publicKey.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * Establece el valor de la propiedad publicKey.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicKey(String value) {
        this.publicKey = value;
    }

    /**
     * Obtiene el valor de la propiedad primeModulus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimeModulus() {
        return primeModulus;
    }

    /**
     * Establece el valor de la propiedad primeModulus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimeModulus(String value) {
        this.primeModulus = value;
    }

    /**
     * Obtiene el valor de la propiedad baseGenerator.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseGenerator() {
        return baseGenerator;
    }

    /**
     * Establece el valor de la propiedad baseGenerator.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseGenerator(String value) {
        this.baseGenerator = value;
    }

}
