package pe.com.entel.aplicacion.carrierbilling.jobs.tasklet;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaEjecucionSp;
import pe.com.entel.aplicacion.carrierbilling.domain.InicioCobroSp;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaEjecucionStoreProcedure;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jsegovia
 * @version 1.0, 12/26/18
 */
public class FinCobroTasklet implements Tasklet {

    static Logger logger = Logger.getLogger(FinCobroTasklet.class);

    private ActualizaEjecucionStoreProcedure procedure;

    private int idBillControl;

    private int suscripcionOk;

    private int suscripcionError;

    private int suscripcionReintento;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        ActualizaEjecucionSp in = new ActualizaEjecucionSp();

        in.setIdBillControl(idBillControl);
        in.setSuscripcionOk(suscripcionOk);
        in.setSuscripcionError(suscripcionError);
        in.setSuscripcionReintento(suscripcionReintento);

        ActualizaEjecucionSp o = procedure.run(in);

        logger.debug(o);

        if (!"0000".equals(o.getCodigoRpta())) {
            throw new Exception(o.getMensaje());
        }

        return RepeatStatus.FINISHED;
    }

    public ActualizaEjecucionStoreProcedure getProcedure() {
        return procedure;
    }

    public void setProcedure(ActualizaEjecucionStoreProcedure procedure) {
        this.procedure = procedure;
    }

    public int getIdBillControl() {
        return idBillControl;
    }

    public void setIdBillControl(int idBillControl) {
        this.idBillControl = idBillControl;
    }

    public int getSuscripcionOk() {
        return suscripcionOk;
    }

    public void setSuscripcionOk(int suscripcionOk) {
        this.suscripcionOk = suscripcionOk;
    }

    public int getSuscripcionError() {
        return suscripcionError;
    }

    public void setSuscripcionError(int suscripcionError) {
        this.suscripcionError = suscripcionError;
    }

    public int getSuscripcionReintento() {
        return suscripcionReintento;
    }

    public void setSuscripcionReintento(int suscripcionReintento) {
        this.suscripcionReintento = suscripcionReintento;
    }
}
