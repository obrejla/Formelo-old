
package cz.brejla.java.web.formelo.application;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author warden
 */
abstract public class ComponentContainer extends Component implements IComponentContainer {

    /**
     * Prefix of component factory methods.
     */
    private static final String COMPONENT_FACTORY_PREFIX = "createComponent";

    /**
     * Components registred for this container.
     */
    private Map<String, IComponent> components = new HashMap<String, IComponent>();

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IComponentContainer#addComponent(cz.brejla.java.web.formelo.application.IComponent)
     */
    @Override
    public void addComponent(IComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("'null' component can not be added to component container.");
        }
        if (component.getName() == null) {
            throw new IllegalArgumentException("Component with 'null' name can not be added into component container.");
        }
        if (components.containsKey(component.getName())) {
            throw new IllegalArgumentException("Component with name '" + component.getName() + "' already exists.");
        }

        components.put(component.getName(), component);
        component.runCallbacksOnAttach();
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IComponentContainer#getComponent(java.lang.String)
     */
    @Override
    public IComponent getComponent(String name) {
        if (components.containsKey(name)) {
            return components.get(name);
        }

        this.attachComponent(name);

        return components.get(name);
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IComponentContainer#getComponents() 
     */
    @Override
    public Map<String, IComponent> getComponents() {
        return components;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IComponentContainer#removeComponent(java.lang.String) 
     */
    @Override
    public void removeComponent(String name) {
        if (!components.containsKey(name)) {
            throw new IllegalArgumentException("Component with name '" + name + "' does not exists and can not be removed.");
        }

        components.remove(name);
    }

    /**
     * Runs runCallbacksOnBeforeRender method for each registred component.
     */
    protected void runBeforeRenderCallbacks() {
        Iterator i = components.values().iterator();
        while (i.hasNext()) {
            IComponent component = (IComponent) i.next();
            component.runCallbacksOnBeforeRender();
        }
    }

    /**
     * Tries to attach component to current component container.
     *
     * @param name
     */
    private void attachComponent(String name) {
        try {
            Method method = Class.forName(getClass().getName()).getMethod(COMPONENT_FACTORY_PREFIX + name);
            IComponent component = (IComponent) method.invoke(this);
            component.setName(name);
            component.setParent(this);
            addComponent(component);
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException("Factory method with name '" + COMPONENT_FACTORY_PREFIX + name + "' does not exists.");
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}
