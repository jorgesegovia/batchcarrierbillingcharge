package pe.com.entel.aplicacion.carrierbilling.jobs.listener;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class ProcesoCobroStepListener implements StepExecutionListener {

    static Logger logger = Logger.getLogger(ProcesoCobroStepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("Total de paginas: " + stepExecution.getJobExecution().getExecutionContext().getInt("total_paginas"));
        logger.info("Pagina actual: " + stepExecution.getJobExecution().getExecutionContext().getInt("pagina_actual"));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        stepExecution.getJobExecution().getExecutionContext().putInt("suscripcion_ok", 4);
        stepExecution.getJobExecution().getExecutionContext().putInt("suscripcion_error", 4);
        stepExecution.getJobExecution().getExecutionContext().putInt("suscripcion_reintento", 4);


        int totalPaginas = stepExecution.getJobExecution().getExecutionContext().getInt("total_paginas");
        int paginaActual = stepExecution.getJobExecution().getExecutionContext().getInt("pagina_actual");

        try {
            paginaActual++;

            stepExecution.getJobExecution().getExecutionContext().putInt("pagina_actual", paginaActual);

            logger.info("Total de paginas: " + totalPaginas);
            logger.info("Pagina actual: " + paginaActual);

            if (paginaActual <= totalPaginas) {
                stepExecution.setExitStatus(new ExitStatus("REPEAT"));
                logger.info("ExitStatus: REPEAT");
            } else {
                stepExecution.setExitStatus(ExitStatus.COMPLETED);
                logger.info("ExitStatus: " + ExitStatus.COMPLETED.toString());
            }

        } catch (NumberFormatException e) {
            logger.error(e);
        }

        return stepExecution.getExitStatus();
    }

}
