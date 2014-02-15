
package cz.brejla.java.web.formelo.forms;

import cz.brejla.java.web.formelo.forms.rules.IRule;
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
public class InputElementTest {

    public InputElementTest() {
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
     * Test of getLabel method, of class InputElement.
     */
    @Test
    public void testGetLabel() {
        String name = "myName";
        String label = "myLabel";
        InputElement ie = new InputElementImpl(name, label);

        assertEquals(label, ie.getLabel());
    }

    /**
     * Test of addRule method, of class InputElement.
     */
    @Test
    public void testAddRule() {
        InputElement ie = new InputElementImpl("myName", "myLabel");

        try {
            ie.addRule(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        IRule rule = new IRule() {
            public boolean validate(String value) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            public String getFailMessage() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        ie.addRule(rule);
        try {
            ie.addRule(rule);
            fail();
        } catch (IllegalArgumentException ex) {}

        IRule anotherRule = new IRule() {
            public boolean validate(String value) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            public String getFailMessage() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        try {
            ie.addRule(anotherRule);
        } catch (IllegalArgumentException ex) {
            fail();
        }
    }

    /**
     * Test of isValid method, of class InputElement.
     */
    @Test
    public void testIsValid() {
        InputElement ie = new InputElementImpl("myName", "myLabel");
        assertTrue(ie.isValid());
    }

    /**
     * Test of getFailMessages method, of class InputElement.
     */
    @Test
    public void testGetFailMessages() {
        InputElement ie = new InputElementImpl("myName", "myLabel");
        assertTrue(ie.getFailMessages().isEmpty());
    }

    public class InputElementImpl extends InputElement {

        public InputElementImpl(String name, String label) {
            super(name, label);
        }
    }

}