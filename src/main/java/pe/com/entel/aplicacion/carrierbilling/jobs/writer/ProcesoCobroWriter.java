package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaCobroSp;
import pe.com.entel.aplicacion.carrierbilling.domain.EjecucionCobro;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaCobroStoreProcedure;

import javax.batch.runtime.StepExecution;
import java.util.List;

/**
 * @version 1.0, 17/12/2018
 * @autor jsegovia
 */
public class ProcesoCobroWriter implements ItemWriter<EjecucionCobro>, ItemStreamWriter<EjecucionCobro> {

    static Logger logger = Logger.getLogger(ProcesoCobroWriter.class);

    private ActualizaCobroStoreProcedure procedure;

    private int suscripcionOk = 0;

    private int suscripcionError = 0;

    private StepExecution stepExecution;

    @Override
    public void write(List<? extends EjecucionCobro> list) throws Exception {

        for (EjecucionCobro e : list) {

            Suscripcion suscripcion = e.getSuscripcion();

            logger.debug("Ejecutando write: " + e.getWscodrpta() + " IdSuscripcion: " + suscripcion.getIdSuscripcion());

            ActualizaCobroSp o = new ActualizaCobroSp();

            if ("0000".equals(suscripcion.getCodigorpta())) {
                o.setEstado("activa");
                o.setEstadocobro("Cobrado");
                suscripcionOk++;
                logger.debug("IdSuscripcion: " + suscripcion.getIdSuscripcion() + " ejecutada OK ");
            } else {
                o.setEstado("pendiente");
                o.setEstadocobro("Pendiente");
                suscripcionError++;
                logger.debug("IdSuscripcion: " + suscripcion.getIdSuscripcion() + " ejecutada con ERROR ");
            }

            o.setIdsuscripcion(suscripcion.getIdSuscripcion());
            o.setIdbillcontrol(suscripcion.getBillControl());
            o.setWscodrpta(e.getWscodrpta());
            o.setWsdescripcionrpta(e.getWsdescripcionrpta());
            o.setWsejecucion(e.getWsejecucion());
            o.setWshttpstatus(e.getWshttpstatus());
            o.setServicioejec(e.getServicioejec());

            ActualizaCobroSp resp = procedure.run(o);

            if (!"0000".equals(resp.getCodigorpta())) {
                throw new Exception("Error en el procedure");
            }
        }
    }

    public ActualizaCobroStoreProcedure getProcedure() {
        return procedure;
    }

    public void setProcedure(ActualizaCobroStoreProcedure procedure) {
        this.procedure = procedure;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {

        int ok = executionContext.getInt("suscripcion_ok");
        int error = executionContext.getInt("suscripcion_error");

        int sumOk = ok + suscripcionOk;
        int sumError = error + suscripcionError;

        logger.debug("Cuenta cobros OK = " + sumOk);
        logger.debug("Cuenta cobros ERROR = " + sumError);

        executionContext.putInt("suscripcion_ok", sumOk);
        executionContext.putInt("suscripcion_error", sumError);
    }

    @Override
    public void close() throws ItemStreamException {

    }

    public StepExecution getStepExecution() {
        return stepExecution;
    }

    public void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
}
