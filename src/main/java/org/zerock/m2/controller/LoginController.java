package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MemberDTO;
import org.zerock.m2.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//GET일 때는 아이디와 패스워드를 입력
//POST일 때는 로그인 처리 -> /msg/list
@Log4j2
@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET login..............");

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        // 로그인을 하는페이지 생성 완료
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //파라미터mid mpw() 수집
        // 정할 것-> mid:사용자의 아이디/mpw:사용자의 패스워드/mname:사용자의 이름/nickname:닉네임/joindate:가입일/moddate:수정일
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");

        log.info("--------------------------");

        log.info("mid: " + mid);
        log.info("--------------------------");

        log.info("mpw: "+ mpw);

        log.info("--------------------------");

        //사용자 정보가 필요함 -> 단순한 이름뿐만 아니라 닉네임 이메일 등 여러개의 정보를 담아야 함 -> 객체로 담는다.
        //=> 실패 : 사용자의 정보가 없다고 뜸. 다시 GET방식으로 로그인창에 보낸다. -> 값을 추가하도록 한다.
//        MemberDTO memberDTO = MemberDTO.builder()
//                .mid(mid)
//                .mpw(mpw)
//                .mname("사용자" + mid)
//                .nickname("사용자" + mid)
//                .build();

        try{
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid,mpw);
            //세션에 setAttribute("member", 사용자 정보)
            HttpSession session = request.getSession();
            session.setAttribute("member", memberDTO);

            // /msg/list 로 리다이렉트 시킨다. -> 로그인하면 일단 목록부터 표시하는 것
            response.sendRedirect("/msg/list");

        }catch(Exception e){
            log.error("Login Fail.." + e.getMessage());
            response.sendRedirect("/login?result=fail");
        }
        //------------------여기까지 오면 성공하는 시나리오-------------------------------
    }
}
