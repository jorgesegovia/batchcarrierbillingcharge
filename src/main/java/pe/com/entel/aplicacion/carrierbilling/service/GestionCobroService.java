package pe.com.entel.aplicacion.carrierbilling.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.SoapFaultDetailElement;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.xml.transform.StringSource;

import com.oracle.xmlns.carrierbilling.bpel_gestioncobro.EjecutarCobroRequestType;
import com.oracle.xmlns.carrierbilling.bpel_gestioncobro.EjecutarCobroResponseType;
import com.oracle.xmlns.carrierbilling.bpel_gestioncobroconfirmacion.EjecutarCobroConfirmacionRequestType;
import com.oracle.xmlns.carrierbilling.bpel_gestioncobroconfirmacion.EjecutarCobroConfirmacionResponseType;

import pe.com.entel.aplicacion.carrierbilling.domain.EjecucionActualizacion;
import pe.com.entel.aplicacion.carrierbilling.domain.EjecucionCobro;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.repository.InsertErrorCancelacionStoreProcedure;
import pe.com.entel.soa.data.generico.entelfault.v1.EntelFault;
import pe.com.entel.soa.data.servicio.carrierbilling.v1.CarrierBillingCobroConfirmacionType;
import pe.com.entel.soa.data.servicio.carrierbilling.v1.CarrierBillingCobroType;

/**
 * @author jsegovia
 * @version 1.0, 12/20/18
 */
public class GestionCobroService {

    public static final String TRANSACCION_DATE_FORMAT = "yyyyMMdd_HHmm";

    public static final String FECHAINICIO_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    
    static Logger logger = Logger.getLogger(GestionCobroService.class);

    private static final String TIPO_CANCELACION = "CANCELACION";
    
    private static final String TIPO_DEGRADACION = "DEGRADACION";
    
    private WebServiceTemplate cobroWsTemplate;

    private WebServiceTemplate cobroConfirmacionWsTemplate;

    private String currency;

    private String idAplicacion;

    private String userId;

    private SoapClientInterceptor interceptor = new SoapClientInterceptor();

    private EjecucionCobro ejecucionCobro = new EjecucionCobro();
    
    private ActualizarSuscripcionService actualizarSuscripcionService;
    
    private InsertErrorCancelacionStoreProcedure procedureError;

    public EjecucionCobro ejecutarCobro(Suscripcion s) throws Exception {

        logger.info("Ejecutando cobro para suscripcion [ " + s.getIdSuscripcion() + " ] ...");

        if (TIPO_CANCELACION.equals(StringUtils.upperCase(s.getTipoEjecucion()))) {
        	ejecutarActualizarSuscripcion(s);
        }else {
            ejecutarCobroWs(s);

            if ("0000".equals(s.getCodigorpta())) {
                ejecutarCobroConfirmacionWs(s);
            }

            logger.debug("Suscripcion: " + s);
            
            if("0000".equals(s.getCodigorpta()) && TIPO_DEGRADACION.equals(StringUtils.upperCase(s.getTipoEjecucion()))) {
            	ejecutarActualizarSuscripcion(s);
            }
        }

        return ejecucionCobro;
    }

    public void ejecutarCobroWs(Suscripcion s) throws Exception {

        com.oracle.xmlns.carrierbilling.bpel_gestioncobro.ObjectFactory factory1
                = new com.oracle.xmlns.carrierbilling.bpel_gestioncobro.ObjectFactory();
        EjecutarCobroRequestType ejecutarCobroReq = factory1.createEjecutarCobroRequestType();

        pe.com.entel.soa.data.servicio.carrierbilling.v1.ObjectFactory factory2
                = new pe.com.entel.soa.data.servicio.carrierbilling.v1.ObjectFactory();

        CarrierBillingCobroType cbCobroType = factory2.createCarrierBillingCobroType();
        cbCobroType.setRequestId(s.getRequestId());
        cbCobroType.setBangoTransactionId(s.getBangoTransactionId());
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

        logger.debug("URI: " + cobroWsTemplate.getDefaultUri());

        cobroWsTemplate.setInterceptors(new ClientInterceptor[]{interceptor});

        EjecutarCobroResponseType ejecutarCobroResp = null;
        String codigoRespuestWs = null;
        String descripcionRespuestaWs = null;


        try {
            ejecutarCobroResp = (EjecutarCobroResponseType) cobroWsTemplate.marshalSendAndReceive(ejecutarCobroReq,
                    new BpelHeaderMessageCallBack(s.getCanal(), idAplicacion, userId,
                            todayTrxFormat, todayTrxFormat, todayfechaInicioFormat));

            codigoRespuestWs = ejecutarCobroResp.getResponseStatus().getCodigoRespuesta();
            descripcionRespuestaWs = ejecutarCobroResp.getResponseStatus().getDescripcionRespuesta();

        } catch (SoapFaultClientException e) {
            logger.debug("e.getFaultCode() :" + e.getFaultCode());
            logger.debug("e.getSoapFault() :" + e.getSoapFault());
            logger.debug("e.getFaultCode().getFaultDetail() :" + e.getSoapFault().getFaultDetail());
            logger.debug("e.getFaultCode().getFaultDetail().getResult() :" + e.getSoapFault().getFaultDetail().getResult());
            logger.debug("e.getFaultStringOrReason() :" + e.getFaultStringOrReason());

            SoapFaultDetail soapFaultDetail = e.getSoapFault().getFaultDetail();

            if (soapFaultDetail == null) {
                throw e;
            }
            SoapFaultDetailElement detailElementChild = (SoapFaultDetailElement) soapFaultDetail.getDetailEntries().next();
            Source detailSource = detailElementChild.getSource();

            logger.debug("detailElementChild.getSource() :" + detailElementChild.getSource());

            try {
                JAXBElement<EntelFault> jaxbEntelFault = (JAXBElement<EntelFault>) cobroWsTemplate.getUnmarshaller().unmarshal(detailSource);

                if (jaxbEntelFault != null) {
                    logger.debug("entelFault :" + jaxbEntelFault.getValue());
                    codigoRespuestWs = jaxbEntelFault.getValue().getCodigoError();
                    descripcionRespuestaWs = jaxbEntelFault.getValue().getCodigoError();
                }

            } catch (IOException e1) {
                throw new IllegalArgumentException("cannot unmarshal SOAP fault detail object: " + soapFaultDetail.getSource());
            }
        }

        codigoRespuestWs = "0000"; //eliminar
        s.setCodigorpta(codigoRespuestWs);
        s.setDescripcionrpta(descripcionRespuestaWs);

        logger.info("RESERVA Codigo -> [ " + codigoRespuestWs + " ]");
        logger.info("RESERVA Mensaje -> [ " + StringUtils.left(descripcionRespuestaWs, 150) + " ]");

        if ("0000".equals(codigoRespuestWs)) {
            if (ejecutarCobroResp.getResponseData() != null) {
                String paymentTransactionId = ejecutarCobroResp.getResponseData().getIdtransacccion();
                s.setPaymentTransactionId(paymentTransactionId);
                ejecucionCobro.setEstadocobro("Reservado");
                logger.info("RESERVA ... [ OK ]");
            }
        } else {
            logger.info("RESERVA ... [ FALLO ]");
        }

        ejecucionCobro.setSuscripcion(s);
        ejecucionCobro.setWscodrpta(codigoRespuestWs);
        ejecucionCobro.setWsdescripcionrpta(descripcionRespuestaWs);
        ejecucionCobro.setServicioejec("reserva");
        ejecucionCobro.setWsejecucion(new Date());

    }

    public void ejecutarCobroConfirmacionWs(Suscripcion s) throws Exception {

        com.oracle.xmlns.carrierbilling.bpel_gestioncobroconfirmacion.ObjectFactory factory1
                = new com.oracle.xmlns.carrierbilling.bpel_gestioncobroconfirmacion.ObjectFactory();
        EjecutarCobroConfirmacionRequestType request = factory1.createEjecutarCobroConfirmacionRequestType();

        pe.com.entel.soa.data.servicio.carrierbilling.v1.ObjectFactory factory2
                = new pe.com.entel.soa.data.servicio.carrierbilling.v1.ObjectFactory();

        CarrierBillingCobroConfirmacionType cbCobroType = factory2.createCarrierBillingCobroConfirmacionType();
        cbCobroType.setRequestId(s.getRequestId());
        cbCobroType.setBangoTransactionId(s.getBangoTransactionId());
        cbCobroType.setMerchantTransactionId(s.getMerchantTransactionId());
        cbCobroType.setUserId(s.getIdCliente());
        cbCobroType.setAmount(String.valueOf(s.getMontoCobro()));
        cbCobroType.setCurrency(s.getCurrency());
        cbCobroType.setPaymentProviderTransactionId(s.getPaymentTransactionId());

        request.setDatosCobro(cbCobroType);

        SimpleDateFormat trxFormat = new SimpleDateFormat(TRANSACCION_DATE_FORMAT);
        String todayTrxFormat = trxFormat.format(new Date());

        SimpleDateFormat fechaInicioFormat = new SimpleDateFormat(FECHAINICIO_DATE_FORMAT);
        String todayfechaInicioFormat = fechaInicioFormat.format(new Date());

        logger.debug("URI: " + cobroConfirmacionWsTemplate.getDefaultUri());

        String codigoRespuestWs = null;
        String descripcionRespuestaWs = null;

        try {

            cobroConfirmacionWsTemplate.setInterceptors(new ClientInterceptor[]{interceptor});

            EjecutarCobroConfirmacionResponseType response = (EjecutarCobroConfirmacionResponseType) cobroConfirmacionWsTemplate.marshalSendAndReceive(request,
                    new BpelHeaderMessageCallBack(s.getCanal(), idAplicacion, userId,
                            todayTrxFormat, todayTrxFormat, todayfechaInicioFormat));

            codigoRespuestWs = response.getResponseStatus().getCodigoRespuesta();
            descripcionRespuestaWs = response.getResponseStatus().getDescripcionRespuesta();

        } catch (SoapFaultClientException e) {
            logger.debug("e.getFaultCode() :" + e.getFaultCode());
            logger.debug("e.getSoapFault() :" + e.getSoapFault());
            logger.debug("e.getFaultCode().getFaultDetail() :" + e.getSoapFault().getFaultDetail());
            logger.debug("e.getFaultCode().getFaultDetail().getResult() :" + e.getSoapFault().getFaultDetail().getResult());
            logger.debug("e.getFaultStringOrReason() :" + e.getFaultStringOrReason());

            SoapFaultDetail soapFaultDetail = e.getSoapFault().getFaultDetail();

            if (soapFaultDetail == null) {
                throw e;
            }
            SoapFaultDetailElement detailElementChild = (SoapFaultDetailElement) soapFaultDetail.getDetailEntries().next();
            Source detailSource = detailElementChild.getSource();

            logger.debug("detailElementChild.getSource() :" + detailElementChild.getSource());

            try {
                JAXBElement<EntelFault> jaxbEntelFault = (JAXBElement<EntelFault>) cobroConfirmacionWsTemplate.getUnmarshaller().unmarshal(detailSource);

                if (jaxbEntelFault != null) {
                    logger.debug("entelFault :" + jaxbEntelFault.getValue());
                    codigoRespuestWs = jaxbEntelFault.getValue().getCodigoError();
                    descripcionRespuestaWs = jaxbEntelFault.getValue().getCodigoError();
                }

            } catch (IOException e1) {
                throw new IllegalArgumentException("cannot unmarshal SOAP fault detail object: " + soapFaultDetail.getSource());
            }
        }

        logger.info("CONFIRMACIOM Codigo -> [ " + codigoRespuestWs + " ]");
        logger.info("CONFIRMACION Mensaje -> [ " + StringUtils.left(descripcionRespuestaWs, 150) + " ]");

        codigoRespuestWs = "0000";//eliminar
        s.setCodigorpta(codigoRespuestWs);
        s.setDescripcionrpta(descripcionRespuestaWs);

        if ("0000".equals(codigoRespuestWs)) {
            ejecucionCobro.setEstadocobro("Cobrado");
            logger.info("CONFIRMACIOM ... [ OK ]");
        } else {
            logger.info("CONFIRMACIOM ... [ FALLO ]");
        }

        ejecucionCobro.setSuscripcion(s);
        ejecucionCobro.setWscodrpta(codigoRespuestWs);
        ejecucionCobro.setWsdescripcionrpta(descripcionRespuestaWs);
        ejecucionCobro.setServicioejec("confirmacion");
        ejecucionCobro.setWsejecucion(new Date());
    }

    public void ejecutarActualizarSuscripcion(Suscripcion s) throws Exception {
    	logger.debug("Inicio ejecutarActualizarSuscripcion");
    	EjecucionActualizacion ejecucionActualizacion = actualizarSuscripcionService.ejecutar(s);
    	
        s.setCodigorpta(ejecucionActualizacion.getCodrpta());
        s.setDescripcionrpta(ejecucionActualizacion.getDescripcionrpta());

        ejecucionCobro.setSuscripcion(s);
        ejecucionCobro.setWscodrpta(ejecucionActualizacion.getCodrpta());
        ejecucionCobro.setWsdescripcionrpta(ejecucionActualizacion.getDescripcionrpta());
        
        logger.info("ACTUALIZAR SUSCRIPCION Codigo -> [ " + ejecucionActualizacion.getCodrpta() + " ]");
        logger.info("ACTUALIZAR SUSCRIPCION Mensaje -> [ " + StringUtils.left(ejecucionActualizacion.getDescripcionrpta(), 150) + " ]");
        
        if(TIPO_CANCELACION.equals(StringUtils.upperCase(s.getTipoEjecucion()))) {
        	ejecucionCobro.setServicioejec(TIPO_CANCELACION);
        	logger.debug("Tipo Cancelacion");
        }else {
        	ejecucionCobro.setServicioejec("confirmacion");
        	logger.debug("Tipo Degradacion");
        }
        ejecucionCobro.setWsejecucion(new Date());
        logger.debug("Fin ejecutarActualizarSuscripcion");
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
                        = new StringBuilder("<v1:headerRequest xmlns:v1=\"http://entel.com.pe/soa/data/generico/entelGenericHeader/v1/\">").append("\n");
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

    public class SoapClientInterceptor implements ClientInterceptor {

        private String httpResponseCode;
        private String httpMessage;

        @Override
        public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
            return false;
        }

        @Override
        public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {


            String[] props = messageContext.getPropertyNames();
            logger.debug("Properties Response: ");
            for (String prop : props) {
                logger.debug("-> " + prop);
            }

            httpResponseCode = String.valueOf(messageContext.getProperty(SOAPMessageContext.HTTP_RESPONSE_CODE));
            httpMessage = String.valueOf(messageContext.getProperty(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY));

            logger.debug("handleResponse httpResponseCode: " + httpResponseCode);
            logger.debug("handleResponse httpMessage: " + httpMessage);
            return false;
        }

        @Override
        public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {

            String[] props = messageContext.getPropertyNames();
            logger.debug("Properties Response: ");
            for (String prop : props) {
                logger.debug("-> " + prop);
            }

            httpResponseCode = String.valueOf(messageContext.getProperty(SOAPMessageContext.HTTP_RESPONSE_CODE));
            httpMessage = String.valueOf(messageContext.getProperty(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY));

            logger.debug("handleFault httpResponseCode: " + httpResponseCode);
            logger.debug("handleFault httpMessage: " + httpMessage);
            return false;
        }

        public String getHttpResponseCode() {
            return httpResponseCode;
        }

        public void setHttpResponseCode(String httpResponseCode) {
            this.httpResponseCode = httpResponseCode;
        }

        public String getHttpMessage() {
            return httpMessage;
        }

        public void setHttpMessage(String httpMessage) {
            this.httpMessage = httpMessage;
        }
    }

	public ActualizarSuscripcionService getActualizarSuscripcionService() {
		return actualizarSuscripcionService;
	}

	public void setActualizarSuscripcionService(ActualizarSuscripcionService actualizarSuscripcionService) {
		this.actualizarSuscripcionService = actualizarSuscripcionService;
	}

	public InsertErrorCancelacionStoreProcedure getProcedureError() {
		return procedureError;
	}

	public void setProcedureError(InsertErrorCancelacionStoreProcedure procedureError) {
		this.procedureError = procedureError;
	}
    
    

}
