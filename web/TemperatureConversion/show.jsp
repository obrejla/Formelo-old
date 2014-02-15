<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Temperature conversion</title>
        <style type="text/css">
        <!--
        .error {
            border: 3px solid red;
        }
        -->
        </style>
    </head>
    <body>
        <h1>Temperature conversion</h1>
        <%= request.getAttribute("ConversionForm") %>
        <p>
        Konvertovaná teplota: <%= request.getAttribute("convertedTemperature") %>
        </p>
    </body>
</html>
