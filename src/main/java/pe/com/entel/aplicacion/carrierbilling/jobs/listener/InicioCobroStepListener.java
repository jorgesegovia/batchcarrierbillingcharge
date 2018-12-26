package pe.com.entel.aplicacion.carrierbilling.jobs.listener;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @version 1.0, 17/12/2018
 * @autor jsegovia
 */
public class InicioCobroStepListener implements StepExecutionListener {

    static Logger logger = Logger.getLogger(InicioCobroStepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }
}