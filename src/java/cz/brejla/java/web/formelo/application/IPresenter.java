
package cz.brejla.java.web.formelo.application;

/**
 *
 * @author warden
 */
public interface IPresenter extends IComponentContainer {

    /**
     * Runs presenter.
     *
     * @param request
     * @param router
     */
    public void run(IPresenterRequest request, IRouter router);

}
