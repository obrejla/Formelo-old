
package cz.brejla.java.web.formelo.forms;

/**
 *
 * @author warden
 */
public class SubmitButton extends FormElement {

    /**
     * Creates new submit button object.
     *
     * @param name
     * @param value
     */
    public SubmitButton(String name, String value) {
        super(name, value);
    }

    /**
     * Returns textual html representation of submit button element.
     *
     * @return
     */
    @Override
    public String toString() {
        return "<p>\n<input type=\"submit\" name=\"" + getForm().getName() + "-" + getName() + "\" value=\"" + getValue() + "\" />\n</p>\n";
    }

}
