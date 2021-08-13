<%--
  Created by IntelliJ IDEA.
  User: hanseul
  Date: 2021/08/13
  Time: 12:10 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Fake Login</h1>
<%
    String userid = request.getParameter("userid"); // login에서 입력한 userid값을 받아옴.(공간에 저장)

    //HttpSession session = request.getSession(); 원래는 선언해줘야하는데 jsp는 이미 가지고 있음
    session.setAttribute("name",userid);//session의 타입을 보면 HttpSession타입인 것을 확인할 수 있음
    // 현재 저장소가 없기 때문에 공간먼저 생김(세션저장소 생김)
%>
<h2><%=userid%>님 로그인 되었음</h것>
</body>
</html>
