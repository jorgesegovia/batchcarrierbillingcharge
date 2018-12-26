
package pe.com.entel.soa.data.servicio.carrierbilling.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CarrierBillingCobroConfirmacionType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CarrierBillingCobroConfirmacionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}requestId" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}bangoTransactionId" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}merchantTransactionId" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}paymentProviderTransactionId" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}userId" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}amount" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}currency" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarrierBillingCobroConfirmacionType", propOrder = {
         "requestId", "bangoTransactionId", "merchantTransactionId", "paymentProviderTransactionId", "userId", "amount",
         "currency"
    })
public class CarrierBillingCobroConfirmacionType {

    protected String requestId;
    protected String bangoTransactionId;
    protected String merchantTransactionId;
    protected String paymentProviderTransactionId;
    protected String userId;
    protected String amount;
    protected String currency;

    /**
     * Gets the value of the requestId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the bangoTransactionId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBangoTransactionId() {
        return bangoTransactionId;
    }

    /**
     * Sets the value of the bangoTransactionId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBangoTransactionId(String value) {
        this.bangoTransactionId = value;
    }

    /**
     * Gets the value of the merchantTransactionId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    /**
     * Sets the value of the merchantTransactionId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMerchantTransactionId(String value) {
        this.merchantTransactionId = value;
    }

    /**
     * Gets the value of the paymentProviderTransactionId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPaymentProviderTransactionId() {
        return paymentProviderTransactionId;
    }

    /**
     * Sets the value of the paymentProviderTransactionId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPaymentProviderTransactionId(String value) {
        this.paymentProviderTransactionId = value;
    }

    /**
     * Gets the value of the userId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the amount property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the currency property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

}
