
package pe.com.entel.soa.data.cliente.telefono.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the pe.com.entel.soa.data.cliente.telefono.v1 package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NumeroTelefono_QNAME =
        new QName("http://entel.com.pe/soa/data/cliente/telefono/v1/", "numeroTelefono");
    private final static QName _CodArea_QNAME =
        new QName("http://entel.com.pe/soa/data/cliente/telefono/v1/", "codArea");
    private final static QName _TelefonoPrincipal_QNAME =
        new QName("http://entel.com.pe/soa/data/cliente/telefono/v1/", "telefonoPrincipal");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pe.com.entel.soa.data.cliente.telefono.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TelefonoPrincipalType }
     *
     */
    public TelefonoPrincipalType createTelefonoPrincipalType() {
        return new TelefonoPrincipalType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/cliente/telefono/v1/", name = "numeroTelefono")
    public JAXBElement<String> createNumeroTelefono(String value) {
        return new JAXBElement<String>(_NumeroTelefono_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/cliente/telefono/v1/", name = "codArea")
    public JAXBElement<String> createCodArea(String value) {
        return new JAXBElement<String>(_CodArea_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TelefonoPrincipalType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/cliente/telefono/v1/", name = "telefonoPrincipal")
    public JAXBElement<TelefonoPrincipalType> createTelefonoPrincipal(TelefonoPrincipalType value) {
        return new JAXBElement<TelefonoPrincipalType>(_TelefonoPrincipal_QNAME, TelefonoPrincipalType.class, null,
                                                      value);
    }

}
