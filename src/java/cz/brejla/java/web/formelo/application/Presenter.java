
package cz.brejla.java.web.formelo.application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author warden
 */
abstract public class Presenter extends ComponentContainer implements IPresenter {

    /**
     * Presenter request.
     */
    private IPresenterRequest request;

    /**
     * Application router.
     */
    private IRouter router;

    /**
     * Methods of the presenter.
     */
    private Method[] methods;

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IPresenter#run()
     */
    @Override
    public void run(IPresenterRequest request, IRouter router) {
        if (request == null) {
            throw new IllegalArgumentException("Presenter request can not be null.");
        }

        this.request = request;

        if (router == null) {
            throw new IllegalArgumentException("Router can not be null.");
        }

        this.router = router;

        try {
            startup();
            runMethod("action");
            processSignal(request.getSignal());
            runMethod("render");
            runBeforeRenderCallbacks();
            toTemplate();
            destruct();
        } catch (RedirectingException ex) {}
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IComponent#getName() 
     */
    @Override
    public String getName() {
        return getClass().getName().replace("Presenter", "");
    }

    /**
     * Returns presenter request.
     */
    public IPresenterRequest getRequest() {
        return request;
    }

    /**
     * Returns application router.
     *
     * @return
     */
    public IRouter getRouter() {
        return router;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ISignalProcessor#processSignal(cz.brejla.java.web.formelo.application.ISignal) 
     */
    @Override
    public void processSignal(ISignal signal) {
        if (signal != null) {
            getComponent(signal.getName()).processSignal(signal);
        }
    }

    /**
     * Method call at first in presenter lifecycle.
     */
    protected void startup() {}

    /**
     * Method call at last in presenter lifecycle.
     */
    protected void destruct() {}

    /**
     * Redirects presnter to another presenter and action.
     *
     * @param presenter
     * @param action
     */
    public void redirect(String presenter, String action) {
        IPresenterRequest pr = new PresenterRequest(presenter, action, request.getMethod(), null, null);
        String url = getRouter().restoreUrl(pr);
        
        FormeloApplication.getResponse().setHeader("Location", url);
        FormeloApplication.getResponse().setStatus(301);
        
        throw new RedirectingException();
    }

    /**
     * Terminates running presenter.
     */
    public void terminate() {
        throw new RedirectingException();
    }

    /**
     * Runs method type for currently processed action.
     *
     * @param type
     */
    private void runMethod(String type) {
        String action = request.getAction();
        action = action.substring(0, 1).toUpperCase() + action.substring(1);
        try {
            if (methods == null) {
                methods = Class.forName(getClass().getName()).getDeclaredMethods();
            }
            
            for (Method m : methods) {
                if (m.getName().equals(type + action)) {
                    m.invoke(this);
                }
            }
        } catch (InvocationTargetException ex) {
            throw new RedirectingException();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * Shows template after presenter is processed.
     */
    private void toTemplate() {
        try {
            String presenter = getClass().getSimpleName();
            FormeloApplication.getRequest().setAttribute("presenter", this);
            FormeloApplication.getRequest().getRequestDispatcher("/" + presenter.replace("Presenter", "") + "/" + request.getAction() + ".jsp")
                    .forward(FormeloApplication.getRequest(), FormeloApplication.getResponse());
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}
