package pe.com.entel.aplicacion.carrierbilling.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import pe.com.entel.aplicacion.carrierbilling.domain.ApiManagementError;
import pe.com.entel.aplicacion.carrierbilling.domain.HeaderRequest;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.domain.Token;
import pe.com.entel.aplicacion.carrierbilling.domain.TokenError;
import pe.com.entel.aplicacion.carrierbilling.exception.ApiManagementException;
import pe.com.entel.aplicacion.carrierbilling.repository.InsertErrorCancelacionStoreProcedure;

public class LimpiarCacheService {

	static Logger logger = Logger.getLogger(ActualizarSuscripcionService.class);

	private String tokenUrl;
	private String tokenMetodo;
	private String limpiarCacheUrl;
	private String limpiarCacheMetodo;
	private String tokenHeader1;
	private String limpiarCacheHeader1;
	private String limpiarCacheHeader2;
	private String limpiarCacheHeader3;
	private InsertErrorCancelacionStoreProcedure procedureError;

	public LimpiarCacheService(String tokenUrl, String tokenMetodo, String limpiarCacheUrl,
			String limpiarCacheMetodo, String tokenHeader1, String limpiarCacheHeader1, String limpiarCacheHeader2,
			String limpiarCacheHeader3, String modificadoPor) {

		logger.debug("tokenUrl: " + tokenUrl);
		logger.debug("tokenMetodo: " + tokenMetodo);
		logger.debug("limpiarCacheUrl: " + limpiarCacheUrl);
		logger.debug("limpiarCacheMetodo: " + limpiarCacheMetodo);
		logger.debug("tokenHeader1: " + limpiarCacheHeader1);
		logger.debug("tokenHeader1: " + limpiarCacheHeader2);
		logger.debug("tokenHeader1: " + limpiarCacheHeader3);
		logger.debug("modificadoPor: " + modificadoPor);

		this.tokenUrl = tokenUrl;
		this.tokenMetodo = tokenMetodo;
		this.limpiarCacheUrl = limpiarCacheUrl;
		this.limpiarCacheMetodo = limpiarCacheMetodo;
		this.tokenHeader1 = tokenHeader1;
		this.limpiarCacheHeader1 = limpiarCacheHeader1;
		this.limpiarCacheHeader2 = limpiarCacheHeader2;
		this.limpiarCacheHeader3 = limpiarCacheHeader3;

	}

	public void ejecutar(Suscripcion s) throws Exception {
		
		try {
			logger.info("Actualizando suscripcion [ " + s.getIdSuscripcion() + " ] ....");
			ArrayList<HeaderRequest> listaHeadersToken = obtenerHeadersToken();
			String tokenJson = this.invocarRest(tokenUrl, tokenMetodo, listaHeadersToken, null);
			logger.debug("tokenJson : " + tokenJson);
			Gson gson = new Gson();
			Token token = gson.fromJson(tokenJson, Token.class);
			logger.info("Token concedido [ " + token.getAccess_token() + " ] ");

			ArrayList<HeaderRequest> listaHeadersLimpiarCache = obtenerHeadersLimpiarCache(token.getAccess_token());
			this.invocarRest(limpiarCacheUrl, limpiarCacheMetodo, listaHeadersLimpiarCache,
					s.getShareAccountId());
			logger.info("Actualizando suscripcion ...");
			logger.info("Limpiar cache de suscripcion [ " + s.getIdSuscripcion() + " ] ejecutada correctamente!");
			
		} catch (ApiManagementException e) {
			procedureError.run(e.getError(), s.getIdSuscripcion());
			logger.info("Actualizacion de suscripcion [ " + s.getIdSuscripcion() + " ] con FALLO: " + e.getError());
		}

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

	private String invocarRest(String cadenaUrl, String metodo, ArrayList<HeaderRequest> listaCabecera,
			String shareAccountId) throws ApiManagementException {
		String output = "";
		StringBuilder sb = new StringBuilder();
		ApiManagementError apiManagementError = new ApiManagementError();
		apiManagementError.setServicio(cadenaUrl);
		try {
			URL url = new URL(cadenaUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(metodo);
			conn.setRequestProperty("Accept", "application/json");
			if (shareAccountId != null) {
				conn.setRequestProperty("sharedAccountId", shareAccountId);
			}
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

	private ArrayList<HeaderRequest> obtenerHeadersLimpiarCache(String accesToken) {
		ArrayList<HeaderRequest> listaHeaders = new ArrayList<HeaderRequest>();

		HeaderRequest header1 = new HeaderRequest();
		String[] listaHeader1 = this.limpiarCacheHeader1.split(",");
		header1.setNombre(listaHeader1[0]);
		header1.setValor(listaHeader1[1]);
		listaHeaders.add(header1);

		HeaderRequest header2 = new HeaderRequest();
		String[] listaHeader2 = this.limpiarCacheHeader2.split(",");
		header2.setNombre(listaHeader2[0]);
		header2.setValor(listaHeader2[1]);
		listaHeaders.add(header2);

		HeaderRequest header3 = new HeaderRequest();
		String[] listaHeader3 = this.limpiarCacheHeader3.split(",");
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

	public InsertErrorCancelacionStoreProcedure getProcedureError() {
		return procedureError;
	}

	public void setProcedureError(InsertErrorCancelacionStoreProcedure procedureError) {
		this.procedureError = procedureError;
	}

}
