package org.zerock.m2.dao;

import org.zerock.m2.dto.MemberDTO;

public enum MemberDAO {
    INSTANCE;

    private static final String SQL_LOGIN = "select mid,mpw,mname,nickname,joindate,moddate\n" +
            "from tbl_member\n" +
            "where mid=? and mpw=?";
    public MemberDTO selectForLogin(String mid, String mpw) throws RuntimeException {

        MemberDTO dto = MemberDTO.builder().build(); // 객체를 생성해 주는 것 new로 선언했던 그런거..
        //mid,mpw,mname,nickname,joindate
        // moddate
        new JdbcTemplate(){
            @Override
            protected void execute() throws Exception {
                preparedStatement = connection.prepareStatement(SQL_LOGIN);
                preparedStatement.setString(1,mid);
                preparedStatement.setString(2,mpw);

                resultSet = preparedStatement.executeQuery();
                // 성공하는 시나리오에서는 한 번만 돌아갈 것. 그런데 resulSet.next()의 리턴값이 boolean -> false라면 로그인실패
                if(resultSet.next() == false){
                    //throws : 위쪽에 문제를 던지는거. throw new : 내생각에 이거 문제같아서 강제로 예외로 만들게!
                    //                             -> 제어문이고 return을 가지고 있음 -> 예외발생시 밑으로 빠진다.
                    throw new Exception("NOT EXIT");
                }
                dto.setMid(resultSet.getString(1));
                dto.setMpw(resultSet.getString(2));
                dto.setMname(resultSet.getString(3));
                dto.setNickname(resultSet.getString(4));
                dto.setJoindate(resultSet.getTimestamp(5));

                dto.setModdate(resultSet.getTimestamp(6));
            }
        }.makeAll();

        return dto;
    }

}
