
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
public class FormElementTest {

    public FormElementTest() {
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
     * Test of getName method, of class FormElement.
     */
    @Test
    public void testGetName() {
        String name = "myName";
        FormElement fe = new FormElementImpl(name);

        assertEquals(name, fe.getName());

        try {
            new FormElementImpl("__bad__");
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            new FormElementImpl("very-bad");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test of setValue method, of class FormElement.
     */
    @Test
    public void testSetValue() {
        String name = "myName";
        FormElement fe = new FormElementImpl(name);

        try {
            fe.setValue(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test of getValue method, of class FormElement.
     */
    @Test
    public void testGetValue() {
        String name = "myName";
        String value = "myValue";
        FormElement fe = new FormElementImpl(name);

        assertEquals("", fe.getValue());

        fe.setValue(value);
        assertEquals(value, fe.getValue());
    }

    /**
     * Test of setForm method, of class FormElement.
     */
    @Test
    public void testSetForm() {
        String name = "myName";
        FormElement fe = new FormElementImpl(name);

        try {
            fe.setForm(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        Form form = new Form();
        form.setName("myForm");
        fe.setForm(form);
        assertSame(form, fe.getForm());

        Form anotherForm = new Form();
        form.setName("anotherForm");
        assertNotSame(anotherForm, fe.getForm());
    }

    /**
     * Test of addClass method, of class FormElement.
     */
    @Test
    public void testAddClass() {
        String name = "myName";
        FormElement fe = new FormElementImpl(name);

        try {
            fe.addClass(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        assertSame(fe, fe.addClass("testClass"));
    }

    /**
     * Test of isValid method, of class FormElement.
     */
    @Test
    public void testIsValid() {
        String name = "myName";
        FormElement fe = new FormElementImpl(name);

        assertTrue(fe.isValid());
    }

    /**
     * Test of getForm method, of class FormElement.
     */
    @Test
    public void testGetForm() {
        String name = "myName";
        FormElement fe = new FormElementImpl(name);

        assertNull(fe.getForm());

        Form form = new Form();
        form.setName("myForm");
        fe.setForm(form);
        assertSame(form, fe.getForm());
    }

    /**
     * Test of getClasses method, of class FormElement.
     */
    @Test
    public void testGetClasses() {
        String name = "myName";
        FormElement fe = new FormElementImpl(name);

        assertTrue(fe.getClasses().isEmpty());
    }

    public class FormElementImpl extends FormElement {

        public FormElementImpl(String name) {
            super(name);
        }

        public FormElementImpl(String name, String value) {
            super(name, value);
        }
    }

}