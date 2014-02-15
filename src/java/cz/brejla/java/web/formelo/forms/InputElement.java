
package cz.brejla.java.web.formelo.forms;

import cz.brejla.java.web.formelo.forms.rules.IRule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author warden
 */
abstract public class InputElement extends FormElement implements IInputElement {

    /**
     * Element's label.
     */
    private String label;

    /**
     * Collection of rule object belonging to the element.
     */
    private Collection<IRule> rules = new ArrayList<IRule>();

    /**
     * Collection of fail messages after validation failed.
     */
    private Collection<String> failMessages = new ArrayList<String>();

    /**
     * Creates new input element object.
     *
     * @param name
     * @param label
     */
    public InputElement(String name, String label) {
        super(name);

        this.label = label;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.IInputElement#getLabel()
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.IInputElement#addRule(cz.brejla.java.web.formelo.forms.rules.IRule) 
     */
    @Override
    public IInputElement addRule(IRule rule) {
        if (rule == null) {
            throw new IllegalArgumentException("'null' rule can not be added to the element.");
        }
        if (rules.contains(rule)) {
            throw new IllegalArgumentException("Rule can not be added to the element, already exists.");
        }

        rules.add(rule);

        return this;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.IInputElement#isValid() 
     */
    @Override
    public boolean isValid() {
        boolean output = true;
        
        if (rules.isEmpty()) {
            return output;
        }

        for (IRule rule : rules) {
            if (!rule.validate(getValue())) {
                failMessages.add(rule.getFailMessage());
                addClass("error");

                output = false;
            }
        }

        return output;
    }

    /**
     * Returns collection of fail messages.
     *
     * @return
     */
    protected Collection<String> getFailMessages() {
        return failMessages;
    }

    /**
     * Returns formated fail messages for addition to html output.
     *
     * @return
     */
    protected String getFailMessage() {
        StringBuffer failMessage = new StringBuffer();

        Iterator i = getFailMessages().iterator();
        while (i.hasNext()) {
            failMessage.append(i.next());
            if (i.hasNext()) {
                failMessage.append(" ");
            }
        }

        return failMessage.toString();
    }

}
