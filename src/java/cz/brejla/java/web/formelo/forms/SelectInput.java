
package cz.brejla.java.web.formelo.forms;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author warden
 */
public class SelectInput extends InputElement {

    private Map<Integer, String> options;

    public SelectInput(String name, String label, Map<Integer, String> options) {
        super(name, label);
        this.options = options;
    }

    /**
     * Returns textual html representation of text input element.
     *
     * @return
     */
    @Override
    public String toString() {
        return "<p>\n<label for=\"" + getName() + "\">" + getLabel() + "</label>\n<select name=\"" + getForm().getName() + "-" + getName() + "\" id=\"" + getName() + "\"" + getClassAttribute() + ">" + getOptionElements() + "</select>\n" + getFailMessage() + "</p>";
    }

    /**
     * Returns textual html representaion of select option.
     *
     * @return
     */
    private String getOptionElements() {
        String optElements = "";
/*
        for (int i = 0; i < options.length; i++) {
            optElements += "<option value=\"" + i + "\"" + (!getValue().equals("") ? (Integer.parseInt(getValue()) == i ? " selected=\"selected\"" : "") : "") + ">" + options[i] + "</option>\n";
        }
*/
        Iterator i = options.keySet().iterator();
        while (i.hasNext()) {
            Integer key = (Integer) i.next();
            optElements += "<option value=\"" + key + "\"" + (!getValue().equals("") ? (Integer.parseInt(getValue()) == key ? " selected=\"selected\"" : "") : "") + ">" + options.get(key) + "</option>\n";
        }

        return optElements;
    }

}
