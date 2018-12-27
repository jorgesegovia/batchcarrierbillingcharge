package pe.com.entel.aplicacion.carrierbilling.service;

import com.oracle.xmlns.carrierbilling.bpel_gestioncobro.EjecutarCobroRequestType;
import com.oracle.xmlns.carrierbilling.bpel_gestioncobro.EjecutarCobroResponseType;
import com.oracle.xmlns.carrierbilling.bpel_gestioncobroconfirmacion.EjecutarCobroConfirmacionRequestType;
import com.oracle.xmlns.carrierbilling.bpel_gestioncobroconfirmacion.EjecutarCobroConfirmacionResponseType;
import org.apache.log4j.Logger;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringSource;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.soa.data.servicio.carrierbilling.v1.CarrierBillingCobroConfirmacionType;
import pe.com.entel.soa.data.servicio.carrierbilling.v1.CarrierBillingCobroType;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jsegovia
 * @version 1.0, 12/20/18
 */
public class GestionCobroService {

    public static final String TRANSACCION_DATE_FORMAT = "yyyyMMdd_HHmm";

    public static final String FECHAINICIO_DATE_FORMAT = "yyyy-MM-dd HH:mm";

    static Logger logger = Logger.getLogger(GestionCobroService.class);

    private WebServiceTemplate cobroWsTemplate;

    private WebServiceTemplate cobroConfirmacionWsTemplate;

    private String currency;

    private String idAplicacion;

    private String userId;

    public Suscripcion ejecutarCobro(Suscripcion s) throws Exception {

        ejecutarCobroWs(s);

        if ("0000".equals(s.getCodigorpta())) {
            ejecutarCobroConfirmacionWs(s);
        }

        logger.debug("Suscripcion: " + s);

        return s;
    }

    public Suscripcion ejecutarCobroWs(Suscripcion s) throws Exception {

        com.oracle.xmlns.carrierbilling.bpel_gestioncobro.ObjectFactory factory1
                = new com.oracle.xmlns.carrierbilling.bpel_gestioncobro.ObjectFactory();
        EjecutarCobroRequestType ejecutarCobroReq = factory1.createEjecutarCobroRequestType();

        pe.com.entel.soa.data.servicio.carrierbilling.v1.ObjectFactory factory2
                = new pe.com.entel.soa.data.servicio.carrierbilling.v1.ObjectFactory();

        CarrierBillingCobroType cbCobroType = factory2.createCarrierBillingCobroType();
        cbCobroType.setRequestId("12345678");
        cbCobroType.setBangoTransactionId("12345678");
        cbCobroType.setMerchantTransactionId(s.getMerchantTransactionId());
        cbCobroType.setUserId(s.getIdCliente());
        cbCobroType.setAmount(String.valueOf(s.getMontoCobro()));
        cbCobroType.setCurrency(currency);
        cbCobroType.setMerchantAccountKey(s.getMerchantAccountKey());
        cbCobroType.setProductKey(s.getProductKey());
        cbCobroType.setProductDescription(s.getProductDescription());
        cbCobroType.setProductCategory(s.getProductCategory());

        ejecutarCobroReq.setDatosCobro(cbCobroType);

        SimpleDateFormat trxFormat = new SimpleDateFormat(TRANSACCION_DATE_FORMAT);
        String todayTrxFormat = trxFormat.format(new Date());

        SimpleDateFormat fechaInicioFormat = new SimpleDateFormat(FECHAINICIO_DATE_FORMAT);
        String todayfechaInicioFormat = fechaInicioFormat.format(new Date());

        logger.info("URI: " + cobroWsTemplate.getDefaultUri());

        EjecutarCobroResponseType ejecutarCobroResp = (EjecutarCobroResponseType) cobroWsTemplate.marshalSendAndReceive(ejecutarCobroReq,
                new BpelHeaderMessageCallBack(s.getCanal(), idAplicacion, userId,
                        todayTrxFormat, todayTrxFormat, todayfechaInicioFormat));


        if (ejecutarCobroResp == null) {
            throw new Exception("Respuesta del servicio Gestioncobro es null");
        }

        String codigoRespuestWs = ejecutarCobroResp.getResponseStatus().getCodigoRespuesta();
        String descripcionRespuestaWs = ejecutarCobroResp.getResponseStatus().getDescripcionRespuesta();
        logger.info("Codigo Rspta: " + codigoRespuestWs);
        logger.info("Descripcion Rspta: " + descripcionRespuestaWs);

        s.setCodigorpta(codigoRespuestWs);
        s.setDescripcionrpta(descripcionRespuestaWs);

        if ("0000".equals(codigoRespuestWs)) {
            if (ejecutarCobroResp.getResponseData() != null) {
                String paymentTransactionId = ejecutarCobroResp.getResponseData().getIdtransacccion();
                s.setPaymentTransactionId(paymentTransactionId);
            }
        }

        return s;
    }

    public Suscripcion ejecutarCobroConfirmacionWs(Suscripcion s) throws Exception {

        com.oracle.xmlns.carrierbilling.bpel_gestioncobroconfirmacion.ObjectFactory factory1
                = new com.oracle.xmlns.carrierbilling.bpel_gestioncobroconfirmacion.ObjectFactory();
        EjecutarCobroConfirmacionRequestType request = factory1.createEjecutarCobroConfirmacionRequestType();

        pe.com.entel.soa.data.servicio.carrierbilling.v1.ObjectFactory factory2
                = new pe.com.entel.soa.data.servicio.carrierbilling.v1.ObjectFactory();

        CarrierBillingCobroConfirmacionType cbCobroType = factory2.createCarrierBillingCobroConfirmacionType();
        cbCobroType.setRequestId("12345678");
        cbCobroType.setBangoTransactionId("12345678");
        cbCobroType.setMerchantTransactionId(s.getMerchantTransactionId());
        cbCobroType.setUserId(s.getIdCliente());
        cbCobroType.setAmount(String.valueOf(s.getMontoCobro()));
        cbCobroType.setCurrency(currency);
        cbCobroType.setPaymentProviderTransactionId(s.getPaymentTransactionId());

        request.setDatosCobro(cbCobroType);

        SimpleDateFormat trxFormat = new SimpleDateFormat(TRANSACCION_DATE_FORMAT);
        String todayTrxFormat = trxFormat.format(new Date());

        SimpleDateFormat fechaInicioFormat = new SimpleDateFormat(FECHAINICIO_DATE_FORMAT);
        String todayfechaInicioFormat = fechaInicioFormat.format(new Date());

        logger.info("URI: " + cobroWsTemplate.getDefaultUri());

        EjecutarCobroConfirmacionResponseType response = (EjecutarCobroConfirmacionResponseType) cobroConfirmacionWsTemplate.marshalSendAndReceive(request,
                new BpelHeaderMessageCallBack(s.getCanal(), idAplicacion, userId,
                        todayTrxFormat, todayTrxFormat, todayfechaInicioFormat));


        if (response == null) {
            throw new Exception("Respuesta del servicio Gestioncobroconfirmacion es null");
        }

        String codigoRespuestWs = response.getResponseStatus().getCodigoRespuesta();
        String descripcionRespuestaWs = response.getResponseStatus().getDescripcionRespuesta();
        logger.info("Codigo Rspta: " + codigoRespuestWs);
        logger.info("Descripcion Rspta: " + descripcionRespuestaWs);
        s.setCodigorpta(codigoRespuestWs);
        s.setDescripcionrpta(descripcionRespuestaWs);

        return s;
    }

    public WebServiceTemplate getCobroWsTemplate() {
        return cobroWsTemplate;
    }

    public void setCobroWsTemplate(WebServiceTemplate cobroWsTemplate) {
        this.cobroWsTemplate = cobroWsTemplate;
    }

    public WebServiceTemplate getCobroConfirmacionWsTemplate() {
        return cobroConfirmacionWsTemplate;
    }

    public void setCobroConfirmacionWsTemplate(WebServiceTemplate cobroConfirmacionWsTemplate) {
        this.cobroConfirmacionWsTemplate = cobroConfirmacionWsTemplate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(String idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public class BpelHeaderMessageCallBack implements WebServiceMessageCallback {

        private String canal;
        private String headerIdApp;
        private String headerUsuario;
        private String headerIdTransaccionESB;
        private String headerIdTransaccionNegocio;
        private String fechaInicio;

        public BpelHeaderMessageCallBack(String canal, String headerIdApp, String headerUsuario,
                                         String headerIdTransaccionESB, String headerIdTransaccionNegocio,
                                         String fechaInicio) {
            this.canal = canal;
            this.headerIdApp = headerIdApp;
            this.headerUsuario = headerUsuario;
            this.headerIdTransaccionESB = headerIdTransaccionESB;
            this.headerIdTransaccionNegocio = headerIdTransaccionNegocio;
            this.fechaInicio = fechaInicio;
        }

        @Override
        public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
            try {
                SoapMessage soapMessage = (SoapMessage) message;
                SoapHeader header = soapMessage.getSoapHeader();
                StringBuilder headerSb
                        = new StringBuilder("<v1:headerRequest xmlns:v1=\"http://entel.com.pe/esb/data/generico/entelGenericHeader/v1/\">").append("\n");
                headerSb.append("<v1:canal>").append(canal).append("</v1:canal>").append("\n");
                headerSb.append("<v1:idAplicacion>").append(headerIdApp).append("</v1:idAplicacion>").append("\n");
                headerSb.append("<v1:usuario>").append(headerUsuario).append("</v1:usuario>").append("\n");
                headerSb.append("<v1:idTransaccionESB>").append(headerIdTransaccionESB).append("</v1:idTransaccionESB>").append("\n");
                headerSb.append("<v1:idTransaccionNegocio>").append(headerIdTransaccionNegocio).append("</v1:idTransaccionNegocio>").append("\n");
                headerSb.append("<v1:fechaInicio>").append(fechaInicio).append("</v1:fechaInicio>").append("\n");
                headerSb.append("</v1:headerRequest>");
                StringSource headerSource = new StringSource(headerSb.toString());
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(headerSource, header.getResult());

            } catch (Exception e) {
                logger.error(e);
            }
        }

    }

}
