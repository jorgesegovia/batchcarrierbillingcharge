
package com.oracle.xmlns.carrierbilling.bpel_gestioncobro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import pe.com.entel.soa.data.servicio.carrierbilling.v1.CarrierBillingCobroType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="datosCobro" type="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}CarrierBillingCobroType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "datosCobro" })
@XmlRootElement(name = "ejecutarCobroRequestType")
public class EjecutarCobroRequestType {

    @XmlElement(required = true)
    protected CarrierBillingCobroType datosCobro;

    /**
     * Gets the value of the datosCobro property.
     *
     * @return
     *     possible object is
     *     {@link CarrierBillingCobroType }
     *
     */
    public CarrierBillingCobroType getDatosCobro() {
        return datosCobro;
    }

    /**
     * Sets the value of the datosCobro property.
     *
     * @param value
     *     allowed object is
     *     {@link CarrierBillingCobroType }
     *
     */
    public void setDatosCobro(CarrierBillingCobroType value) {
        this.datosCobro = value;
    }

}
