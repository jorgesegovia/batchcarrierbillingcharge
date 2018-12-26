package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaCobroSp;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaCobroStoreProcedure;

import java.util.List;

/**
 * @version 1.0, 17/12/2018
 * @autor jsegovia
 */
public class ProcesoCobroWriter implements ItemWriter<Suscripcion> {

    static Logger logger = Logger.getLogger(ProcesoCobroWriter.class);

    private ActualizaCobroStoreProcedure procedure;

    @Override
    public void write(List<? extends Suscripcion> list) throws Exception {

        for (Suscripcion s : list) {

            logger.debug("ActualizaCobroStoreProcedure: " + procedure);

            ActualizaCobroSp o = new ActualizaCobroSp();
            o.setIdsuscripcion(s.getIdSuscripcion());
            o.setEstado("COBRADO");
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
}
