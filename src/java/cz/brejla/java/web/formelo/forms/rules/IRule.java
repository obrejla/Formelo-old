
package cz.brejla.java.web.formelo.forms.rules;

/**
 *
 * @author warden
 */
public interface IRule {

    /**
     * Makes validation of passed value.
     *
     * @param value
     * @return
     */
    public boolean validate(String value);

    /**
     * Returns message of failed validation.
     *
     * @return
     */
    public String getFailMessage();

}
