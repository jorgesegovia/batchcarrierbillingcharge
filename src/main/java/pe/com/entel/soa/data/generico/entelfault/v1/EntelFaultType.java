
package pe.com.entel.soa.data.generico.entelfault.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntelFaultType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="EntelFaultType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="entelFault" type="{http://entel.com.pe/soa/data/generico/entelFault/v1/}EntelFault"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntelFaultType", propOrder = { "entelFault" })
public class EntelFaultType {

    @XmlElement(required = true)
    protected EntelFault entelFault;

    /**
     * Gets the value of the entelFault property.
     *
     * @return
     *     possible object is
     *     {@link EntelFault }
     *
     */
    public EntelFault getEntelFault() {
        return entelFault;
    }

    /**
     * Sets the value of the entelFault property.
     *
     * @param value
     *     allowed object is
     *     {@link EntelFault }
     *
     */
    public void setEntelFault(EntelFault value) {
        this.entelFault = value;
    }

}
