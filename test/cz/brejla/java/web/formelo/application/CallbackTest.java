
package cz.brejla.java.web.formelo.application;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author warden
 */
public class CallbackTest {

    public CallbackTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of call method, of class Callback.
     */
    @Test
    public void testCall() {
        ICallback cb = null;
        try {
            cb = new Callback(this, "isOk", this);
        } catch (IllegalArgumentException ex) {
            fail();
        }
        try {
            cb.call();
        } catch (RuntimeException ex) {
            fail();
        }

        try {
            ICallback cb2 = new Callback(this, "isNotOk", this);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            ICallback cb3 = new Callback(this, "", this);
            fail();
        } catch (IllegalArgumentException ex) {

        }
    }

    public void isOk(CallbackTest owner) {}

    public void isNotOk() {}

}