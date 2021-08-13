package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MemberDTO;
import org.zerock.m2.dto.MsgDTO;
import org.zerock.m2.service.MsgService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j2
@WebServlet(name = "ListController", value = "/msg/list")
public class ListController extends HttpServlet {

    //멤버변수(조력자)가 들어감.
//    private MsgService msgService;   <- 정석

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // whom 파라미터 수집
//        String user = request.getParameter("whom");

        log.info("list controller doGet.............");

        //로그인 체크 로직
        HttpSession session = request.getSession();
        Object memberobj = session.getAttribute("member"); // 로그인을 안하면 member값이 없음
        //로그인 관련 정보 없음 - 로그인 안한 사용자
        if (memberobj == null) {
            response.sendRedirect("/login"); // login페이지가 아니고 login URL로 보내주니까 login.jsp(x)
            return; // redirect 후 뭔가 더 실행하지 않아야 하기 때문에 return으로 끊어준다.
        }
        MemberDTO memberDTO = (MemberDTO)memberobj; // down casting:영임쌤이 말한 강제형변환
        String user = memberDTO.getMid();

//        Map<String, List<MsgDTO>> result = MsgService.INSTANCE.getList("user");
        Map<String, List<MsgDTO>> result = MsgService.INSTANCE.getList(user);


        //jsp(view)로 택배 전달 -> request.setAttribute를 이용
        //따로따로 전달하는게 화면에서 처리하기에 편하니까 최대한 펼쳐서 전달한다.
        request.setAttribute("Receive",result.get("R")); // 실제로 R에 들어가는건 List<MsgDTO>
        request.setAttribute("Send", result.get("S"));

        request.getRequestDispatcher("/WEB-INF/msg/list.jsp").forward(request,response);

    }
}
