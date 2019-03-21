package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.service.ActualizarSuscripcionService;
import pe.com.entel.aplicacion.carrierbilling.service.GestionTerminacionService;

public class ProcesoActualizacionProgWriter implements ItemWriter<Suscripcion> {

	static Logger logger = Logger.getLogger(ProcesoActualizacionProgWriter.class);
	private static final String CANAL_SPOTIFY = "Spotify";
	private GestionTerminacionService service;
	private ActualizarSuscripcionService actualizarSuscripcionService;

	@Override
	public void write(List<? extends Suscripcion> list) throws Exception {

		logger.debug("Total de registros: " + list.size());

		for (Suscripcion s : list) {
			logger.debug("Suscripcion: " + s);

			if (CANAL_SPOTIFY.equals(s.getCanal())) {
				service.ejecutar(s);
			} else {
				actualizarSuscripcionService.ejecutar(s);
			}
		}
	}

	public GestionTerminacionService getService() {
		return service;
	}

	public void setService(GestionTerminacionService service) {
		this.service = service;
	}

	public ActualizarSuscripcionService getActualizarSuscripcionService() {
		return actualizarSuscripcionService;
	}

	public void setActualizarSuscripcionService(ActualizarSuscripcionService actualizarSuscripcionService) {
		this.actualizarSuscripcionService = actualizarSuscripcionService;
	}

}
