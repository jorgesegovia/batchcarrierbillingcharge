
package pe.com.entel.soa.data.generico.responsestatus.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseStatusType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ResponseStatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="responseStatus" type="{http://entel.com.pe/soa/data/generico/responseStatus/v1/}ResponseStatus"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseStatusType", propOrder = { "responseStatus" })
public class ResponseStatusType {

    @XmlElement(required = true)
    protected ResponseStatus responseStatus;

    /**
     * Gets the value of the responseStatus property.
     *
     * @return
     *     possible object is
     *     {@link ResponseStatus }
     *
     */
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    /**
     * Sets the value of the responseStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseStatus }
     *
     */
    public void setResponseStatus(ResponseStatus value) {
        this.responseStatus = value;
    }

}
