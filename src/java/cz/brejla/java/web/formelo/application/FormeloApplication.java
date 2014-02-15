
package cz.brejla.java.web.formelo.application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author warden
 */
public class FormeloApplication {

    /**
     * Application Singleton object.
     */
    private static FormeloApplication application;

    /**
     * Application request.
     */
    private static HttpServletRequest request;

    /**
     * Application response.
     */
    private static HttpServletResponse response;

    /**
     * Package, where users presenters are stored.
     */
    private String presentersPackage;

    /**
     * Application router.
     */
    private IRouter router;

    /**
     * Returns Singleton object of application and sets current application request and response.
     *
     * @param request
     * @param response
     * @return
     */
    public static FormeloApplication getApplication(HttpServletRequest request, HttpServletResponse response) {
        FormeloApplication.request = request;
        FormeloApplication.response = response;

        if (application == null) {
            application = new FormeloApplication();
        }

        return application;
    }

    /**
     * Returns current application request.
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return request;
    }

    /**
     * Returns current application response.
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        return response;
    }

    /**
     * Returns current application session.
     *
     * @return
     */
    public static HttpSession getSession() {
        if (getRequest() != null) {
            return getRequest().getSession();
        }

        return null;
    }

    /**
     * Private constructor for Singleton pattern.
     */
    private FormeloApplication() {}

    /**
     * Sets router of the application.
     *
     * @param router
     */
    public void setRouter(IRouter router) {
        if (router == null) {
            throw new IllegalArgumentException("Router can not be set to 'null' value.");
        }

        this.router = router;
    }

    /**
     * Returns router of the application.
     *
     * @return
     */
    public IRouter getRouter() {
        return router;
    }

    /**
     * Sets package, where users presenters are stored.
     *
     * @param presenterPackage
     */
    public void setPresenterPackage(String presenterPackage) {
        if (presenterPackage == null) {
            throw new IllegalArgumentException("Presenter package can not be set to 'null' value.");
        }

        this.presentersPackage = presenterPackage;
    }

    /**
     * Runs application.
     */
    public void run() {
        IPresenterRequest presenterRequest = router.processRequest(getRequest());
        try {
            IPresenter presenter = (IPresenter) Class.forName(presentersPackage + "." + presenterRequest.getPresenter() + "Presenter").newInstance();
            presenter.run(presenterRequest, router);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}
