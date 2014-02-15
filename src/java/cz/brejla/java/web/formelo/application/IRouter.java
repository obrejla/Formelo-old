
package cz.brejla.java.web.formelo.application;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author warden
 */
public interface IRouter {

    /**
     * Returns presenter request from application request.
     *
     * @param request
     * @return
     */
    public IPresenterRequest processRequest(HttpServletRequest request);

    /**
     * Constructs url from passes presenter request.
     *
     * @param request
     * @return
     */
    public String restoreUrl(IPresenterRequest request);

}
