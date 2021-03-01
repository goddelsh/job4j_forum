<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:if test="${not empty errorMessage}">
    <div style="color:red; font-weight: bold; margin: 30px 0px;">
            ${errorMessage}
    </div>
</c:if>
<c:if test="${not empty topic}">
    <div style="color:red; font-weight: bold; margin: 30px 0px;">
            Пост для топика ${topic.name}
    </div>
    <td><input type="text"  name='topic' value="${topic}" hidden></td>
</c:if>
<form name='post' action="<c:choose>
    <c:when test="${not empty post and not empty post.id}">
        <c:url value='/edit'/>
    </c:when>
    <c:otherwise>
        <c:url value='/create'/>
    </c:otherwise>
</c:choose>  " method='POST'>
    <table>
        <c:if test="${not empty id}">
            <tr>
                <td>Id:</td>
                <td><input type="text" type='text' name='id' value="${id}" readonly></td>

            </tr>
        </c:if>

        <tr>
            <td>Name:</td>
            <td><input type='text' name='name'></td>
        </tr>
        <tr>
            <td>Desc:</td>
            <td><input type='text' name='desc'/></td>
            <c:if test="${not empty topic}">
                <td><input type="text"  name='topic' value="${topic.id}" hidden></td>
            </c:if>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>
