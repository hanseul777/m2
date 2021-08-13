<%--
  Created by IntelliJ IDEA.
  User: hanseul
  Date: 2021/08/13
  Time: 12:39 오후
  To change this template use File | Settings | File Templates.
--%>
<%
    //HttpSession session = request.getSession();

    //세션만료를 만들어줌.
    session.removeAttribute("name"); // name이라는 값을 가진애를 지운상태에서 무효화를 시켜줘야 함. 그래서 먼저 선언
    session.invalidate(); // 이세션은 쓰레기니까 버려! 무효화해주는 것
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
