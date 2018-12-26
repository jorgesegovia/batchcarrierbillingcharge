
package pe.com.entel.soa.data.servicio.carrierbilling.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CarrierBillingDevolucionType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CarrierBillingDevolucionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}transactionId" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}producto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarrierBillingDevolucionType", propOrder = { "transactionId", "producto" })
public class CarrierBillingDevolucionType {

    protected String transactionId;
    protected String producto;

    /**
     * Gets the value of the transactionId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the producto property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Sets the value of the producto property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProducto(String value) {
        this.producto = value;
    }

}
