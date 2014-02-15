<?xml version="1.0" encoding="utf-8"?>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="cs" lang="cs">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Graph</title>
    </head>
    <body>
        <h1>Koláčový graf</h1>
   	<p>
            <img src="?presenter=Viewer&action=show" width="<%= request.getAttribute("width") %>" height="<%= request.getAttribute("height") %>" alt="Kruhový graf" />
        </p>
    </body>
</html>
