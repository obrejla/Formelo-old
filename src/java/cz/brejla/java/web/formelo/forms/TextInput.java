
package cz.brejla.java.web.formelo.forms;


/**
 *
 * @author warden
 */
public class TextInput extends InputElement {

    /**
     * Creates new text input object.
     *
     * @param name
     * @param label
     */
    public TextInput(String name, String label) {
        super(name, label);
    }

    /**
     * Returns textual html representation of text input element.
     *
     * @return
     */
    @Override
    public String toString() {
        return "<p>\n<label for=\"" + getName() + "\">" + getLabel() + "</label>\n<input type=\"text\" name=\"" + getForm().getName() + "-" + getName() + "\" id=\"" + getName() + "\" value=\"" + getValue() + "\"" + getClassAttribute() + " />\n" + getFailMessage() + "</p>";
    }

}
