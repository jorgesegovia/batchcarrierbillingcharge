package pe.com.entel.aplicacion.carrierbilling.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import pe.com.entel.aplicacion.carrierbilling.domain.ActualizarCancelacionSp;
import pe.com.entel.aplicacion.carrierbilling.domain.ApiManagementError;
import pe.com.entel.aplicacion.carrierbilling.domain.HeaderRequest;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.domain.Token;
import pe.com.entel.aplicacion.carrierbilling.domain.TokenError;
import pe.com.entel.aplicacion.carrierbilling.exception.ApiManagementException;
import pe.com.entel.aplicacion.carrierbilling.exception.ApiManagementInactiveException;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaCancelacionStoreProcedure;
import pe.com.entel.aplicacion.carrierbilling.repository.InsertErrorCancelacionStoreProcedure;

public class GestionTerminacionService {

	static Logger logger = Logger.getLogger(GestionCancelacionService.class);

	private String tokenUrl;
	private String tokenMetodo;
	private String terminarUrl;
	private String terminarMetodo;
	private String tokenHeader1;
	private String terminarHeader1;
	private String terminarHeader2;
	private String terminarHeader3;
	private String codigosError;
	private ActualizaCancelacionStoreProcedure procedure;
	private InsertErrorCancelacionStoreProcedure procedureError;

	public GestionTerminacionService(String tokenUrl, String tokenMetodo, String terminarUrl, String terminarMetodo,
			String tokenHeader1, String terminarHeader1, String terminarHeader2, String terminarHeader3,
			String codigosError) {

		logger.debug("tokenUrl: " + tokenUrl);
		logger.debug("tokenMetodo: " + tokenMetodo);
		logger.debug("terminarUrl: " + terminarUrl);
		logger.debug("terminarMetodo: " + terminarMetodo);
		logger.debug("tokenHeader1: " + tokenHeader1);
		logger.debug("terminarHeader1: " + terminarHeader1);
		logger.debug("terminarHeader2: " + terminarHeader2);
		logger.debug("terminarHeader3: " + terminarHeader3);
		logger.debug("codigosError: " + codigosError);

		this.tokenUrl = tokenUrl;
		this.tokenMetodo = tokenMetodo;
		this.terminarUrl = terminarUrl;
		this.terminarMetodo = terminarMetodo;
		this.tokenHeader1 = tokenHeader1;
		this.terminarHeader1 = terminarHeader1;
		this.terminarHeader2 = terminarHeader2;
		this.terminarHeader3 = terminarHeader3;
		this.codigosError = codigosError;

	}

	public String ejecutar(Suscripcion s) throws Exception {
		String terminarJson = "";
		try {
			logger.info("Terminando suscripcion [ " + s.getIdSuscripcion() + " ] ....");
			ArrayList<HeaderRequest> listaHeadersToken = obtenerHeadersToken();
			String tokenJson = this.invocarRest(tokenUrl, tokenMetodo, listaHeadersToken);
			logger.debug("tokenJson : " + tokenJson);
			Gson gson = new Gson();
			Token token = gson.fromJson(tokenJson, Token.class);
			logger.info("Token concedido [ " + token.getAccess_token() + " ] ");
			String urlTerminar = MessageFormat.format(terminarUrl, s.getShareAccountId());
			ArrayList<HeaderRequest> listaHeadersTerminate = obtenerHeadersTerminate(token.getAccess_token());
			terminarJson = this.invocarRest(urlTerminar, terminarMetodo, listaHeadersTerminate);
			logger.info("Actualizando suscripcion ...");
			actualizarCancelacion(s);
			logger.info("Terminacion de suscripcion [ " + s.getIdSuscripcion() + " ] ejecutada correctamente!");
		} catch (ApiManagementException e) {
			procedureError.run(e.getError(), s.getIdSuscripcion());
            logger.info("Terminacion de suscripcion [ " + s.getIdSuscripcion() + " ] con FALLO: " + e.getError());
		} catch (ApiManagementInactiveException e) {
			procedureError.run(e.getError(), s.getIdSuscripcion());
			actualizarCancelacion(s);
            logger.info("Terminacion de suscripcion [ " + s.getIdSuscripcion() + " ] con FALLO: " + e.getError());
		}

		return terminarJson;
	}

	private String invocarRest(String cadenaUrl, String metodo, ArrayList<HeaderRequest> listaCabecera)
			throws ApiManagementException, ApiManagementInactiveException {
		String output = "";
		StringBuilder sb = new StringBuilder();
		ApiManagementError apiManagementError = new ApiManagementError();
		apiManagementError.setServicio(cadenaUrl);
		try {
			URL url = new URL(cadenaUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(metodo);
			conn.setRequestProperty("Accept", "application/json");
			logger.debug("cadenaUrl : " + cadenaUrl);
			if (listaCabecera != null && !listaCabecera.isEmpty()) {
				for (HeaderRequest header : listaCabecera) {
					conn.setRequestProperty(header.getNombre(), header.getValor());
				}
			}
            logger.info("HTTP code response : " + conn.getResponseCode());
			if (conn.getResponseCode() != 200) {
				BufferedReader brerror = new BufferedReader(new InputStreamReader((conn.getErrorStream())));

				while ((output = brerror.readLine()) != null) {
					sb.append(output);
				}
				Gson gson = new Gson();
				TokenError tokenError = gson.fromJson(sb.toString(), TokenError.class);
				apiManagementError.setCodigoHttp(conn.getResponseCode());
				apiManagementError.setCodigoError(tokenError.getResult().getCode());
				apiManagementError.setDescripcionError(tokenError.getResult().getDescription());

                logger.info("Codigo Error: " + apiManagementError.getCodigoError());
                logger.info("Descripcion Error: " + apiManagementError.getDescripcionError());

				String[] listaCodigosError = this.codigosError.split(",");
				for (String codigoError : listaCodigosError) {
					if (tokenError.getResult().getCode().equals(codigoError)) {
						throw new ApiManagementInactiveException(apiManagementError);
					}
				}

				throw new ApiManagementException(apiManagementError);
			}

			logger.debug("Output from Server .... \n");
			apiManagementError.setCodigoHttp(conn.getResponseCode());
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			logger.debug(sb.toString());
			conn.disconnect();
		} catch (MalformedURLException e) {
			logger.debug("Respuesta no esperada : " + e.getMessage());
			apiManagementError.setDescripcionError(e.getMessage());
			throw new ApiManagementException(apiManagementError);
		} catch (IOException e) {
			logger.debug("Respuesta no esperada : " + e.getMessage());
			apiManagementError.setDescripcionError(e.getMessage());
			throw new ApiManagementException(apiManagementError);
		}
		return sb.toString();
	}

	private ArrayList<HeaderRequest> obtenerHeadersToken() {
		ArrayList<HeaderRequest> listaHeaders = new ArrayList<HeaderRequest>();
		HeaderRequest header = new HeaderRequest();
		String[] listaHeader = this.tokenHeader1.split(",");
		header.setNombre(listaHeader[0]);
		header.setValor(listaHeader[1]);
		listaHeaders.add(header);
		return listaHeaders;
	}

	private ArrayList<HeaderRequest> obtenerHeadersTerminate(String accesToken) {
		ArrayList<HeaderRequest> listaHeaders = new ArrayList<HeaderRequest>();

		HeaderRequest header1 = new HeaderRequest();
		String[] listaHeader1 = this.terminarHeader1.split(",");
		header1.setNombre(listaHeader1[0]);
		header1.setValor(listaHeader1[1]);
		listaHeaders.add(header1);

		HeaderRequest header2 = new HeaderRequest();
		String[] listaHeader2 = this.terminarHeader2.split(",");
		header2.setNombre(listaHeader2[0]);
		header2.setValor(listaHeader2[1]);
		listaHeaders.add(header2);

		HeaderRequest header3 = new HeaderRequest();
		String[] listaHeader3 = this.terminarHeader3.split(",");
		header3.setNombre(listaHeader3[0]);
		header3.setValor(listaHeader3[1]);
		listaHeaders.add(header3);

		HeaderRequest header4 = new HeaderRequest();
		header4.setNombre("requestTimestamp");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String fecha = dateFormat.format(new Date()) + "-05:00";

		header4.setValor(fecha);
		listaHeaders.add(header4);

		HeaderRequest header5 = new HeaderRequest();
		header5.setNombre("Authorization");
		header5.setValor("Bearer " + accesToken);
		listaHeaders.add(header5);
		return listaHeaders;
	}
	
	private void actualizarCancelacion(Suscripcion s) throws Exception {
		logger.debug("ActualizaCobroStoreProcedure: " + procedure);
		ActualizarCancelacionSp o = new ActualizarCancelacionSp();
		o.setIdsuscripcion(s.getIdSuscripcion());
		ActualizarCancelacionSp resp = procedure.run(o);

		if (!"0000".equals(resp.getCodigorpta())) {
			logger.debug("Suscripcion: " + s);
			throw new Exception("Error en el procedure");
		}
	}

	public ActualizaCancelacionStoreProcedure getProcedure() {
		return procedure;
	}

	public void setProcedure(ActualizaCancelacionStoreProcedure procedure) {
		this.procedure = procedure;
	}

	public InsertErrorCancelacionStoreProcedure getProcedureError() {
		return procedureError;
	}

	public void setProcedureError(InsertErrorCancelacionStoreProcedure procedureError) {
		this.procedureError = procedureError;
	}
}
