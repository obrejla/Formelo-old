
package cz.brejla.java.web.formelo.application;

/**
 *
 * @author warden
 */
public interface IComponent extends ISignalProcessor, ICallbackRunner {

    /**
     * Sets component's name.
     *
     * @param name
     */
    public void setName(String name);

    /**
     * Returns component's name.
     *
     * @return
     */
    public String getName();

    /**
     * Sets parent component.
     *
     * @param parent
     */
    public void setParent(IComponentContainer parent);

    /**
     * Returns parent object.
     *
     * @return
     */
    public IComponentContainer getParent();

}
