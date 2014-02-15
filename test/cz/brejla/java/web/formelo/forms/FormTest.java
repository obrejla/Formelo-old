
package cz.brejla.java.web.formelo.forms;

import cz.brejla.java.web.formelo.application.Callback;
import cz.brejla.java.web.formelo.application.ICallback;
import cz.brejla.java.web.formelo.application.Signal;
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
public class FormTest {

    public FormTest() {
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
     * Test of toString method, of class Form.
     */
    @Test
    public void testToString() {
        Form form = new Form();
        form.setAction("/index.jsp");
        form.setMethod("get");
        assertEquals("<form action=\"/index.jsp\" method=\"get\">\n</form>\n", form.toString());
    }

    /**
     * Test of processSignal method, of class Form.
     */
    @Test
    public void testProcessSignal() {
        Form form = new Form();
        String formName = "MyForm";
        form.setName(formName);

        try {
            form.processSignal(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            form.processSignal(new Signal("AnotherSignalName"));
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test of addElement method, of class Form.
     */
    @Test
    public void testAddElement() {
        Form form = new Form();
        try {
            form.addElement(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        FormElement element = new TextInput("textInput", "Label:");
        try {
            form.addElement(element);
        } catch (IllegalArgumentException ex) {
            fail();
        }

        try {
            form.addElement(element);
            fail();
        } catch (IllegalArgumentException ex) {}

        FormElement elementReturned = new TextInput("textInputReturned", "Label:");
        assertSame(elementReturned, form.addElement(elementReturned));
    }

    /**
     * Test of addInputElement method, of class Form.
     */
    @Test
    public void testAddInputElement() {
        Form form = new Form();
        try {
            form.addInputElement(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        IInputElement element = new TextInput("textInput", "Label:");
        try {
            form.addInputElement(element);
        } catch (IllegalArgumentException ex) {
            fail();
        }

        try {
            form.addInputElement(element);
            fail();
        } catch (IllegalArgumentException ex) {}

        IInputElement elementReturned = new TextInput("textInputReturned", "Label:");
        assertSame(elementReturned, form.addInputElement(elementReturned));
    }

    /**
     * Test of getElement method, of class Form.
     */
    @Test
    public void testGetElement() {
        Form form = new Form();
        String elementName = "textInput";
        FormElement element = new TextInput(elementName, "Label:");
        form.addElement(element);

        assertSame(element, form.getElement(elementName));

        assertNull(form.getElement("nonExistingElement"));
    }

    /**
     * Test of getAction method, of class Form.
     */
    @Test
    public void testSetGetAction() {
        Form form = new Form();
        assertEquals("", form.getAction());

        String action = "/index.jsp";
        form.setAction(action);
        assertEquals(action, form.getAction());
    }

    /**
     * Test of getMethod method, of class Form.
     */
    @Test
    public void testSetGetMethod() {
        Form form = new Form();
        assertEquals(Form.METHOD_POST, form.getMethod());

        String methodGet = Form.METHOD_GET;
        form.setMethod(methodGet);
        assertEquals(methodGet, form.getMethod());

        String methodPost = Form.METHOD_POST;
        form.setMethod(methodPost);
        assertEquals(methodPost, form.getMethod());

        String methodNull = null;
        try {
            form.setMethod(methodNull);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        String methodRubbish = "rubbish";
        try {
            form.setMethod(methodRubbish);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        String methodEmpty = "     ";
        try {
            form.setMethod(methodEmpty);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of addCallbackOnSubmit method, of class Form.
     */
    @Test
    public void testAddCallbackOnSubmit() {
        Form form = new Form();
        ICallback callback = new Callback(this, "onlyTestMethod", this);


        assertSame(form, form.addCallbackOnSubmit(callback));

        try {
            form.addCallbackOnSubmit(callback);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    public void onlyTestMethod(FormTest formTest) {};

}