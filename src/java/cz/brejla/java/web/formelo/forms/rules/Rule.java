
package cz.brejla.java.web.formelo.forms.rules;


/**
 *
 * @author warden
 */
abstract public class Rule implements IRule {

    /**
     * Message which can be shown after validation fail.
     */
    private String failMessage;

    /**
     * Creates Rule object with fail message.
     *
     * @param failMessage
     */
    public Rule(String failMessage) {
        this.failMessage = failMessage;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.rules.IRule#getFailMessage() 
     */
    @Override
    public String getFailMessage() {
        return failMessage;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.rules.IRule#validate(java.lang.String)
     */
    abstract public boolean validate(String value);

}
