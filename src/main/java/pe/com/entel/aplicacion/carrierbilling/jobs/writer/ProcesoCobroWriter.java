package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaCobroSp;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaCobroStoreProcedure;

import java.util.List;

/**
 * @version 1.0, 17/12/2018
 * @autor jsegovia
 */
public class ProcesoCobroWriter implements ItemWriter<Suscripcion>, ItemStreamWriter<Suscripcion> {

    static Logger logger = Logger.getLogger(ProcesoCobroWriter.class);

    private ActualizaCobroStoreProcedure procedure;

    private int suscripcionOk;
    private int suscripcionError;

    @Override
    public void write(List<? extends Suscripcion> list) throws Exception {

        for (Suscripcion s : list) {
            ActualizaCobroSp o = new ActualizaCobroSp();
            o.setIdsuscripcion(s.getIdSuscripcion());

            if ("0000".equals(s.getCodigorpta())) {
                o.setEstado("COBRADO");
                suscripcionOk++;
            } else {
                o.setEstado("PENDIENTE");
                suscripcionError++;
            }

            ActualizaCobroSp resp = procedure.run(o);

            if (!"0000".equals(resp.getCodigorpta())) {
                logger.debug("Suscripcion: " + s);
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
