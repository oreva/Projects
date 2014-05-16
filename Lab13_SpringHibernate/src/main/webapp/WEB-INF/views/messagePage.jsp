<%--
  Created by IntelliJ IDEA.
  User: Olga Reva
  Date: 5/14/14
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>It's the message page!</title>
</head>
<body>
    <h2>Messages</h2>
    <table>
        <tr>
            <td>User Email</td>
            <td>User Phone</td>
            <td>Message</td>
        </tr>
        <c:forEach items="${messageList}" var="row">
            <tr>
                <td>${row.email}</td>
                <td>${row.phone}</td>
                <td>${row.message}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Create New Message</h2>
    <sf:form action="message"
          title="Create your message"
          modelAttribute="message"
          method="POST">
        <table>
            <tr>
                <td>Phone:</td>
                <td><sf:input path="phone" size="50"/> </td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><sf:input path="email" size="50"/></td>
            </tr>
            <tr>
                <td>Message:</td>
                <td><sf:textarea path="message"/></td>
            </tr>
            <tr>
                <input type="submit" value="Send"/>
            </tr>
        </table>
    </sf:form>
</body>
</html>