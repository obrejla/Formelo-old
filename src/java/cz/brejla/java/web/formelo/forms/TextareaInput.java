
package cz.brejla.java.web.formelo.forms;

/**
 *
 * @author warden
 */
public class TextareaInput extends InputElement {

    /**
     * Creates new text input object.
     *
     * @param name
     * @param label
     */
    public TextareaInput(String name, String label) {
        super(name, label);
    }

    /**
     * Returns textual html representation of text input element.
     *
     * @return
     */
    @Override
    public String toString() {
        return "<p>\n<label for=\"" + getName() + "\">" + getLabel() + "</label>\n<textarea name=\"" + getForm().getName() + "-" + getName() + "\" id=\"" + getName() + "\"" + getClassAttribute() + ">" + getValue() + "</textarea>\n" + getFailMessage() + "</p>";
    }

}
