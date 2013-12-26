<%-- 
    Document   : city
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
        
        <h1>Search cities:</h1>
        <form id="searchForm" action="city.jsp" method="get">
            <div>
                Search city by 
                <input id="countryInput" type="hidden" name="country" value="${param.country}"/>
                region: <input type="search" name="region" value="${param.region}">
                and name: <input type="search" name="name" value="${param.name}">
                <input type="submit" value="Search"/>
            </div>
        </form>
                
        <sql:query dataSource="${db}" var="cities">
            select *
            from city
            where country_id in
            (
                select id
                from country
                where iso_code = '<c:out value="${param.country}"/>'
            )
            and region_id in
            (
                select id
                from region
                where name like '%<c:out value="${param.region}"/>%'
            )
            and name like '%<c:out value="${param.name}"/>%'
            
        </sql:query>
            
        <h1>Cities:</h1>
        <table border="1"> 
            <tr> 
                <th>ID</th> 
                <th>Name</th> 
            </tr>
            <c:forEach var="row" items="${cities.rows}"> 
                <tr> 
                    <td><c:out value="${row.id}"/></td> 
                    <td><c:out value="${row.name}"/></td> 
                </tr> 
             </c:forEach> 
        </table>
    </body>
</html>
