package com.study.spring_pjt_board.dao;

import com.study.spring_pjt_board.dto.BDto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class BDao {

    DataSource dataSource;

    public BDao(){
//        try {
//            Context context = new InitialContext();
////            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch(Exception e) {
//            e.printStackTrace();
//        }

        try {
            Context ctx = new InitialContext();
            dataSource=
                    (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void write(String bName, String bTitle, String bContent){

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?,?,?, 0,mvc_board_seq.currval,0,0)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bName);
            preparedStatement.setString(2, bTitle);
            preparedStatement.setString(3, bContent);

            int rn = preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (Exception e2){
                e2.printStackTrace();

            }
        }

    }

    public ArrayList<BDto> list(){

        ArrayList<BDto> dtos = new ArrayList<BDto>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


//        try{ connection = dataSource.getConnection("markkang05", "rhfqoddl2");
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        try {
            connection = dataSource.getConnection();

            String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
//            System.out.println("result:    "+ resultSet);

            while (resultSet.next()){
                int bId = resultSet.getInt("bId");
                String bName = resultSet.getString("bName");
                String bTitle= resultSet.getString("bTitle");
                String bContent = resultSet.getString("bContent");
                Timestamp bDate = resultSet.getTimestamp("bDate");
                int bHit = resultSet.getInt("bHit");
                int bGroup = resultSet.getInt("bGroup");
                int bStep = resultSet.getInt("bStep");
                int bIndent= resultSet.getInt("bIndent");

                BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,bHit,bGroup,bStep,bIndent);
                dtos.add(dto);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection!= null) connection.close();

            } catch (Exception ignored){
            }
        }

        return dtos;
    }

   public BDto contentView(String strD) {

        upHit(strD);

        BDto dto = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;



        return dto;
   }


    private void upHit( String bId) {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bId);

            int rn = preparedStatement.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
    }

}
