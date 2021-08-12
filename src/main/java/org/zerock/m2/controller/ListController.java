package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;
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
        String user = request.getParameter("whom");

        log.info("list controller doGet.............");
//        Map<String, List<MsgDTO>> result = MsgService.INSTANCE.getList("user");
        Map<String, List<MsgDTO>> result = MsgService.INSTANCE.getList(user);


        //jsp(view)로 택배 전달 -> request.setAttribute를 이용
        //따로따로 전달하는게 화면에서 처리하기에 편하니까 최대한 펼쳐서 전달한다.
        request.setAttribute("Receive",result.get("R")); // 실제로 R에 들어가는건 List<MsgDTO>
        request.setAttribute("Send", result.get("S"));

        request.getRequestDispatcher("/WEB-INF/msg/list.jsp").forward(request,response);

    }
}
