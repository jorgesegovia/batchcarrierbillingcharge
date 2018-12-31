package pe.com.entel.aplicacion.carrierbilling;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0, 17/12/2018
 * @autor jsegovia
 */
@Component
@Scope
public class GestionCobroLaunch {

    public static Logger logger = Logger.getLogger(GestionCobroLaunch.class);

    @Autowired
    private JobOperator jobOperator;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = null;
        try {
            ctx = new ClassPathXmlApplicationContext("spring-context.xml");
            ctx.registerShutdownHook();

            GestionCobroLaunch mainTask = ctx.getBean(GestionCobroLaunch.class);
            mainTask.start(args);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (ctx != null) {
                ctx.close();
            }
        }
    }

    public void validatingJob(String jobName) throws Exception {
        int timeToWaitJob = 1;
        while (true) {
            Set<Long> idsJob = jobOperator.getRunningExecutions(jobName);

            if (idsJob == null || idsJob.size() > 0) {
                //logger.info("Job [ " + jobName + " ] se encuentra en ejecucion. Esperando " + timeToWaitJob + " secs ...");
                TimeUnit.SECONDS.sleep(timeToWaitJob);
            } else {
                //logger.info("Job [ " + jobName + " ] termino de ejecutarse!");
                break;
            }
        }
    }


    public void start(String args[]) {
        try {

            String dateProcess = args[0];
/*
            JobParameters firstJobParameters = new JobParametersBuilder()
                    .addString("dateProcesoStr", dateProcess)
                    .toJobParameters();

            String firstParams = firstJobParameters.toString();
            firstParams = StringUtils.substring(firstParams, 1, firstParams.length() - 1);

            jobOperator.start(firstJobName, firstParams);

            validatingJob(firstJobName);
*/
            String secondJobName = "gestionCancelacionJob";

            JobParameters secondJobParameters = new JobParametersBuilder()
                    .addString("dateProcesoStr", dateProcess)
                    .toJobParameters();

            String secondParams = secondJobParameters.toString();
            secondParams = StringUtils.substring(secondParams, 1, secondParams.length() - 1);

            jobOperator.start(secondJobName, secondParams);

            validatingJob(secondJobName);

        } catch (Exception e) {
            logger.error(e);
        }
    }

}
