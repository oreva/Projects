<%-- 
    Document   : country
    Created on : Oct 26, 2013, 6:54:57 PM
    Author     : Olga Reva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Countries</title>
    </head>
    <body>
        <h1>Search countries:</h1>
        <form action="countryServlet" method="get">
            <div>
                Search countries by 
                code: <input type="search" name="code" value="${param.code}">
                and name: <input type="search" name="name" value="${param.name}">
                <input type="submit" value="Search"/>
            </div>
        </form>
        <h1>Countries:</h1>
        <table border="1"> 
            <tr> 
                <th>ID</th> 
                <th>ISO Code</th> 
            </tr>
            <c:forEach var="row" items="${countries}">
                <tr> 
                    <td><c:out value="${row.id}"/></td> 
                    <td><c:out value="${row.isoCode}"/></td>
                </tr> 
             </c:forEach> 
        </table>
    </body>
</html>
