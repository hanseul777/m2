package org.zerock.m2.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HelloController", value = "/hello")
public class HelloController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //동작확인
        System.out.println("Hello Controller doGet");

        String data = "생성된 데이터";
        String[] arr = {"AAA","BBB","CCC","DDD","EEE"}; //보낼 우편의 내용

        request.setAttribute("msg", data); // msg의 내용은 String data인 것. : 우편배달부
        request.setAttribute("arr",arr);// 문자열배열을 담았음. -> hello2.jsp에서 받음

        //request를 JSP로 전달해줌(push해줌) : RequestDispatcher
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/hello2.jsp"); // request를 어디로 보내줄지 경로를 지정(서버내부의 경로)

        dispatcher.forward(request,response); // push해서 보내줌. dispatcher.include는 생각하지말기 안씀


    }

    // get방식만 사용할거라서 doPost() 삭제

}
