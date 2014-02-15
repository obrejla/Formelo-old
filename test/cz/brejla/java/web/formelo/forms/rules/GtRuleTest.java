
package cz.brejla.java.web.formelo.forms.rules;

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
public class GtRuleTest {

    public GtRuleTest() {
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
     * Test of getFailMessage method, of class GtRule.
     */
    @Test
    public void testGetFailMessage() {
        GtRule gr = new GtRule("Must be greater then %d", 3);
        assertEquals("Must be greater then 3", gr.getFailMessage());

        GtRule gr2 = new GtRule("Must be greater then %f", 3.5);
        assertEquals("Must be greater then 3,500000", gr2.getFailMessage());
    }

    /**
     * Test of validate method, of class GtRule.
     */
    @Test
    public void testValidate() {
        GtRule gr = new GtRule("", 3);

        assertTrue(gr.validate("7"));
        assertFalse(gr.validate("2"));
        assertFalse(gr.validate("3"));

        GtRule gr2 = new GtRule("", 3.5);

        assertTrue(gr2.validate("7"));
        assertTrue(gr2.validate("6.4"));
        assertFalse(gr2.validate("2"));
        assertFalse(gr2.validate("3.5"));
        assertFalse(gr2.validate("6,4"));
        assertFalse(gr2.validate("2,5"));
    }

}