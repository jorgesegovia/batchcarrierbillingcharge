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
import pe.com.entel.aplicacion.carrierbilling.exception.ApiManagementException;

public class GestionCancelacionService {

	static Logger logger = Logger.getLogger(GestionCancelacionService.class);

	private String tokenUrl;
	private String tokenMetodo;
	private String terminarUrl;
	private String terminarMetodo;
	
	public GestionCancelacionService(String tokenUrl, String tokenMetodo, String terminarUrl, String terminarMetodo) {

		logger.debug("tokenUrl: " + tokenUrl);
		logger.debug("tokenMetodo: " + tokenMetodo);
		logger.debug("terminarUrl: " + terminarUrl);
		logger.debug("terminarMetodo: " + terminarMetodo);

		this.tokenUrl = tokenUrl;
		this.tokenMetodo = tokenMetodo;
		this.terminarUrl = terminarUrl;
		this.terminarMetodo = terminarMetodo;
		
	}

	public String ejecutar(String shareAccountId) throws ApiManagementException{

		logger.debug("Token");
		ArrayList<HeaderRequest> listaHeadersToken = obtenerHeadersToken();
		String tokenJson = this.invocarRest(tokenUrl, tokenMetodo, listaHeadersToken);
		logger.debug("tokenJson : " + tokenJson);
		Gson gson = new Gson();
		Token token = gson.fromJson(tokenJson, Token.class);
		logger.debug("getAcces_token : " + token.getAccess_token());

		logger.debug("Terminar");
		String urlTerminar = MessageFormat.format(terminarUrl, shareAccountId);
		ArrayList<HeaderRequest> listaHeadersTerminate = obtenerHeadersTerminate(token.getAccess_token());
		String terminarJson = this.invocarRest(urlTerminar, terminarMetodo, listaHeadersTerminate);
		return terminarJson;
	}

	private String invocarRest(String cadenaUrl, String metodo, ArrayList<HeaderRequest> listaCabecera) throws ApiManagementException {
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
			
			apiManagementError.setCodigoHttp(conn.getResponseCode());
			
			if (conn.getResponseCode() != 200) {
				throw new ApiManagementException(apiManagementError);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			logger.debug("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			logger.debug(sb.toString());
			conn.disconnect();
		} catch (Exception e) {
			logger.debug("error", e);
			apiManagementError.setDescripcionError(e.getMessage());
			throw new ApiManagementException(apiManagementError);
		}
		return sb.toString();
	}
	
	private ArrayList<HeaderRequest> obtenerHeadersToken(){
		ArrayList<HeaderRequest> listaHeaders = new ArrayList<HeaderRequest>();
		HeaderRequest header = new HeaderRequest();
		header.setNombre("Content-Type");
		header.setValor("application/x-www-form-urlencoded");
		listaHeaders.add(header);
		return listaHeaders;
	}
	
	private ArrayList<HeaderRequest> obtenerHeadersTerminate(String accesToken){
		ArrayList<HeaderRequest> listaHeaders = new ArrayList<HeaderRequest>();

		HeaderRequest header1 = new HeaderRequest();
		header1.setNombre("requestTimestamp");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String fecha = dateFormat.format(new Date()) + "-05:00";
		System.out.println(fecha);

		header1.setValor(fecha);
		listaHeaders.add(header1);

		HeaderRequest header2 = new HeaderRequest();
		header2.setNombre("applicationCode");
		header2.setValor("Spotify");
		listaHeaders.add(header2);

		HeaderRequest header3 = new HeaderRequest();
		header3.setNombre("countryCode");
		header3.setValor("PER");
		listaHeaders.add(header3);

		HeaderRequest header4 = new HeaderRequest();
		header4.setNombre("consumerId");
		header4.setValor("123");
		listaHeaders.add(header4);

		HeaderRequest header5 = new HeaderRequest();
		header5.setNombre("Authorization");
		header5.setValor("Bearer " + accesToken);
		listaHeaders.add(header5);
		return listaHeaders;
	}
}
