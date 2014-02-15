
package cz.brejla.java.web.formelo.forms.rules;


/**
 *
 * @author warden
 */
public class IntegerRule extends Rule {

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.rules.Rule#Rule(java.lang.String) 
     */
    public IntegerRule(String failMessage) {
        super(failMessage);
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.rules.IRule#validate(java.lang.String)
     */
    @Override
    public boolean validate(String value) {
        try {
            Integer.parseInt(value);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
