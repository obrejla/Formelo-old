
package cz.brejla.java.web.formelo.forms;

import cz.brejla.java.web.formelo.application.ICallback;
import cz.brejla.java.web.formelo.application.Component;
import cz.brejla.java.web.formelo.application.IPresenterRequest;
import cz.brejla.java.web.formelo.application.ISignal;
import cz.brejla.java.web.formelo.application.Presenter;
import cz.brejla.java.web.formelo.application.PresenterRequest;
import cz.brejla.java.web.formelo.application.Signal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author warden
 */
public class Form extends Component {

    /**
     * Identificator of post method.
     */
    public static final String METHOD_POST = "post";

    /**
     * Identificator of get method.
     */
    public static final String METHOD_GET = "get";

    /**
     * Action attribute of the form.
     */
    private String action = "";

    /**
     * Method attribute of the form.
     */
    private String method = METHOD_POST;

    /**
     * Map of elements which belong to the form.
     */
    private Map<String, IFormElement> elements = new LinkedHashMap<String, IFormElement>();

    /**
     * Collection of callbacks which are executed after the form is submitted and valid.
     */
    private Collection<ICallback> onSubmitCallbacks = new ArrayList<ICallback>();

    /**
     * Returns textual html representation of form and its components.
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();

        String newAction = prepareAction();

        output.append("<form action=\"" + newAction + "\" method=\"" + method + "\">\n");

        Iterator i = elements.values().iterator();
        while (i.hasNext()) {
            output.append((IFormElement) i.next());
        }

        output.append("</form>\n");

        return output.toString();
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ISignalProcessor#processSignal(cz.brejla.java.web.formelo.application.ISignal) 
     */
    @Override
    public void processSignal(ISignal signal) {
        if ((signal == null) || !signal.getName().equals(getName())) {
            throw new IllegalArgumentException("Signal with name '" + (signal == null ? "null" : signal.getName()) + "' does not belog to component with name '" + getName() + "'.");
        }

        Iterator i = elements.keySet().iterator();
        while (i.hasNext()) {
            String componentName = (String) i.next();
            elements.get(componentName).setValue(signal.getValue(componentName));
            setTemplateAttribute(componentName, null);
        }
        
        setSessionAttribute(getName(), this);

        Presenter p = (Presenter) getParent();
        IPresenterRequest oldPr = p.getRequest();
        p.redirect(oldPr.getPresenter(), oldPr.getAction());
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ICallbackRunner#runCallbacksOnBeforeRender()
     */
    @Override
    public void runCallbacksOnBeforeRender() {
        if (getSessionAttribute(getName()) != null) {
            Form sesForm = (Form) getSessionAttribute(getName());
            Iterator i = sesForm.getElements().keySet().iterator();
            while (i.hasNext()) {
                String componentName = (String) i.next();
                elements.get(componentName).setValue(sesForm.getElement(componentName).getValue());
            }

            setSessionAttribute(getName(), null);
            if (isValid()) {
                runOnSubmitCallbacks();
            }
        }
    }

    /**
     * Adds element to the form.
     *
     * @param element
     * @return
     */
    public IFormElement addElement(IFormElement element) {
        if (element == null) {
            throw new IllegalArgumentException("'null' element can not be added to the form.");
        }
        if (elements.containsKey(element.getName())) {
            throw new IllegalArgumentException("Element with name '" + element.getName() + "' already exists.");
        }

        elements.put(element.getName(), element);
        element.setForm(this);

        return element;
    }

    /**
     * Adds input element to the form.
     *
     * @param element
     * @return
     */
    public IInputElement addInputElement(IInputElement element) {
        addElement(element);

        return element;
    }

    /**
     * Returns element which is specified by passed name.
     *
     * @param name
     * @return
     */
    public IFormElement getElement(String name) {
        return elements.get(name);
    }

    /**
     * Returns elements container.
     *
     * @return
     */
    public Map<String, IFormElement> getElements() {
        return elements;
    }

    /**
     * Sets form action attribute.
     *
     * @param action
     * @return
     */
    public Form setAction(String action) {
        this.action = action;

        return this;
    }

    /**
     * Returns action attribute of the form.
     *
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets form method attribute.
     *
     * @param method
     * @return
     */
    public Form setMethod(String method) {
        if ((method == null) || (!method.equals(METHOD_POST) && !method.equals(METHOD_GET))) {
            throw new IllegalArgumentException("Form method can be only set to '" + METHOD_POST + "' or '" + METHOD_GET + "', but '" + method + "' given.");
        }

        this.method = method;

        return this;
    }

    /**
     * Returns form method attribute.
     *
     * @return
     */
    public String getMethod() {
        return method;
    }

    /**
     * Adds callback object which is executed when the form is submitted and valid.
     *
     * @param callback
     * @return
     */
    public Form addCallbackOnSubmit(ICallback callback) {
        if (onSubmitCallbacks.contains(callback)) {
            throw new IllegalArgumentException("Passed callback is already in use.");
        }
        
        onSubmitCallbacks.add(callback);

        return this;
    }

    /**
     * Returns true, if the form is valid.
     *
     * @return
     */
    private boolean isValid() {
        boolean output = true;

        Iterator i = elements.values().iterator();
        while (i.hasNext()) {
            IFormElement element = (IFormElement) i.next();

            FormElement formElement = (FormElement) element;
            if (!formElement.isValid()) {
                output = false;
            }
        }

        return output;
    }

    /**
     * Runs callbacks which should be executed when the form is submitted and valid.
     */
    private void runOnSubmitCallbacks() {
        for (ICallback callback : onSubmitCallbacks) {
            callback.call();
        }
    }

    /**
     * Prepares default form action.
     *
     * @return
     */
    private String prepareAction() {
        if (!action.trim().equals("")) {
            return action;
        }

        Signal signal = new Signal(getName());
        signal.setType("submit");

        Iterator i = elements.values().iterator();
        while (i.hasNext()) {
            IFormElement element = (IFormElement) i.next();
            signal.addValue(element.getName(), element.getValue());
        }

        Presenter presenter = (Presenter) getParent();
        IPresenterRequest request = new PresenterRequest(presenter.getRequest().getPresenter(), presenter.getRequest().getAction(),
                presenter.getRequest().getMethod(), signal, presenter.getRequest().getRestParams());

        return presenter.getRouter().restoreUrl(request);
    }

}
