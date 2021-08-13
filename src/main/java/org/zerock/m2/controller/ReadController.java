package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MemberDTO;
import org.zerock.m2.dto.MsgDTO;
import org.zerock.m2.service.MsgService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name = "ReadController", value = "/msg/read")
public class ReadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //로그인 체크 로직
        HttpSession session = request.getSession();
        Object memberobj = session.getAttribute("member"); // 로그인을 안하면 member값이 없음
        //로그인 관련 정보 없음 - 로그인 안한 사용자
        if (memberobj == null) {
            response.sendRedirect("/login"); // login페이지가 아니고 login URL로 보내주니까 login.jsp(x)
            return; // redirect 후 뭔가 더 실행하지 않아야 하기 때문에 return으로 끊어준다.
        }



        // request.getParameter는 String만 가능함.
        Long mno = Long.parseLong(request.getParameter("mno"));

        MsgDTO msgDTO = MsgService.INSTANCE.read(mno);

        //택배담아줌
        request.setAttribute("dto", msgDTO);
        request.getRequestDispatcher("/WEB-INF/msg/read.jsp").forward(request,response);

    }
}
