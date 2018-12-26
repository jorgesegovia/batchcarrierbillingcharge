
package pe.com.entel.soa.data.cliente.telefono.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TelefonoPrincipalType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TelefonoPrincipalType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/cliente/telefono/v1/}numeroTelefono" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/cliente/telefono/v1/}codArea" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TelefonoPrincipalType", propOrder = { "numeroTelefono", "codArea" })
public class TelefonoPrincipalType {

    protected String numeroTelefono;
    protected String codArea;

    /**
     * Gets the value of the numeroTelefono property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * Sets the value of the numeroTelefono property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNumeroTelefono(String value) {
        this.numeroTelefono = value;
    }

    /**
     * Gets the value of the codArea property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodArea() {
        return codArea;
    }

    /**
     * Sets the value of the codArea property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodArea(String value) {
        this.codArea = value;
    }

}
