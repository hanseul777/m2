<%--
  Created by IntelliJ IDEA.
  User: hanseul
  Date: 2021/08/13
  Time: 12:13 오후
  To change this template use File | Settings | File Templates.
--%>

<!--로그인한 사용자가 볼 수 있는 화면 -->
<%
    //저장한 세션에 name이 저장되어 있는 값이면 로그인이 되어있는 것
    //HttpSession session = request.getSession();

    Object obj = session.getAttribute("member"); //모든값이 다 들어갈 수 있도록 Object를 사용함.
    // obj가 null이면 -> 로그인하지 않았음 -> 로그인페이지로 보내줌.(login.jsp)

    if (obj == null) {
        response.sendRedirect("/login?result=fail");
        return;
    } //obj가 userid인

%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Write Page</h1>
<h2><%=obj%></h2>

<form action="/logout.jsp" method="post">
    <button type="submit">LOGOUT</button>
</form>

</body>
</html>
