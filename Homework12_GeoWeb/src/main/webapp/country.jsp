<%-- 
    Document   : country
    Created on : Oct 26, 2013, 6:54:57 PM
    Author     : Olga Reva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<sql:query dataSource="jdbc/postgres" var="countries">
    select *
    from country
    where iso_code like '%<c:out value="${param.code}"/>%'
    and name like '%<c:out value="${param.name}"/>%'
</sql:query>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Countries</title>
    </head>
    <body>
        <h1>Search countries:</h1>
        <form action="country.jsp" method="get">
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
            <c:forEach var="row" items="${countries.rows}"> 
                <tr> 
                    <td><c:out value="${row.id}"/></td> 
                    <td><c:out value="${row.iso_code}"/></td> 
                </tr> 
             </c:forEach> 
        </table>
    </body>
</html>
