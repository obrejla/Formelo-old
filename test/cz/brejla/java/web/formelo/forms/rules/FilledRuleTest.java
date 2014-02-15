
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
public class FilledRuleTest {

    public FilledRuleTest() {
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
     * Test of validate method, of class FilledRule.
     */
    @Test
    public void testValidate() {
        FilledRule fr = new FilledRule("");

        assertTrue(fr.validate("something"));
        assertFalse(fr.validate(""));
        assertFalse(fr.validate("     "));
        assertFalse(fr.validate(null));
    }

    @Test
    public void testGetFailMessage() {
        IntegerRule ir = new IntegerRule("Validation failed.");
        assertEquals("Validation failed.", ir.getFailMessage());

        IntegerRule ir2 = new IntegerRule("");
        assertEquals("", ir2.getFailMessage());
    }

}