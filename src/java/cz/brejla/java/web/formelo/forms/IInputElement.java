
package cz.brejla.java.web.formelo.forms;

import cz.brejla.java.web.formelo.forms.rules.IRule;

/**
 *
 * @author warden
 */
public interface IInputElement extends IFormElement {

    /**
     * Returns element's label.
     *
     * @return
     */
    public String getLabel();

    /**
     * Adds rule object to the element.
     *
     * @param rule
     * @return
     */
    public IInputElement addRule(IRule rule);

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.FormElement#isValid() 
     */
    public boolean isValid();

}
