package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import pe.com.entel.aplicacion.carrierbilling.domain.ActualizacionProgramada;
import pe.com.entel.aplicacion.carrierbilling.service.ActualizarSuscripcionService;

public class ProcesoActualizacionProgWriter implements ItemWriter<ActualizacionProgramada> {

	static Logger logger = Logger.getLogger(ProcesoActualizacionProgWriter.class);
	private ActualizarSuscripcionService actualizarSuscripcionService;

	@Override
	public void write(List<? extends ActualizacionProgramada> list) throws Exception {

		logger.debug("Total de registros: " + list.size());

		for (ActualizacionProgramada s : list) {
			logger.debug("ActualizacionProgramada: " + s);
			actualizarSuscripcionService.ejecutar(s);
		}
	}

	public ActualizarSuscripcionService getActualizarSuscripcionService() {
		return actualizarSuscripcionService;
	}

	public void setActualizarSuscripcionService(ActualizarSuscripcionService actualizarSuscripcionService) {
		this.actualizarSuscripcionService = actualizarSuscripcionService;
	}

}
