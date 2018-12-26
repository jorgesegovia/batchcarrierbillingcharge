
package pe.com.entel.soa.data.generico.responsestatus.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the pe.com.entel.soa.data.generico.responsestatus.v1 package.
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

    private final static QName _ResponseStatus_QNAME =
        new QName("http://entel.com.pe/soa/data/generico/responseStatus/v1/", "responseStatus");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pe.com.entel.soa.data.generico.responsestatus.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseStatus }
     *
     */
    public ResponseStatus createResponseStatus() {
        return new ResponseStatus();
    }

    /**
     * Create an instance of {@link ResponseStatusType }
     *
     */
    public ResponseStatusType createResponseStatusType() {
        return new ResponseStatusType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseStatus }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/generico/responseStatus/v1/", name = "responseStatus")
    public JAXBElement<ResponseStatus> createResponseStatus(ResponseStatus value) {
        return new JAXBElement<ResponseStatus>(_ResponseStatus_QNAME, ResponseStatus.class, null, value);
    }

}
