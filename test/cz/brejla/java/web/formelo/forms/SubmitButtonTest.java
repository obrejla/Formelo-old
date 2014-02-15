
package cz.brejla.java.web.formelo.forms;

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
public class SubmitButtonTest {

    public SubmitButtonTest() {
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
     * Test of toString method, of class SubmitButton.
     */
    @Test
    public void testToString() {
        SubmitButton sb = new SubmitButton("myName", "myValue");
        Form form = new Form();
        form.setName("testForm");
        sb.setForm(form);

        assertEquals("<p>\n<input type=\"submit\" name=\"testForm-myName\" value=\"myValue\" />\n</p>\n", sb.toString());
    }

}