
package cz.brejla.java.web.formelo.forms.rules;


/**
 *
 * @author warden
 */
public class FilledRule extends Rule {

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.rules.Rule#Rule(java.lang.String)
     */
    public FilledRule(String failMessage) {
        super(failMessage);
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.rules.IRule#validate(java.lang.String)
     */
    @Override
    public boolean validate(String value) {
        if (value != null && !value.trim().equals("")) {
            return true;
        }

        return false;
    }

}
