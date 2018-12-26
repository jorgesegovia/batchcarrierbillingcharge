
package pe.com.entel.esb.data.generico.entelgenericheader.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the pe.com.entel.esb.data.generico.entelgenericheader.v1 package.
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

    private final static QName _HeaderRequest_QNAME =
        new QName("http://entel.com.pe/esb/data/generico/entelGenericHeader/v1/", "headerRequest");
    private final static QName _HeaderResponse_QNAME =
        new QName("http://entel.com.pe/esb/data/generico/entelGenericHeader/v1/", "headerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pe.com.entel.esb.data.generico.entelgenericheader.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HeaderRequestType }
     *
     */
    public HeaderRequestType createHeaderRequestType() {
        return new HeaderRequestType();
    }

    /**
     * Create an instance of {@link HeaderResponseType }
     *
     */
    public HeaderResponseType createHeaderResponseType() {
        return new HeaderResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HeaderRequestType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/esb/data/generico/entelGenericHeader/v1/", name = "headerRequest")
    public JAXBElement<HeaderRequestType> createHeaderRequest(HeaderRequestType value) {
        return new JAXBElement<HeaderRequestType>(_HeaderRequest_QNAME, HeaderRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HeaderResponseType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/esb/data/generico/entelGenericHeader/v1/", name = "headerResponse")
    public JAXBElement<HeaderResponseType> createHeaderResponse(HeaderResponseType value) {
        return new JAXBElement<HeaderResponseType>(_HeaderResponse_QNAME, HeaderResponseType.class, null, value);
    }

}
