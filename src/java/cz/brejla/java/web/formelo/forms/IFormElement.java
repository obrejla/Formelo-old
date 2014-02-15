
package cz.brejla.java.web.formelo.forms;

/**
 *
 * @author warden
 */
public interface IFormElement {

    /**
     * Returns elements name.
     *
     * @return
     */
    public String getName();

    /**
     * Sets elements value.
     *
     * @param value
     */
    public void setValue(String value);

    /**
     * Returns elements value.
     *
     * @return
     */
    public String getValue();

    /**
     * Sets elements parent form.
     *
     * @param form
     */
    public void setForm(Form form);

}
