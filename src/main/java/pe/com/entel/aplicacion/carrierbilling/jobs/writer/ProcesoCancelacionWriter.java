package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import pe.com.entel.aplicacion.carrierbilling.domain.ActualizarCancelacionSp;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.exception.ApiManagementException;
import pe.com.entel.aplicacion.carrierbilling.exception.ApiManagementInactiveException;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaCancelacionStoreProcedure;
import pe.com.entel.aplicacion.carrierbilling.repository.InsertErrorCancelacionStoreProcedure;
import pe.com.entel.aplicacion.carrierbilling.service.GestionCancelacionService;

public class ProcesoCancelacionWriter implements ItemWriter<Suscripcion> {

	static Logger logger = Logger.getLogger(ProcesoCancelacionWriter.class);

	private ActualizaCancelacionStoreProcedure procedure;
	private InsertErrorCancelacionStoreProcedure procedureError;
	private GestionCancelacionService service;

	@Override
	public void write(List<? extends Suscripcion> list) throws Exception {
		for (Suscripcion s : list) {

			logger.info("API terminar" + s);
			try {
				service.ejecutar(s.getShareAccountId());
				actualizarCancelacion(s);
			} catch (ApiManagementException e) {
				procedureError.run(e.getError());
			} catch (ApiManagementInactiveException e) {
				procedureError.run(e.getError());
				actualizarCancelacion(s);
			}

		}
	}

	private void actualizarCancelacion(Suscripcion s) throws Exception {
		logger.info("ActualizaCobroStoreProcedure: " + procedure);
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

	public GestionCancelacionService getService() {
		return service;
	}

	public void setService(GestionCancelacionService service) {
		this.service = service;
	}

	public InsertErrorCancelacionStoreProcedure getProcedureError() {
		return procedureError;
	}

	public void setProcedureError(InsertErrorCancelacionStoreProcedure procedureError) {
		this.procedureError = procedureError;
	}

}
