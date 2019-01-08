package pe.com.entel.aplicacion.carrierbilling.jobs.tasklet;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import pe.com.entel.aplicacion.carrierbilling.domain.InicioCobroSp;
import pe.com.entel.aplicacion.carrierbilling.repository.RegCobroPendienteStoreProcedure;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @version 1.0, 17/12/2018
 * @autor jsegovia
 */
public class InicioGestionCobroTasklet implements Tasklet {

    static Logger logger = Logger.getLogger(InicioGestionCobroTasklet.class);

    private RegCobroPendienteStoreProcedure procedure;

    private String dateProcesoStr;

    private String creadoPor;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        long startTime = System.currentTimeMillis();

        InicioCobroSp in = new InicioCobroSp();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dateProceso = sdf.parse(dateProcesoStr);

        in.setFechaCobro(dateProceso);
        in.setCreadoPor(creadoPor);

        InicioCobroSp o = procedure.run(in);

        logger.debug(o);

        if (!"0000".equals(o.getCodigoRpta())) {
            throw new Exception(o.getMensaje());
        }

        if (o.getNumeroPaginas() == 0) {
            chunkContext.getStepContext().getStepExecution().setExitStatus(new ExitStatus("NO_SUSCRIPTIONS"));
        } else {
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putInt("total_paginas", o.getNumeroPaginas());
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putInt("pagina_actual", 1);
        }

        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putInt("id_bill_control", o.getIdBillControl());
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putInt("suscripcion_ok", 0);
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putInt("suscripcion_error", 0);
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putInt("suscripcion_reintento", 0);
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putLong("tiempo_inicio", startTime);

        logger.info("Se encontraron [ " + o.getCantidadSuscripciones() + " ] suscripcione(s) para procesar agrupadas en [ " + o.getNumeroPaginas() + " ] lote(s)");

        return RepeatStatus.FINISHED;
    }

    public RegCobroPendienteStoreProcedure getProcedure() {
        return procedure;
    }

    public String getDateProcesoStr() {
        return dateProcesoStr;
    }

    public void setDateProcesoStr(String dateProcesoStr) {
        this.dateProcesoStr = dateProcesoStr;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public void setProcedure(RegCobroPendienteStoreProcedure procedure) {
        this.procedure = procedure;
    }
}
