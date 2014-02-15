
package cz.brejla.java.web.formelo.application;

/**
 *
 * @author warden
 */
public interface ICallbackRunner {

    /**
     * Runs callbacks which are registered for execution after component is attached to application.
     */
    public void runCallbacksOnAttach();

    /**
     * Runs callbacks which are registered for execution before rendering.
     */
    public void runCallbacksOnBeforeRender();

}
