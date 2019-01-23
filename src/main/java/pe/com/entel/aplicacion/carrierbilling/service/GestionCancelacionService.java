package pe.com.entel.aplicacion.carrierbilling.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import pe.com.entel.aplicacion.carrierbilling.domain.ApiManagementError;
import pe.com.entel.aplicacion.carrierbilling.domain.HeaderRequest;
import pe.com.entel.aplicacion.carrierbilling.domain.Token;
import pe.com.entel.aplicacion.carrierbilling.domain.TokenError;
import pe.com.entel.aplicacion.carrierbilling.exception.ApiManagementException;
import pe.com.entel.aplicacion.carrierbilling.exception.ApiManagementInactiveException;

public class GestionCancelacionService {

	static Logger logger = Logger.getLogger(GestionCancelacionService.class);

	private String tokenUrl;
	private String tokenMetodo;
	private String cancelarUrl;
	private String cancelarMetodo;
	private String tokenHeader1;
	private String cancelarHeader1;
	private String cancelarHeader2;
	private String cancelarHeader3;
	private String codigosError;

	public GestionCancelacionService(String tokenUrl, String tokenMetodo, String cancelarUrl, String cancelarMetodo,
			String tokenHeader1, String cancelarHeader1, String cancelarHeader2, String cancelarHeader3, String codigosError) {

		logger.debug("tokenUrl: " + tokenUrl);
		logger.debug("tokenMetodo: " + tokenMetodo);
		logger.debug("cancelarUrl: " + cancelarUrl);
		logger.debug("cancelarMetodo: " + cancelarMetodo);
		logger.debug("tokenHeader1: " + tokenHeader1);
		logger.debug("cancelarHeader1: " + cancelarHeader1);
		logger.debug("cancelarHeader2: " + cancelarHeader2);
		logger.debug("cancelarHeader3: " + cancelarHeader3);
		logger.debug("codigosError: " + codigosError);
		
		this.tokenUrl = tokenUrl;
		this.tokenMetodo = tokenMetodo;
		this.cancelarUrl = cancelarUrl;
		this.cancelarMetodo = cancelarMetodo;
		this.tokenHeader1 = tokenHeader1;
		this.cancelarHeader1 = cancelarHeader1;
		this.cancelarHeader2 = cancelarHeader2;
		this.cancelarHeader3 = cancelarHeader3;
		this.codigosError = codigosError;

	}

	public String ejecutar(String shareAccountId) throws ApiManagementException, ApiManagementInactiveException {

		logger.info("Inicio rest Token");
		ArrayList<HeaderRequest> listaHeadersToken = obtenerHeadersToken();
		String tokenJson = this.invocarRest(tokenUrl, tokenMetodo, listaHeadersToken);
		logger.debug("tokenJson : " + tokenJson);
		Gson gson = new Gson();
		Token token = gson.fromJson(tokenJson, Token.class);
		logger.debug("getAcces_token : " + token.getAccess_token());
		logger.info("Fin rest Token");
		logger.info("Inicio Rest Cancelar");
		String urlTerminar = MessageFormat.format(cancelarUrl, shareAccountId);
		ArrayList<HeaderRequest> listaHeadersCancel = obtenerHeadersTerminate(token.getAccess_token());
		String terminarJson = this.invocarRest(urlTerminar, cancelarMetodo, listaHeadersCancel);
		logger.info("FIN Rest Cancelar");
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
			logger.debug("conn.getResponseCode() : " + conn.getResponseCode());
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
				
				String[] listaCodigosError = this.codigosError.split(",");
				for (String codigoError: listaCodigosError) {           
				    if(tokenError.getResult().getCode().equals(codigoError)) {
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
		} catch (Exception e) {
			logger.info("Respuesta no esperada : " + e.getMessage());
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
		String[] listaHeader1 = this.cancelarHeader1.split(",");
		header1.setNombre(listaHeader1[0]);
		header1.setValor(listaHeader1[1]);
		listaHeaders.add(header1);

		HeaderRequest header2 = new HeaderRequest();
		String[] listaHeader2 = this.cancelarHeader2.split(",");
		header2.setNombre(listaHeader2[0]);
		header2.setValor(listaHeader2[1]);
		listaHeaders.add(header2);

		HeaderRequest header3 = new HeaderRequest();
		String[] listaHeader3 = this.cancelarHeader3.split(",");
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
}
