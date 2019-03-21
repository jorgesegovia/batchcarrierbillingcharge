package pe.com.entel.aplicacion.carrierbilling.jobs.writer;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaCobroSp;
import pe.com.entel.aplicacion.carrierbilling.domain.EjecucionCobro;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaCobroStoreProcedure;
import pe.com.entel.aplicacion.carrierbilling.util.MountSuscriptionsExecuted;

import java.util.List;

/**
 * @version 1.0, 17/12/2018
 * @autor jsegovia
 */
public class ProcesoCobroWriter implements ItemWriter<EjecucionCobro> {

    static Logger logger = Logger.getLogger(ProcesoCobroWriter.class);

    private ActualizaCobroStoreProcedure procedure;

    private MountSuscriptionsExecuted mountSuscriptionsExecuted;

    @Override
    public void write(List<? extends EjecucionCobro> list) throws Exception {

        for (EjecucionCobro e : list) {

            Suscripcion suscripcion = e.getSuscripcion();

            logger.debug("Ejecutando write: " + e.getWscodrpta() + " IdSuscripcion: " + suscripcion.getIdSuscripcion());

            ActualizaCobroSp o = new ActualizaCobroSp();

            if ("0000".equals(suscripcion.getCodigorpta())) {
                o.setEstadocobro("Cobrado");
                mountSuscriptionsExecuted.addSuscriptionOk();
                logger.info("Suscripcion con codigo [ " + suscripcion.getCodigorpta() + " ] cobrada OK!");
            } else {
                o.setEstadocobro("Pendiente");
                mountSuscriptionsExecuted.addSuscriptionError();
                logger.info("Suscripcion con codigo [ " + suscripcion.getCodigorpta() + " ] NO ha sido cobrada. Ejecucion con FALLO!");
            }

            o.setIdsuscripcion(suscripcion.getIdSuscripcion());
            o.setIdbillcontrol(suscripcion.getBillControl());
            o.setWscodrpta(e.getWscodrpta());
            o.setWsdescripcionrpta(e.getWsdescripcionrpta());
            o.setWsejecucion(e.getWsejecucion());
            o.setWshttpstatus(e.getWshttpstatus());
            o.setServicioejec(e.getServicioejec());
            o.setTipoEjecucion(suscripcion.getTipoEjecucion());
            
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

    public MountSuscriptionsExecuted getMountSuscriptionsExecuted() {
        return mountSuscriptionsExecuted;
    }

    public void setMountSuscriptionsExecuted(MountSuscriptionsExecuted mountSuscriptionsExecuted) {
        this.mountSuscriptionsExecuted = mountSuscriptionsExecuted;
    }
}
