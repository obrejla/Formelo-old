
package cz.brejla.java.web.formelo.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.Part;
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
public class FormeloApplicationTest {

    public FormeloApplicationTest() {
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
     * Test of getApplication method, of class FormeloApplication.
     */
    @Test
    public void testGetApplication() {
        FormeloApplication fa = FormeloApplication.getApplication(null, null);
        FormeloApplication faSame = FormeloApplication.getApplication(null, null);

        assertSame(fa, faSame);

        HttpServletRequest rq = new HttpServletRequestImpl();
        HttpServletResponse rs = new HttpServletResponseImpl();

        FormeloApplication faSameAgain = FormeloApplication.getApplication(rq, rs);

        assertSame(fa, faSameAgain);
    }

    /**
     * Test of getRequest method, of class FormeloApplication.
     */
    @Test
    public void testGetRequest() {
        FormeloApplication fa = FormeloApplication.getApplication(null, null);

        assertNull(FormeloApplication.getRequest());

        HttpServletRequest rq = new HttpServletRequestImpl();
        fa = FormeloApplication.getApplication(rq, null);

        assertSame(rq, FormeloApplication.getRequest());
    }

    /**
     * Test of getResponse method, of class FormeloApplication.
     */
    @Test
    public void testGetResponse() {
        FormeloApplication fa = FormeloApplication.getApplication(null, null);

        assertNull(FormeloApplication.getResponse());

        HttpServletResponse rs = new HttpServletResponseImpl();
        fa = FormeloApplication.getApplication(null, rs);

        assertSame(rs, FormeloApplication.getResponse());
    }

    /**
     * Test of getSession method, of class FormeloApplication.
     */
    @Test
    public void testGetSession() {
        assertNull(FormeloApplication.getSession());

        FormeloApplication fa = FormeloApplication.getApplication(null, null);
        assertNull(FormeloApplication.getSession());
        
        HttpServletRequest rq = new HttpServletRequestImpl();
        fa = FormeloApplication.getApplication(rq, null);
        assertTrue(FormeloApplication.getSession() instanceof HttpSession);
    }

    /**
     * Test of setRouter method, of class FormeloApplication.
     */
    @Test
    public void testSetRouter() {
        FormeloApplication fa = FormeloApplication.getApplication(null, null);

        try {
            fa.setRouter(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        Router r = new RouterImpl();
        try {
            fa.setRouter(r);
        } catch (IllegalArgumentException ex) {
            fail();
        }
    }

    /**
     * Test of getRouter method, of class FormeloApplication.
     */
    @Test
    public void testGetRouter() {
        FormeloApplication fa = FormeloApplication.getApplication(null, null);
        Router r = new RouterImpl();

        fa.setRouter(r);

        assertSame(r, fa.getRouter());
    }

    /**
     * Test of setPresenterPackage method, of class FormeloApplication.
     */
    @Test
    public void testSetPresenterPackage() {
        FormeloApplication fa = FormeloApplication.getApplication(null, null);

        try {
            fa.setPresenterPackage(null);
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            fa.setPresenterPackage("myPackage");
        } catch (IllegalArgumentException ex) {
            fail();
        }
    }

    /**
     * Test of run method, of class FormeloApplication.
     */
    @Test
    public void testRun() {
        FormeloApplication fa = FormeloApplication.getApplication(null, null);
        
        try {
            fa.run();
            fail();
        } catch (Exception ex) {}
    }

    public class HttpServletRequestImpl implements HttpServletRequest {

        public String getAuthType() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Cookie[] getCookies() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public long getDateHeader(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getHeader(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Enumeration getHeaders(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Enumeration getHeaderNames() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int getIntHeader(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getMethod() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getPathInfo() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getPathTranslated() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getContextPath() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getQueryString() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getRemoteUser() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isUserInRole(String role) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Principal getUserPrincipal() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getRequestedSessionId() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getRequestURI() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public StringBuffer getRequestURL() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getServletPath() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public HttpSession getSession(boolean create) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public HttpSession getSession() {
            return new HttpSessionImpl();
        }

        public boolean isRequestedSessionIdValid() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isRequestedSessionIdFromCookie() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isRequestedSessionIdFromURL() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isRequestedSessionIdFromUrl() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Object getAttribute(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Enumeration getAttributeNames() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getCharacterEncoding() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int getContentLength() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getContentType() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public ServletInputStream getInputStream() throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getParameter(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Enumeration getParameterNames() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String[] getParameterValues(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Map getParameterMap() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getProtocol() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getScheme() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getServerName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int getServerPort() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public BufferedReader getReader() throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getRemoteAddr() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getRemoteHost() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setAttribute(String name, Object o) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void removeAttribute(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Locale getLocale() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Enumeration getLocales() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isSecure() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public RequestDispatcher getRequestDispatcher(String path) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getRealPath(String path) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int getRemotePort() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getLocalName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getLocalAddr() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int getLocalPort() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void login(String username, String password) throws ServletException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void logout() throws ServletException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Collection<Part> getParts() throws IOException, ServletException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Part getPart(String name) throws IOException, ServletException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public ServletContext getServletContext() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public AsyncContext startAsync() throws IllegalStateException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isAsyncStarted() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isAsyncSupported() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public AsyncContext getAsyncContext() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public DispatcherType getDispatcherType() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    public class HttpServletResponseImpl implements HttpServletResponse {

        public void addCookie(Cookie cookie) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean containsHeader(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String encodeURL(String url) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String encodeRedirectURL(String url) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String encodeUrl(String url) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String encodeRedirectUrl(String url) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void sendError(int sc, String msg) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void sendError(int sc) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void sendRedirect(String location) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setDateHeader(String name, long date) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void addDateHeader(String name, long date) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setHeader(String name, String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void addHeader(String name, String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setIntHeader(String name, int value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void addIntHeader(String name, int value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setStatus(int sc) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setStatus(int sc, String sm) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getCharacterEncoding() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getContentType() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public ServletOutputStream getOutputStream() throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public PrintWriter getWriter() throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setCharacterEncoding(String charset) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setContentLength(int len) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setContentType(String type) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setBufferSize(int size) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int getBufferSize() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void flushBuffer() throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void resetBuffer() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isCommitted() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void reset() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setLocale(Locale loc) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Locale getLocale() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int getStatus() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getHeader(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Collection<String> getHeaders(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Collection<String> getHeaderNames() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    public class HttpSessionImpl implements HttpSession {

        public long getCreationTime() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getId() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public long getLastAccessedTime() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public ServletContext getServletContext() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setMaxInactiveInterval(int interval) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int getMaxInactiveInterval() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public HttpSessionContext getSessionContext() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Object getAttribute(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Object getValue(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Enumeration getAttributeNames() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String[] getValueNames() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setAttribute(String name, Object value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void putValue(String name, Object value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void removeAttribute(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void removeValue(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void invalidate() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isNew() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    public class RouterImpl extends Router {

        public IPresenterRequest processRequest(HttpServletRequest request) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String restoreUrl(IPresenterRequest request) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

}