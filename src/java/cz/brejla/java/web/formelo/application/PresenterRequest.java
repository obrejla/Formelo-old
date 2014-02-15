
package cz.brejla.java.web.formelo.application;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author warden
 */
public class PresenterRequest implements IPresenterRequest {

    /**
     * Presenter.
     */
    private String presenter;

    /**
     * Action.
     */
    private String action;

    /**
     * Method of the request.
     */
    private String method;

    /**
     * Signal of the request.
     */
    private ISignal signal;

    /**
     * Map of rest parameters.
     */
    private Map<String, String> restParams = new HashMap<String, String>();

    /**
     * Creates presenter request object from passed presenter request.
     *
     * @param request
     */
    public PresenterRequest(IPresenterRequest request) {
        presenter = request.getPresenter();
        action = request.getAction();
        method = request.getMethod();
        signal = request.getSignal();
        restParams = request.getRestParams();
    }

    /**
     * Creates presenter request from passed arguments.
     *
     * @param presenter
     * @param action
     * @param method
     * @param signal
     * @param restParams 
     */
    public PresenterRequest(String presenter, String action, String method, ISignal signal, Map<String, String> restParams) {
        this.presenter = presenter;
        this.action = action;
        this.method = method;
        this.signal = signal;
        this.restParams = restParams;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IPresenterRequest#getPresenter()
     */
    @Override
    public String getPresenter() {
        return presenter;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IPresenterRequest#getAction()
     */
    @Override
    public String getAction() {
        return action;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IPresenterRequest#getMethod()
     */
    @Override
    public String getMethod() {
        return method;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IPresenterRequest#getSignal()
     */
    @Override
    public ISignal getSignal() {
        return signal;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IPresenterRequest#getRestParams() 
     */
    @Override
    public Map<String, String> getRestParams() {
        return restParams;
    }

}
