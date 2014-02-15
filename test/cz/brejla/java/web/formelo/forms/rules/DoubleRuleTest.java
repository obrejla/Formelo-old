
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
public class DoubleRuleTest {

    public DoubleRuleTest() {
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
     * Test of validate method, of class DoubleRule.
     */
    @Test
    public void testValidate() {
        DoubleRule ir = new DoubleRule("");
        
        assertTrue(ir.validate("3"));
        assertTrue(ir.validate("3.6"));
        assertFalse(ir.validate("text"));
        assertFalse(ir.validate(null));
        assertFalse(ir.validate("     "));
    }

    @Test
    public void testGetFailMessage() {
        DoubleRule dr = new DoubleRule("Validation failed.");
        assertEquals("Validation failed.", dr.getFailMessage());

        DoubleRule dr2 = new DoubleRule("");
        assertEquals("", dr2.getFailMessage());
    }

}