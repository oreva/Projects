<%-- 
    Document   : country
    Created on : Oct 26, 2013, 6:54:57 PM
    Author     : Olga Reva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<sql:setDataSource var="db" driver="org.postgresql.Driver"
     url="jdbc:postgresql://localhost:5432/study00"
     user="study"  password="study"/>

<sql:query dataSource="${db}" var="countries">
    select *
    from country
</sql:query>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regions</title>
    </head>
    <body>
        <h1>Select country:</h1>
        <select id="countryMenu" onchange="countryInput.value = countryMenu.options[countryMenu.selectedIndex].value; document.searchForm.submit();">
            <option value="">Please, select country...</option>
            <c:forEach var="row" items="${countries.rows}">
                <c:choose>
                    <c:when test="${row.iso_code == param.country}">
                        <option value="${row.iso_code}" selected="selected">${row.iso_code}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${row.iso_code}">${row.iso_code}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        
        <h1>Search regions:</h1>
        <form id="searchForm" action="region.jsp" method="get">
            <div>
                Search regions by 
                <input id="countryInput" type="hidden" name="country" value="${param.country}"/>
                name: <input type="search" name="name" value="${param.name}">
                <input type="submit" value="Search"/>
            </div>
        </form>
                
        <sql:query dataSource="${db}" var="regions">
            select *
            from region
            where country_id in
            (
                select id
                from country
                where iso_code = '<c:out value="${param.country}"/>'
            )
            and name like '%<c:out value="${param.name}"/>%'
            
        </sql:query>
            
        <h1>Regions:</h1>
        <table border="1"> 
            <tr> 
                <th>ID</th> 
                <th>Name</th> 
            </tr>
            <c:forEach var="row" items="${regions.rows}"> 
                <tr> 
                    <td><c:out value="${row.id}"/></td> 
                    <td><c:out value="${row.name}"/></td> 
                </tr> 
             </c:forEach> 
        </table>
    </body>
</html>
