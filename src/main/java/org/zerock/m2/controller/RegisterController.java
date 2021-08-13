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
@WebServlet(name = "RegisterController", value = "/msg/register")
public class RegisterController extends HttpServlet {

    //입력화면 - doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("등록 화면 조회");

        //등록화면을 보기 전에 로그인이 필요하다 -> 위치 잘 보기! (ListController에서 복붙 : 반복됨 -> 나중에 filter로 빼줌)
        //로그인 체크 로직
        HttpSession session = request.getSession();
        Object memberobj = session.getAttribute("member"); // 로그인을 안하면 member값이 없음
        //로그인 관련 정보 없음 - 로그인 안한 사용자
        if (memberobj == null) {
            response.sendRedirect("/login"); // login페이지가 아니고 login URL로 보내주니까 login.jsp(x)
            return; // redirect 후 뭔가 더 실행하지 않아야 하기 때문에 return으로 끊어준다.
        }
        MemberDTO memberDTO = (MemberDTO)memberobj; // down casting:영임쌤이 말한 강제형변환

        request.getRequestDispatcher("/WEB-INF/msg/register.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //postman으로 log확인할 때 한글을 넣어서 깨지는지 확인하고 추가
        request.setCharacterEncoding("UTF-8");

        // 던져지는 파라미터들 : who, whom, content
        // 이 파라미터들을 수집해서 MsgDTO를 생성
        String who = request.getParameter("who");
        String whom = request.getParameter("whom");
        String content = request.getParameter("content");

        log.info("who: " + who);
        log.info("whom : " + whom );
        log.info("content : " + content );

        MsgDTO msgDTO = MsgDTO.builder().who(who).whom(whom).content(content).build();

        //MsgService의 register()를 호출
        MsgService.INSTANCE.register(msgDTO);

        //리다이렉트 문법 : response.sendRedirect(보낼 URL입력);
        response.sendRedirect("/msg/list?whom=" + who); // whom과 who 주의하기!!

    }
}
