
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
public class SignalTest {

    public SignalTest() {
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
     * Test of addValue method, of class Signal.
     */
    @Test
    public void testAddValue() {
        Signal signal = new Signal("my-signal");
        try {
            signal.addValue("name", "value");
        } catch (Exception ex) {
            fail();
        }

        try {
            signal.addValue("", "value");
            signal.addValue("     ", "value");
            signal.addValue(null, "value");
            fail();
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Test of getValue method, of class Signal.
     */
    @Test
    public void testGetValue() {
        Signal signal = new Signal("my-signal");
        try {
            signal.getValue("");
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            signal.getValue("     ");
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            signal.getValue(null);
            fail();
        } catch (IllegalArgumentException ex) {

        }
        
        String valueName = "my-name";
        String insValue = "my-value";
        String selValue = "";
        signal.addValue(valueName, insValue);
        try {
            selValue = signal.getValue(valueName);
        } catch (IllegalArgumentException ex) {
            fail();
        }

        assertEquals(insValue, selValue);
    }

    /**
     * Test of getName method, of class Signal.
     */
    @Test
    public void testGetName() {
        String signalName = "mySignal";
        ISignal signal = new Signal(signalName);

        assertEquals(signalName, signal.getName());
    }

    /**
     * Test of setType and getType method, of class Signal.
     */
    @Test
    public void testSetGetType() {
        String type = "myType";
        Signal signal = new Signal("mySignal");

        signal.setType(type);
        assertEquals(type, signal.getType());
    }


}