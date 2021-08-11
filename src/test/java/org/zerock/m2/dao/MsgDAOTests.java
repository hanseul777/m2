package org.zerock.m2.dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.m2.dto.MsgDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Log4j2
public class MsgDAOTests {

    @Test
    public void testInsertDummies() {

        //i가 1부터 시작해서 100까지 간다는 for 루프를 람다식으로 표현
        IntStream.rangeClosed(1,100).forEach(i -> {
            String who = "user"+(int)(Math.random() * 10);
            String whom = "user"+(int)(Math.random() * 10);
            String content = "Sample .." +i;

            MsgDTO dto = MsgDTO.builder().who(who).whom(whom).content(content).build();

            MsgDAO.INSTANCE.insert(dto);
        });

    }
    @Test
    public void testList(){
//        MsgDAO.INSTANCE.selectList("user5");  log 뜨는지 확
        Map<String, List<MsgDTO>> result = MsgDAO.INSTANCE.selectList("user5");

        log.info("받은 목록.........");

        List<MsgDTO> receiveList = result.get("R");

        //stream으로 펼쳐주지 않고 사용하는 것.
        receiveList.forEach(msgDTO -> log.info(msgDTO));

        log.info("보낸 목록.........");

        List<MsgDTO> sendList = result.get("S");

        //stream으로 펼쳐주지 않고 사용하는 것.
        sendList.forEach(msgDTO -> log.info(msgDTO));

    }
}
