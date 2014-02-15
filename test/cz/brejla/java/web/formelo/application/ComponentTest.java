
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
public class ComponentTest {

    public ComponentTest() {
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
     * Test of setName method, of class Component.
     */
    @Test
    public void testSetName() {
        String badNameEmpty = "";
        IComponent comp = new ComponentImpl();

        try {
            comp.setName(badNameEmpty);
            fail();
        } catch (IllegalArgumentException ex) {}

        String badNameDia = "ěščřžýáíé";
        try {
            comp.setName(badNameDia);
            fail();
        } catch (IllegalArgumentException ex) {}

        String badNameSpec = "te.st-na_me";
        try {
            comp.setName(badNameSpec);
            fail();
        } catch (IllegalArgumentException ex) {}

        String badNameSpec2 = "__test__";
        try {
            comp.setName(badNameSpec2);
            fail();
        } catch (IllegalArgumentException ex) {}

        String okName = "okName";
        try {
            comp.setName(okName);
        } catch (IllegalArgumentException ex) {
            fail();
        }

        String okNameCapitals = "OkNameCapitals";
        try {
            comp.setName(okNameCapitals);
        } catch (IllegalArgumentException ex) {
            fail();
        }
    }

    /**
     * Test of getName method, of class Component.
     */
    @Test
    public void testGetName() {
        IComponent comp = new ComponentImpl();
        assertNull(comp.getName());

        String name = "MyName";
        comp.setName(name);
        assertEquals(name, comp.getName());
    }

    /**
     * Test of setParent method, of class Component.
     */
    @Test
    public void testSetParent() {
        IComponent comp = new ComponentImpl();

        try {
            comp.setParent(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test of getParent method, of class Component.
     */
    @Test
    public void testGetParent() {
        IComponent comp = new ComponentImpl();

        assertNull(comp.getParent());
    }

    /**
     * Test of runCallbacksOnAttach method, of class Component.
     */
    @Test
    public void testRunCallbacksOnAttach() {

    }

    /**
     * Test of runCallbacksOnBeforeRender method, of class Component.
     */
    @Test
    public void testRunCallbacksOnBeforeRender() {

    }

    /**
     * Test of addCallbackOnAttach method, of class Component.
     */
    @Test
    public void testAddCallbackOnAttach() {
        Component comp = new ComponentImpl();

        try {
            comp.addCallbackOnAttach(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        ICallback cb = new ICallback() {
                public void call() {}
            };

        try {
            comp.addCallbackOnAttach(cb);
        } catch (IllegalArgumentException ex) {
            fail();
        }

        try {
            comp.addCallbackOnAttach(cb);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test of addCallbackOnBeforeRender method, of class Component.
     */
    @Test
    public void testAddCallbackOnBeforeRender() {
        Component comp = new ComponentImpl();

        try {
            comp.addCallbackOnAttach(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        ICallback cb = new ICallback() {
                public void call() {}
            };

        try {
            comp.addCallbackOnAttach(cb);
        } catch (IllegalArgumentException ex) {
            fail();
        }

        try {
            comp.addCallbackOnAttach(cb);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public class ComponentImpl extends Component {

        @Override
        public void processSignal(ISignal signal) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

}