<%--
  Created by IntelliJ IDEA.
  User: hanseul
  Date: 2021/08/10
  Time: 11:06 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- JSP에서 JSTL을 사용하고 싶을 때 추가 -->
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello2 JSP Page</h1>

<h2>${msg}</h2> <!-- request.Attribute에 썼던 키를 입력 / EL($붙이는 그런 것들) : 안에는 값이 들어간다.-->
<ul>
    <!--items : arr나 coleection 상관없이 지칭-->
    <!-- var : arr의 내용(abcde)가 루프를 돌면서 str로 변한다. -->
    <c:forEach items="${arr}" var="str">
        <li>${str}</li>
    </c:forEach>

</ul>
</body>
</html>
