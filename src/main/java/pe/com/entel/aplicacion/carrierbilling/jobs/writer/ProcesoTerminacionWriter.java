package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.service.GestionTerminacionService;

/**
 * @version 1.0, 23/01/2019
 * @autor wsotomayor
 */
public class ProcesoTerminacionWriter implements ItemWriter<Suscripcion> {

	static Logger logger = Logger.getLogger(ProcesoCancelacionWriter.class);
	private GestionTerminacionService service;


	@Override
	public void write(List<? extends Suscripcion> list) throws Exception {

		logger.debug("Total de registros: " + list.size());

		for (Suscripcion s : list) {
			logger.debug("Suscripcion: " + s);

			service.ejecutar(s);
		}
	}

	public GestionTerminacionService getService() {
		return service;
	}

	public void setService(GestionTerminacionService service) {
		this.service = service;
	}

}