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

    public void start(String args[]) {
        try {
            String jobName = "gestionCobroJob";

            String dateProcess = args[0];

            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("dateProcesoStr", dateProcess)
                    .toJobParameters();

            String params = jobParameters.toString();
            params = StringUtils.substring(params, 1, params.length() - 1);

            jobOperator.start(jobName, params);

            TimeUnit.SECONDS.sleep(10);

            logger.debug("Esperando 50 sec...");

            String jobName1 = "gestionCancelacionJob";

            JobParameters jobParameters1 = new JobParametersBuilder()
                    .addString("dateProcesoStr", dateProcess)
                    .toJobParameters();

            String params1 = jobParameters1.toString();
            params1 = StringUtils.substring(params1, 1, params1.length() - 1);

            jobOperator.start(jobName1, params1);

            TimeUnit.SECONDS.sleep(10);

            logger.debug("Esperando 20 sec...");

        } catch (Exception e) {
            logger.error(e);
        }
    }

}
