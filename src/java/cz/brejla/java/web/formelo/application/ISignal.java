
package cz.brejla.java.web.formelo.application;

/**
 *
 * @author warden
 */
public interface ISignal {

    /**
     * Returns value from signal object.
     *
     * @param name
     * @return
     */
    public String getValue(String name);

    /**
     * Returns signals name.
     *
     * @return
     */
    public String getName();

}
