
package cz.brejla.java.web.formelo.application;

import java.util.Map;

/**
 *
 * @author warden
 */
public interface IComponentContainer extends IComponent {

    /**
     * Registers passed component for application.
     *
     * @param component Registered component.
     */
    public void addComponent(IComponent component);

    /**
     * Tries to find and return registered component via passed name. If it is
     * not successfull, tries to load factory method. Otherwies throws exception.
     *
     * @param name
     * @return Found component.
     */
    public IComponent getComponent(String name);

    /**
     * Returns map of components.
     *
     * @return
     */
    public Map<String, IComponent> getComponents();

    /**
     * Removes component identified by passed name.
     *
     * @param name
     */
    public void removeComponent(String name);

}
