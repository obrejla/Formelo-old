
package cz.brejla.java.web.formelo.application;

import java.util.Map;

/**
 *
 * @author warden
 */
public interface IPresenterRequest {

    /**
     * Returns presenter name.
     *
     * @return
     */
    public String getPresenter();

    /**
     * Returns action name.
     *
     * @return
     */
    public String getAction();

    /**
     * Returns request method type.
     *
     * @return
     */
    public String getMethod();

    /**
     * Returns signal of the request.
     *
     * @return
     */
    public ISignal getSignal();

    /**
     * Returns params from url query string.
     *
     * @return
     */
    public Map<String, String> getRestParams();

}
