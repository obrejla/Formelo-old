<%@page import="cz.brejla.java.web.formelo.application.*"  %>
<%

FormeloApplication app = FormeloApplication.getApplication(request, response);

IRouter router = new SimpleRouter("Graph", "insert");
app.setRouter(router);
app.setPresenterPackage("cz.brejla.pjj.web.graph.presenters");

app.run();

// IllegalStateException: getOutputStream() has already been called for this response
// out.clear();
%>