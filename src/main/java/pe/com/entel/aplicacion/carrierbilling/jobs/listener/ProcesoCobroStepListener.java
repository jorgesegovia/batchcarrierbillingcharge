package pe.com.entel.aplicacion.carrierbilling.jobs.listener;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class ProcesoCobroStepListener implements StepExecutionListener {

    static Logger logger = Logger.getLogger(ProcesoCobroStepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("Procesando Lote [ " + stepExecution.getJobExecution().getExecutionContext().getInt("pagina_actual") + " ] ");
        stepExecution.getExecutionContext().putInt("suscripcion_ok", 0);
        stepExecution.getExecutionContext().putInt("suscripcion_error", 0);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        int totalPaginas = stepExecution.getJobExecution().getExecutionContext().getInt("total_paginas");
        int paginaActual = stepExecution.getJobExecution().getExecutionContext().getInt("pagina_actual");

        try {
            paginaActual++;

            stepExecution.getJobExecution().getExecutionContext().putInt("pagina_actual", paginaActual);

            logger.debug("Total de paginas: " + totalPaginas);
            logger.debug("Pagina actual: " + paginaActual);

            if (paginaActual <= totalPaginas) {
                stepExecution.setExitStatus(new ExitStatus("REPEAT"));
                logger.info("Se inicia ejecucion del siguiente Lote ...");
            } else {
                stepExecution.setExitStatus(ExitStatus.COMPLETED);
                logger.info("Fin de la ejecucion de Lotes.");
            }

        } catch (NumberFormatException e) {
            logger.error(e);
        }

        logger.debug("Total de suscripciones Ok = " + stepExecution.getExecutionContext().getInt("suscripcion_ok"));
        logger.debug("Total de suscripciones Error = " + stepExecution.getExecutionContext().getInt("suscripcion_error"));

        return stepExecution.getExitStatus();
    }

}
