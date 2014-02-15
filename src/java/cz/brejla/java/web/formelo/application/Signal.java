
package cz.brejla.java.web.formelo.application;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author warden
 */
public class Signal implements ISignal {

    /**
     * Map of names => values of signal object.
     */
    private Map<String, String> values = new HashMap<String, String>();

    /**
     * Name of signal.
     */
    private String name;

    /**
     * Signal type.
     */
    private String type;

    /**
     * Creates new signal object.
     */
    public Signal(String name) {
        this.name = name;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ISignal#getValue(java.lang.String)
     */
    @Override
    public String getValue(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name of the attribute can not be empty or null.");
        }

        return values.get(name);
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ISignal#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ISignal#addValue(java.lang.String, java.lang.String)
     */
    public void addValue(String name, String value) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name of the attribute can not be empty or null.");
        }
        values.put(name, value);
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ISignal#setType(java.lang.String)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * (Java-doc)
     *
     * @see cz.brejla.java.web.formelo.application.ISignal#getType()
     */
    public String getType() {
        return type;
    }

}
