
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
public class ComponentContainerTest {

    public ComponentContainerTest() {
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
     * Test of addComponent method, of class ComponentContainer.
     */
    @Test
    public void testAddComponent() {
        ComponentContainer cc = new ComponentContainerImpl();
        Component c = new ComponentImpl();

        try {
            cc.addComponent(c);
            fail();
        } catch (IllegalArgumentException ex) {}

        c.setName("myComponent");

        try {
            cc.addComponent(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            cc.addComponent(c);
        } catch (IllegalArgumentException ex) {
            fail();
        }

        try {
            cc.addComponent(c);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test of getComponent method, of class ComponentContainer.
     */
    @Test
    public void testGetComponent() {
        ComponentContainer cc = new ComponentContainerImpl();
        Component c = new ComponentImpl();
        String compName = "myComponent";
        c.setName(compName);

        cc.addComponent(c);

        try {
            cc.getComponent(compName);
        } catch (IllegalArgumentException ex) {
            fail();
        }

        try {
            cc.getComponent("nonExistingComponent");
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            cc.getComponent(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test of getComponents method, of class ComponentContainer.
     */
    @Test
    public void testGetComponents() {
        ComponentContainer cc = new ComponentContainerImpl();

        assertTrue(cc.getComponents().isEmpty());

        Component c = new ComponentImpl();
        c.setName("myComponent");
        cc.addComponent(c);

        assertFalse(cc.getComponents().isEmpty());

        Component ac = new ComponentImpl();
        ac.setName("anotherComponent");
        cc.addComponent(ac);

        assertEquals(2, cc.getComponents().size());
    }

    /**
     * Test of removeComponent method, of class ComponentContainer.
     */
    @Test
    public void testRemoveComponent() {
        ComponentContainer cc = new ComponentContainerImpl();
        Component c = new ComponentImpl();
        String compName = "myComponent";
        c.setName(compName);

        try {
            cc.removeComponent(compName);
            fail();
        } catch (IllegalArgumentException ex) {}

        cc.addComponent(c);
        try {
            cc.removeComponent(compName);
        } catch (IllegalArgumentException ex) {
            fail();
        }

        try {
            cc.removeComponent(compName);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test of runBeforeRenderCallbacks method, of class ComponentContainer.
     */
    @Test
    public void testRunBeforeRenderCallbacks() {
    }

    public class ComponentContainerImpl extends ComponentContainer {
    }

    public class ComponentImpl extends Component {

        @Override
        public void processSignal(ISignal signal) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

}