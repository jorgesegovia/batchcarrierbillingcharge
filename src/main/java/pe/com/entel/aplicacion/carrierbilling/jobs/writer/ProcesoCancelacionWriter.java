package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import com.google.gson.Gson;

import pe.com.entel.aplicacion.carrierbilling.domain.ActualizarCancelacionSp;
import pe.com.entel.aplicacion.carrierbilling.domain.HeaderRequest;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.domain.Token;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaCancelacionStoreProcedure;

public class ProcesoCancelacionWriter implements ItemWriter<Suscripcion> {

	static Logger logger = Logger.getLogger(ProcesoCancelacionWriter.class);

	private ActualizaCancelacionStoreProcedure procedure;

	@Override
	public void write(List<? extends Suscripcion> list) throws Exception {
		logger.debug("Entro write");
		for (Suscripcion s : list) {
			
			logger.debug("API terminar" + s);
			this.terminar(s.getShareAccountId());
			
			logger.debug("ActualizaCobroStoreProcedure: " + procedure);
			ActualizarCancelacionSp o = new ActualizarCancelacionSp();
			o.setIdsuscripcion(s.getIdSuscripcion());
			ActualizarCancelacionSp resp = procedure.run(o);

			if (!"0000".equals(resp.getCodigorpta())) {
				logger.debug("Suscripcion: " + s);
				throw new Exception("Error en el procedure");
			}
		}
	}

	public ActualizaCancelacionStoreProcedure getProcedure() {
		return procedure;
	}

	public void setProcedure(ActualizaCancelacionStoreProcedure procedure) {
		this.procedure = procedure;
	}

	private void terminar(String shareAccountId) {
		
		String metodo = "POST";
        String grant_type = "client_credentials";
        String client_id = "l7de421066040f477290c56b5111d23de5";
        String client_secret = "39f72082d7494260bac48d9734a0be4a";
        
        logger.debug("Token");
		String urlToken = "https://apiinternaluat.entel.pe/auth/oauth/v2/token?grant_type=" + grant_type + "&client_id=" + client_id + "&client_secret=" + client_secret;
		
		ArrayList<HeaderRequest> listaHeaders2 = new ArrayList<HeaderRequest>();
		
		HeaderRequest header6 = new HeaderRequest();
		header6.setNombre("Content-Type");
		header6.setValor("application/x-www-form-urlencoded");
		listaHeaders2.add(header6);
		
		
		String tokenJson = this.invocarRest(urlToken, metodo, listaHeaders2);
		logger.debug("tokenJson : " + tokenJson);
		Gson gson = new Gson();
        Token token = gson.fromJson(tokenJson, Token.class);
        logger.debug("getAcces_token : " + token.getAcces_token());
        
		String urlTerminar = "https://apiinternaluat.entel.pe/billing/carrierBilling/v1/subscriptionAccounts/"+shareAccountId+"/cancel";
		
		ArrayList<HeaderRequest> listaHeaders = new ArrayList<HeaderRequest>();
		
		HeaderRequest header1 = new HeaderRequest();
		header1.setNombre("requestTimestamp");
		header1.setValor("");
		listaHeaders.add(header1);
		
		HeaderRequest header2 = new HeaderRequest();
		header2.setNombre("applicationCode");
		header2.setValor("");
		listaHeaders.add(header2);
		
		HeaderRequest header3 = new HeaderRequest();
		header3.setNombre("countryCode");
		header3.setValor("PER");
		listaHeaders.add(header3);
		
		HeaderRequest header4 = new HeaderRequest();
		header4.setNombre("consumerId");
		header4.setValor("");
		listaHeaders.add(header4);
		
		HeaderRequest header5 = new HeaderRequest();
		header5.setNombre("Authorization");
		header5.setValor("Bearer " + token.getAcces_token());
		listaHeaders.add(header5);
		
		logger.debug("Terminar");
		String terminarJson = this.invocarRest(urlTerminar, metodo, listaHeaders);
	}
	
	private String invocarRest(String cadenaUrl, String metodo, ArrayList<HeaderRequest> listaCabecera) {
		String output = "";
		try {
			URL url = new URL(cadenaUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(metodo);
			conn.setRequestProperty("Accept", "application/json");
			logger.debug("cadenaUrl : " + cadenaUrl);
			if( listaCabecera!=null && !listaCabecera.isEmpty()) {
				logger.debug("listaCabecera : " + listaCabecera);
				for(HeaderRequest header : listaCabecera) {
					logger.debug("header.getNombre() : " + header.getNombre());
					logger.debug("header.getValor() : " + header.getValor());					
					conn.setRequestProperty(header.getNombre(), header.getValor());					
				}
			}
			logger.debug("conn.getResponseCode() : " + conn.getResponseCode());
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			logger.debug("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				logger.debug(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			logger.debug("error" , e);
		} catch (IOException e) {
			logger.debug("error" , e);
		} catch(Exception e) {
			logger.debug("error" , e);
		}
		return output;
	}
}
