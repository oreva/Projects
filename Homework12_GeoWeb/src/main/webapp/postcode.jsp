<%-- 
    Document   : postcode
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
        
        <h1>Search postcodes:</h1>
        <form id="searchForm" action="postcode.jsp" method="get">
            <div>
                Search city by 
                <input id="countryInput" type="hidden" name="country" value="${param.country}"/>
                region: <input type="search" name="region" value="${param.region}">
                and city: <input type="search" name="city" value="${param.city}">
                and number: <input type="search" name="postcode" value="${param.postcode}">
                <input type="submit" value="Search"/>
            </div>
        </form>
                
        <sql:query dataSource="${db}" var="postcodes">
            select *
            from postcode
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
            and city_id in
            (
                select id
                from city
                where name like '%<c:out value="${param.city}"/>%'
            )
            and value like '%<c:out value="${param.postcode}"/>%'
            
        </sql:query>
            
        <h1>Postcodes:</h1>
        <table border="1"> 
            <tr> 
                <th>ID</th> 
                <th>Postcode</th> 
                <th>Latitude</th> 
                <th>Longitude</th> 
                <th>Accuracy</th> 
            </tr>
            <c:forEach var="row" items="${postcodes.rows}"> 
                <tr> 
                    <td><c:out value="${row.id}"/></td> 
                    <td><c:out value="${row.value}"/></td>
                    <td><c:out value="${row.latitude}"/></td>
                    <td><c:out value="${row.longitude}"/></td>
                    <td><c:out value="${row.accuracy}"/></td>
                </tr> 
             </c:forEach> 
        </table>
    </body>
</html>
