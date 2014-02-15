
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
public class PresenterTest {

    public PresenterTest() {
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
     * Test of run method, of class Presenter.
     */
    @Test
    public void testRun() {
    }

    /**
     * Test of getName method, of class Presenter.
     */
    @Test
    public void testGetName() {
    }

    /**
     * Test of getRequest method, of class Presenter.
     */
    @Test
    public void testGetRequest() {
    }

    /**
     * Test of getRouter method, of class Presenter.
     */
    @Test
    public void testGetRouter() {
    }

    /**
     * Test of processSignal method, of class Presenter.
     */
    @Test
    public void testProcessSignal() {
    }

    /**
     * Test of startup method, of class Presenter.
     */
    @Test
    public void testStartup() {
    }

    /**
     * Test of destruct method, of class Presenter.
     */
    @Test
    public void testDestruct() {
    }

    /**
     * Test of redirect method, of class Presenter.
     */
    @Test
    public void testRedirect() {
    }

    /**
     * Test of terminate method, of class Presenter.
     */
    @Test
    public void testTerminate() {
        Presenter p = new PresenterImpl();

        try {
            p.terminate();
            fail();
        } catch (RedirectingException ex) {}
    }

    public class PresenterImpl extends Presenter {
    }

}