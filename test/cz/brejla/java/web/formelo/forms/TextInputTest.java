
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
public class TextInputTest {

    public TextInputTest() {
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
     * Test of toString method, of class TextInput.
     */
    @Test
    public void testToString() {
        TextInput ti = new TextInput("myName", "myLabel");
        Form form = new Form();
        form.setName("testForm");
        ti.setForm(form);

        assertEquals("<p>\n<label for=\"myName\">myLabel</label>\n<input type=\"text\" name=\"testForm-myName\" id=\"myName\" value=\"\" class=\"\" />\n</p>", ti.toString());

        ti.addClass("error");
        assertEquals("<p>\n<label for=\"myName\">myLabel</label>\n<input type=\"text\" name=\"testForm-myName\" id=\"myName\" value=\"\" class=\"error\" />\n</p>", ti.toString());

        ti.addClass("second");
        assertEquals("<p>\n<label for=\"myName\">myLabel</label>\n<input type=\"text\" name=\"testForm-myName\" id=\"myName\" value=\"\" class=\"error, second\" />\n</p>", ti.toString());
    }

}