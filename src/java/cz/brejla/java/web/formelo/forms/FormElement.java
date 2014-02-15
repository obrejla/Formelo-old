
package cz.brejla.java.web.formelo.forms;

import cz.brejla.java.web.formelo.application.FormeloApplication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author warden
 */
abstract public class FormElement implements IFormElement {

    /**
     * Element's name.
     */
    private String name;

    /**
     * Element's value.
     */
    private String value;

    /**
     * Element's parent form.
     */
    private Form form;

    /**
     * Collection of element's css classes.
     */
    private Collection<String> classes = new ArrayList<String>();

    /**
     * Creates new element object with empty value.
     *
     * @param name
     */
    protected FormElement(String name) {
        this(name, "");
    }

    /**
     * Creates new element object.
     *
     * @param name
     * @param value
     */
    protected FormElement(String name, String value) {
        if (name == null) {
            throw new IllegalArgumentException("Element name can not be set to 'null'.");
        }
        if (!name.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Element name can contain only alphabetical characters and numbers, but '" + name + "' given.");
        }

        this.name = name;
        this.value = value;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.IFormElement#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.IFormElement#setValue(java.lang.String) 
     */
    @Override
    public void setValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value of the element can not be set to 'null'.");
        }

        this.value = value;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.IFormElement#getValue() 
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.IFormElement#setForm(cz.brejla.java.web.formelo.forms.Form)
     */
    @Override
    public void setForm(Form form) {
        if (form == null) {
            throw new IllegalArgumentException("Parent form of the element can not be set to 'null'.");
        }

        this.form = form;
    }

    /**
     * Adds css class to element.
     *
     * @param cls
     * @return
     */
    public FormElement addClass(String cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Css class of the element can not be set to 'null'.");
        }

        classes.add(cls);

        return this;
    }

    /**
     * Returns true, if element validation passes. False otherwise.
     *
     * @return
     */
    public boolean isValid() {
        return true;
    }

    /**
     * Returns element's parent form.
     *
     * @return
     */
    protected Form getForm() {
        return form;
    }

    /**
     * Returns Collection of css classes which belong to the element.
     *
     * @return
     */
    protected Collection<String> getClasses() {
        return classes;
    }

    /**
     * Returns formated class attribute for addition to html output.
     *
     * @return
     */
    protected String getClassAttribute() {
        StringBuffer classAttribute = new StringBuffer();

        Iterator i = getClasses().iterator();
        while (i.hasNext()) {
            classAttribute.append(i.next());
            if (i.hasNext()) {
                classAttribute.append(", ");
            }
        }

        return " class=\"" + classAttribute.toString() + "\"";
    }

}
