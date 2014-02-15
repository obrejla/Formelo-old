
package cz.brejla.java.web.formelo.application;


/**
 *
 * @author warden
 */
public class Callback implements ICallback {

    private Object object;

    private String method;

    private Object owner;

    public Callback(Object object, String method, Object owner) {
        try {
            Class.forName(object.getClass().getName()).getDeclaredMethod(method, Class.forName(owner.getClass().getName()));
        } catch (Exception ex) {
            throw new IllegalArgumentException("Callback '" + method + "' is not a valid method.");
        }

        this.object = object;
        this.method = method;
        this.owner = owner;
    }

    /**
     *
     */
    public void call() {
        try {
            Class.forName(this.object.getClass().getName()).getMethod(this.method, Class.forName(this.owner.getClass().getName())).invoke(this.object, this.owner);
        } catch (Exception ex) {
            throw new RuntimeException("An error ocured when calling callback with message: " + ex.getMessage());
        }
    }

}
