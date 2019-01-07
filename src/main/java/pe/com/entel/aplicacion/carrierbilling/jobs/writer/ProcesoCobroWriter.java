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
            } else {
                o.setEstado("pendiente");
                o.setEstadocobro("Pendiente");
                suscripcionError++;
            }

            o.setIdsuscripcion(suscripcion.getIdSuscripcion());
            o.setIdbillcontrol(suscripcion.getBillControl());
            o.setWscodrpta(o.getCodigorpta());
            o.setWsdescripcionrpta(o.getMensaje());
            o.setWsejecucion(o.getWsejecucion());
            o.setWshttpstatus(o.getWshttpstatus());
            o.setServicioejec(o.getServicioejec());

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

        logger.debug("Actual Ok = " + ok);
        logger.debug("Actual Error = " + error);

        int sumOk = ok + suscripcionOk;
        int sumError = ok + suscripcionError;

        logger.debug("Sumando Actual Ok = " + sumOk);
        logger.debug("Sumando Actual Error = " + sumError);

        executionContext.putInt("suscripcion_ok", sumOk);
        executionContext.putInt("suscripcion_error", sumError);
    }

    @Override
    public void close() throws ItemStreamException {

    }
}
