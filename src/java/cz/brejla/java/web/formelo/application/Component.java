
package cz.brejla.java.web.formelo.application;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author warden
 */
abstract public class Component implements IComponent {

    /**
     * Collection of callbacks which are executed after component is attached to application.
     */
    private Collection<ICallback> onAttachCallbacks = new ArrayList<ICallback>();

    /**
     * Collection of callbacks which are executed before component is rendered.
     */
    private Collection<ICallback> onBeforeRenderCallbacks = new ArrayList<ICallback>();

    /**
     * Name of the component.
     */
    private String name;

    /**
     * Parent component.
     */
    private IComponentContainer parent;

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IComponent#setName(java.lang.String) 
     */
    @Override
    public void setName(String name) {
        if (!name.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Component name can contain only alphabetical characters and numbers, but '" + name + "' given.");
        }

        this.name = name;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IComponent#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IComponent#setParent(cz.brejla.java.web.formelo.application.IComponent) 
     */
    @Override
    public void setParent(IComponentContainer parent) {
        if (parent == null) {
            throw new IllegalArgumentException("Parent component can not be set to 'null'.");
        }

        this.parent = parent;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.IComponent#getParent()
     */
    @Override
    public IComponentContainer getParent() {
        return parent;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ISignalProcessor#processSignal(cz.brejla.java.web.formelo.application.ISignal)
     */
    @Override
    public void processSignal(ISignal signal) {}

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ICallbackRunner#runCallbacksOnAttach() 
     */
    @Override
    public void runCallbacksOnAttach() {
        for (ICallback callback : onAttachCallbacks) {
            callback.call();
        }
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ICallbackRunner#runCallbacksOnBeforeRender()
     */
    @Override
    public void runCallbacksOnBeforeRender() {
        for (ICallback callback : onBeforeRenderCallbacks) {
            callback.call();
        }
    }

    /**
     * Adds callback object which is called after component is attached to application.
     *
     * @param callback
     */
    public void addCallbackOnAttach(ICallback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("'null' callback can not be assigned to component.");
        }
        if (onAttachCallbacks.contains(callback)) {
            throw new IllegalArgumentException("Passed callback can not be assigned to component, callback already exists.");
        }
        
        onAttachCallbacks.add(callback);
    }

    /**
     * Adds callback object which is called after component is attached to application.
     *
     * @param callback
     */
    public void addCallbackOnBeforeRender(ICallback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("'null' callback can not be assigned to component.");
        }
        if (onBeforeRenderCallbacks.contains(callback)) {
            throw new IllegalArgumentException("Passed callback can not be assigned to component, callback already exists.");
        }

        onBeforeRenderCallbacks.add(callback);
    }

    /**
     * Sets attribute into request for using in jsp template.
     *
     * @param name
     * @param o
     */
    protected void setTemplateAttribute(String name, Object o) {
        FormeloApplication.getRequest().setAttribute(name, o);
    }

    /**
     * Sets attribute into session.
     *
     * @param name
     * @param value
     */
    protected void setSessionAttribute(String name, Object value) {
        FormeloApplication.getSession().setAttribute(name, value);
    }

    /**
     * Returns attribute form session.
     *
     * @param name
     * @return
     */
    protected Object getSessionAttribute(String name) {
        return FormeloApplication.getSession().getAttribute(name);
    }

}
