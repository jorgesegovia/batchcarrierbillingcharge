package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.service.GestionCancelacionService;

public class ProcesoCancelacionWriter implements ItemWriter<Suscripcion> {

	static Logger logger = Logger.getLogger(ProcesoCancelacionWriter.class);

	private GestionCancelacionService service;

	@Override
	public void write(List<? extends Suscripcion> list) throws Exception {
		for (Suscripcion s : list) {
			logger.info("API terminar" + s);
			service.ejecutar(s);
		}
	}

	public GestionCancelacionService getService() {
		return service;
	}

	public void setService(GestionCancelacionService service) {
		this.service = service;
	}

}
