package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import pe.com.entel.aplicacion.carrierbilling.domain.ActualizarCancelacionSp;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaCancelacionStoreProcedure;
import pe.com.entel.aplicacion.carrierbilling.service.GestionCancelacionService;

public class ProcesoCancelacionWriter implements ItemWriter<Suscripcion> {

	static Logger logger = Logger.getLogger(ProcesoCancelacionWriter.class);

	private ActualizaCancelacionStoreProcedure procedure;
	private GestionCancelacionService service;
	
	@Override
	public void write(List<? extends Suscripcion> list) throws Exception {
		logger.debug("Entro write");
		for (Suscripcion s : list) {
			
			logger.debug("API terminar" + s);
			service.ejecutar(s.getShareAccountId());
			
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

	public GestionCancelacionService getService() {
		return service;
	}

	public void setService(GestionCancelacionService service) {
		this.service = service;
	}

}
