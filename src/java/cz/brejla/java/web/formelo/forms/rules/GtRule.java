
package cz.brejla.java.web.formelo.forms.rules;


/**
 *
 * @author warden
 */
public class GtRule extends Rule {

    /**
     * Number, when double values are compared.
     */
    private Double doubleNumber;

    /**
     * Number, when integer values are compared.
     */
    private Integer integerNumber;

    /**
     * Creates GtRule object with fail message and number for camparison.
     *
     * @param failMessage
     * @param number
     */
    public GtRule(String failMessage, int number) {
        super(failMessage);

        integerNumber = number;
    }

    /**
     * Creates GtRule object with fail message and number for camparison.
     * 
     * @param failMessage
     * @param number
     */
    public GtRule(String failMessage, double number) {
        super(failMessage);

        doubleNumber = number;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.rules.IRule#getFailMessage()
     */
    @Override
    public String getFailMessage() {
        if (integerNumber != null) {
            return String.format(super.getFailMessage(), integerNumber);
        } else {
            return String.format(super.getFailMessage(), doubleNumber);
        }
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.forms.rules.IRule#validate(java.lang.String)
     */
    @Override
    public boolean validate(String value) {
        try {
            if (integerNumber != null) {
                Double v = Double.parseDouble(value);

                if (v > integerNumber) {
                    return true;
                }
            } else {
                Double v = Double.parseDouble(value);

                if (v > doubleNumber) {
                    return true;
                }
            }

            return false;
        } catch (Exception ex) {
            return false;
        }
    }

}
