package pe.com.entel.aplicacion.carrierbilling.jobs.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.service.GestionCobroService;

/**
 * @author jsegovia
 * @version 1.0, 12/19/18
 */
public class ProcesoCobroProcessor implements ItemProcessor<Suscripcion, Suscripcion> {

    static Logger logger = Logger.getLogger(ProcesoCobroProcessor.class);

    private GestionCobroService gestionCobroService;

    @Override
    public Suscripcion process(Suscripcion s) throws Exception {
        Suscripcion resp = null;
        try {

            logger.debug("Proceso de cobro para suscripcion: " + s.getIdSuscripcion());

            resp = gestionCobroService.ejecutarCobro(s);

        } catch (Exception e) {
            logger.error(e);
        }

        return resp;
    }

    public GestionCobroService getGestionCobroService() {
        return gestionCobroService;
    }

    public void setGestionCobroService(GestionCobroService gestionCobroService) {
        this.gestionCobroService = gestionCobroService;
    }
}
