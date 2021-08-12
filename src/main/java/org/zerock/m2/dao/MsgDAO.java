package org.zerock.m2.dao;

import com.ibm.j9ddr.vm29.pointer.generated.__darwin_x86_exception_state64Pointer;
import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MsgDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public enum MsgDAO {
    INSTANCE;
    private static final String SQL_INSERT = "insert into tbl_msg (who,whom,content) values (?,?,?)";

    private static final String SQL_LIST = "select mno, who, whom, if(who = ?,'R', 'S') kind, content, regdate, opendate\n" +
            "from tbl_msg\n" +
            "where whom = ? or who = ?\n" +
            "order by kind asc, mno desc"; // 값(user5)를 ?로 변경

    private static final String SQL_SELECT = "select mno, who, whom,content, regdate,opendate from tbl_msg where mno = ?";
    private static final String SQL_UPDATE_OPEN = "update tbl_msg set opendate = now() where mno = ?";

    private static final String SQL_REMOVE = "delete from tbl_msg where mno = ? and who = ?";

    public void delete(MsgDTO msgDTO) throws RuntimeException {

       new JdbcTemplate(){
           @Override
           protected void execute() throws Exception {

               preparedStatement = connection.prepareStatement(SQL_REMOVE);
               preparedStatement.setLong(1,msgDTO.getMno());
               preparedStatement.setString(2,msgDTO.getWho());

               int count = preparedStatement.executeUpdate();

               log.info("count " + count);
           }
       }.makeAll();
    }

    //해당번호의 MsgDTO를 가져와라
    public MsgDTO select(Long mno) throws RuntimeException {

        MsgDTO msgDTO = MsgDTO.builder().build();

        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception{

                //update opendate
                preparedStatement = connection.prepareStatement(SQL_UPDATE_OPEN);
                preparedStatement.setLong(1,mno);

                preparedStatement.executeUpdate();

                //쿼리문을 연달아서 실행하기위해 일단 직접닫아주고 가비지컬렉터를 위해 null값을 준다.
                preparedStatement.close();
                preparedStatement = null;


                //who,whom,content
                preparedStatement = connection.prepareStatement(SQL_SELECT);
                preparedStatement.setLong(1, mno);
                resultSet = preparedStatement.executeQuery();

                resultSet.next();

                //mno, who, whom,content, regdate
                // opendate
                msgDTO.setMno(resultSet.getLong(1));
                msgDTO.setWho(resultSet.getString(2));
                msgDTO.setWhom(resultSet.getString(3));
                msgDTO.setContent(resultSet.getString(4));
                msgDTO.setRegdate(resultSet.getTimestamp(5));

                msgDTO.setOpendate(resultSet.getTimestamp(6));

            }
        }.makeAll();
        return msgDTO;
    }

    public void insert(MsgDTO msgDTO) throws RuntimeException{

        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception{
                //who,whom,content
                int idx = 1;
                preparedStatement = connection.prepareStatement(SQL_INSERT);
                preparedStatement.setString(idx++,msgDTO.getWho());
                preparedStatement.setString(idx++,msgDTO.getWhom());
                preparedStatement.setString(idx++,msgDTO.getContent());

                int count = preparedStatement.executeUpdate();
                log.info("count : " + count); // 연결확인

            }
        }.makeAll();

    }

    //who인지 whom인지 String으로 구분해서 MsgDTO의 List를 뒤진다.
    public Map<String, List<MsgDTO>> selectList(String user) throws RuntimeException{
      Map<String, List<MsgDTO>> listMap = new HashMap<>(); // 틀만 만든거고
      //listMap에 MsgDTO의 ArrayList를 push, R(받은거) S(보낸거)로 구분했던 것.
      listMap.put("R", new ArrayList<>()); // listMap에 있는 메소드인 put()을 사용
        // put() : 값을 넣어주는 기능 -> R을 가진 new ArrayList() (데이터)를 listMap으로 넣어준다.
      listMap.put("S", new ArrayList<>());


      new JdbcTemplate() {
          @Override
          protected void execute() throws Exception{
              preparedStatement = connection.prepareStatement(SQL_LIST);
              preparedStatement.setString(1,user);
              preparedStatement.setString(2,user);
              preparedStatement.setString(3,user);

              //select문이니까 resultSet한다.
              resultSet = preparedStatement.executeQuery();

              log.info(resultSet);
              // resultSet(select해서 날아 온 결과집합 : 결과가 적으면 한 번에 보내주는데 많으면 나눠서 보내준다. -> 그래서 다 끝나고 나면 close를 해주는 것)
              while(resultSet.next()){

//                  log.info(resultSet.getString(4));
                  String kind = resultSet.getString(4);

                  List<MsgDTO> targetList = listMap.get(kind);
                  //mno, who, whom, if(whom = ?,'R','S') kind,content,
                  //regdate, opendate
                  targetList.add(MsgDTO.builder()
                          .mno(resultSet.getLong(1))
                          .who(resultSet.getString(2))
                          .whom(resultSet.getString(3))
                          .content(resultSet.getString(5))
                          .regdate(resultSet.getTimestamp(6))
                          .opendate(resultSet.getTimestamp(7))
                          .build());

              }
          }
      }.makeAll();

      return listMap;
    }
}
