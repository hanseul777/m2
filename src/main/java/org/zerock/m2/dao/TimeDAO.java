package org.zerock.m2.dao;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum TimeDAO {

    INSTANCE; // enum클래스 : 인스턴스 몇 개 사용할건지 먼저 선언 -> 외부에서는 timeDAO.Instance.getTime 이용해서 호출한다.

    private static final String SQL = "select now()";

    //자신없으면 일단 런타임던져주기
    public String getTime() throws RuntimeException {

        //익명클래스를 사용해야해서 외부에서 가져올 때 사용
        StringBuffer buffer = new StringBuffer();

        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception{

                // 익명클래스 사용
                preparedStatement = connection.prepareStatement(SQL);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
//                log.info(resultSet.getString(1));
                buffer.append(resultSet.getString(1)); // 버퍼이용하면서 변경
            }

        }.makeAll();

//        return null; 초기에는 return null로 선언해서 에러없이 코딩시작하기
        return buffer.toString();
    }
}
