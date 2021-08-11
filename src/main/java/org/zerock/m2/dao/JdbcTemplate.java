package org.zerock.m2.dao;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Log4j2
//dao 패키지 안에서만 바라보기 때문에 public 생략
abstract class JdbcTemplate {

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;


    public void makeAll() throws RuntimeException{
        try {
            makeConnection();
            execute();
            log.info("END");
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }finally {
            finish();
        }
    }

    protected abstract void execute()throws Exception;

    private void makeConnection() throws Exception{
        log.info("Connection.....");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bit08db?serverTimezone=Asia/Seoul","bituser","bituser");
    }

    private void finish() {
        log.info("------------finish--------------");
        log.info("Template ResultSet: "+ resultSet);
        log.info("Template PreparedStatement: "+preparedStatement);
        log.info(connection);

        if(resultSet != null){
            try{ resultSet.close(); }catch(Exception e){}
        }

        if(preparedStatement != null){
            try{ preparedStatement.close(); }catch(Exception e){}
        }

        if(connection != null){
            try{ connection.close(); }catch(Exception e){}
        }
    }
}