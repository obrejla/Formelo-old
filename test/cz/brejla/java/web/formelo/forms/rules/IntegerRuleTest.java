
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
public class IntegerRuleTest {

    public IntegerRuleTest() {
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
     * Test of validate method, of class IntegerRule.
     */
    @Test
    public void testValidate() {
        IntegerRule ir = new IntegerRule("");
        
        assertTrue(ir.validate("3"));
        assertFalse(ir.validate("3.6"));
        assertFalse(ir.validate("text"));
        assertFalse(ir.validate(null));
        assertFalse(ir.validate("     "));
    }

    @Test
    public void testGetFailMessage() {
        IntegerRule ir = new IntegerRule("Validation failed.");
        assertEquals("Validation failed.", ir.getFailMessage());

        IntegerRule ir2 = new IntegerRule("");
        assertEquals("", ir2.getFailMessage());
    }

}