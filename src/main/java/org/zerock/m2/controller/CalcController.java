package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalcController", value = "/calc") // web에서 calc를 호출하면 get방식이 나옴 : calcInput
@Log4j2
public class CalcController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("CalcController doGet........................");

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/calcInput.jsp"); // request를 어디로 보내줄지 경로를 지정(서버내부의 경로)

        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("doPost........................");

        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");
        String oper = request.getParameter("oper");

        //변수를 선언했는데 입력화면을 통하지않으면 변수값을 확인 할 수 없음 -> POST방식 : 화면을 만들어서 전송값을 만들어 줘야한다.
        // PostMan을 사용하면 화면개발없이 바로 POST방식의 개발이 가능하다!

        log.info("num1Str: " + num1Str);
        log.info("num2Str: " + num2Str);
        log.info("oper: " + oper);



        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/calcResult.jsp");

        dispatcher.forward(request,response);
    }
}
