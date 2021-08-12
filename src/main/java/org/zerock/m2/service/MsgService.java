package org.zerock.m2.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dao.MsgDAO;
import org.zerock.m2.dto.MsgDTO;

import java.util.List;
import java.util.Map;

@Log4j2
public enum MsgService { //service 계층은 고객의 요구사항을 반영한다.
    INSTANCE;

    public void remove(MsgDTO msgDTO) throws RuntimeException {
        MsgDAO.INSTANCE.delete(msgDTO);
    }

    public void register(MsgDTO msgDTO) throws RuntimeException {

        log.info("service register........." + msgDTO); // 로그확인 + 파라미터확인

        MsgDAO.INSTANCE.insert(msgDTO);
    }

    public MsgDTO read(Long mno) throws RuntimeException {
        return MsgDAO.INSTANCE.select(mno);
    }

    //enum은 외부에서 생성자를 선언 x (내부에서만 객체를 생성가능)
    public Map<String, List<MsgDTO>> getList(String user)throws RuntimeException {
        long start = System.currentTimeMillis(); // 시간출력

        //데이터 가져오기 -> 누구의? user의 데이터 : 파라미터 추가 String user
        Map<String, List<MsgDTO>> result = MsgDAO.INSTANCE.selectList(user);

        //소요시간을 확인
        long end = System.currentTimeMillis();
        log.info("TIME : " + (end-start));

        return result;

    }// Controller와 DAO사이의 아교역할
}
