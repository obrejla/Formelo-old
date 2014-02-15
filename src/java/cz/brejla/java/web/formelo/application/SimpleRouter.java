
package cz.brejla.java.web.formelo.application;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author warden
 */
public class SimpleRouter extends Router {

    /**
     * Name of signal parametr in url.
     */
    private static final String SIGNAL_PARAMETR_NAME = "signal";

    /**
     * Default presenter.
     */
    private String presenter = "Default";

    /**
     * Default action.
     */
    private String action = "default";
    
    /**
     * Creates new router object and sets default presenter and action.
     *
     * @param presenter
     * @param action
     */
    public SimpleRouter(String presenter, String action) {
        if (presenter == null || action == null) {
            throw new IllegalArgumentException("Presenter and action can not be null.");
        }
        if (presenter.trim().equals("") || action.trim().equals("")) {
            throw new IllegalArgumentException("Presenter and action can not be empty, but '" + presenter + "' and '" + action + "' given");
        }

        this.presenter = presenter;
        this.action = action;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IRouter#processRequest(javax.servlet.http.HttpServletRequest) 
     */
    @Override
    public IPresenterRequest processRequest(HttpServletRequest request) {
        String actPresenter = "";
        String actAction = "";

        if (request.getParameter("presenter") == null) {
            actPresenter = this.presenter;
        } else {
            actPresenter = request.getParameter("presenter");
        }

        if (request.getParameter("action") == null) {
            actAction = this.action;
        } else {
            actAction = request.getParameter("action");
        }
        
        return new PresenterRequest(actPresenter, actAction, request.getMethod(), extractSignal(request), extractRestParams(request));
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IRouter#restoreUrl() 
     */
    public String restoreUrl(IPresenterRequest request) {
        String url = "";

        if (!request.getPresenter().equals(presenter)) {
            url = "presenter=" + request.getPresenter();
        }
        if (!request.getAction().equals(action)) {
            if (url.trim().isEmpty()) {
                url = "action=" + request.getAction();
            } else {
                url += "&action=" + request.getAction();
            }
        }
        if (request.getSignal() != null) {
            if (url.trim().isEmpty()) {
                url = getSignalUrlFormated(request.getSignal());
            } else {
                url += "&" + getSignalUrlFormated(request.getSignal());
            }
        }

        if (request.getRestParams() != null) {
            Iterator i = request.getRestParams().keySet().iterator();
            while (i.hasNext()) {
                String name = (String) i.next();
                String value = request.getRestParams().get(name);
                if (url.trim().isEmpty()) {
                    url = name + "=" + value;
                } else {
                    url += "&" + name + "=" + value;
                }
            }
        }

        return url.trim().isEmpty() ? FormeloApplication.getRequest().getRequestURI() : FormeloApplication.getRequest().getRequestURI() + "?" + url;
    }

    /**
     * Extracts signal from application request.
     *
     * @param request
     * @return
     */
    private ISignal extractSignal(HttpServletRequest request) {
        if (request.getParameter(SIGNAL_PARAMETR_NAME) == null) {
            return null;
        }

        String signalParam = request.getParameter(SIGNAL_PARAMETR_NAME);
        String[] signalSplited = signalParam.split("-");
        //request.removeAttribute(signalParam);
        String componentSignalName = signalSplited[0];
        Signal signal = new Signal(componentSignalName);
        if (signalSplited.length > 1) {
            signal.setType(signalSplited[1]);
        }

        List<String> requestParameterNames = Collections.list((Enumeration<String>) request.getParameterNames());

        for (String parameterName : requestParameterNames){
            if (parameterName.startsWith(componentSignalName + "-")) {
                signal.addValue(parameterName.replaceAll(componentSignalName + "-", ""), request.getParameter(parameterName));
                //request.removeAttribute(parameterName);
            }
        }

        return signal;
    }

    /**
     * Extracts rest parameters from application request.
     *
     * @param request
     * @return
     */
    private Map<String, String> extractRestParams(HttpServletRequest request) {
        String componentSignalName = "";
        if (request.getParameter(SIGNAL_PARAMETR_NAME) != null) {
            String[] signalSplited = request.getParameter(SIGNAL_PARAMETR_NAME).split("-");
            componentSignalName = signalSplited[0] + "-";
        }

        List<String> requestParameterNames = Collections.list((Enumeration<String>) request.getParameterNames());
        Map<String, String> restParams = new HashMap<String, String>();

        for (String parameterName : requestParameterNames){
            if (!parameterName.startsWith(componentSignalName) && !parameterName.equals(SIGNAL_PARAMETR_NAME)
                    && !parameterName.equals("presenter") && !parameterName.equals("action")) {
                restParams.put(parameterName, request.getParameter(parameterName));
            }
        }

        return restParams;
    }

    /**
     * Returns signal formated into url params.
     *
     * @param request
     * @return
     */
    private String getSignalUrlFormated(ISignal iSignal) {
        Signal signal = (Signal) iSignal;
        
        return SIGNAL_PARAMETR_NAME + "=" + signal.getName()
                + (signal.getType() == null ? "" : "-" + signal.getType());
    }

}
