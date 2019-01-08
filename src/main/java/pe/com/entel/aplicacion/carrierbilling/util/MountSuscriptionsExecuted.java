package pe.com.entel.aplicacion.carrierbilling.util;

import javax.annotation.PostConstruct;

/**
 * @author jsegovia
 * @version 1.0, 1/8/19
 */
public class MountSuscriptionsExecuted {

    private int suscriptionOk;

    private int suscriptionError;

    @PostConstruct
    private void init() {
        suscriptionOk = 0;
        suscriptionError = 0;
    }

    public void addSuscriptionOk() {
        suscriptionOk++;
    }

    public void addSuscriptionError() {
        suscriptionError++;
    }

    public int getSuscriptionOk() {
        return suscriptionOk;
    }

    public void setSuscriptionOk(int suscriptionOk) {
        this.suscriptionOk = suscriptionOk;
    }

    public int getSuscriptionError() {
        return suscriptionError;
    }

    public void setSuscriptionError(int suscriptionError) {
        this.suscriptionError = suscriptionError;
    }
}
