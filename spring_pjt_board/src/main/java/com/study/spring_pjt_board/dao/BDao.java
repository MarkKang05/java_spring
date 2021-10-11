package com.study.spring_pjt_board.dao;

import com.study.spring_pjt_board.dto.BDto;
import com.study.spring_pjt_board.util.Constant;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.naming.Context;
import javax.naming.InitialContext; import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class BDao {

    DataSource dataSource;
    JdbcTemplate template = null;

    public BDao(){

        template = Constant.template;

    }

    public BDto contentView(String strD) {

        upHit(strD);
        String query = "select * from mvc_board where bId = "+strD;
        return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));

    }

    public void write(String bName, String bTitle, String bContent){

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, bName);
                pstmt.setString(2, bTitle);
                pstmt.setString(3, bContent);
                return pstmt;
            }
        });

    }

    public ArrayList<BDto> list(){

        String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
        return (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));

    }


    public void modify(String bId, String  bName, String bTitle, String bContent) {

        String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, bName);
                ps.setString(2, bTitle);
                ps.setString(3, bContent);
                ps.setInt(4, Integer.parseInt(bId));
            }
        });

    }

    public void delete(String bId) {

        String query = "delete from mvc_board where bId = ?";
        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, bId);
            }
        });

    }

    public BDto reply_view(String strD){

        String query = "select * from mvc_board where bId = " + strD;
        return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));

    }

    public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent){

        String query = "insert into mvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, ? ,? ,?)";

        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, bName);
                ps.setString(2, bTitle);
                ps.setString(3, bContent);
                ps.setInt(4, Integer.parseInt(bGroup));
                ps.setInt(5, Integer.parseInt(bStep) + 1);
                ps.setInt(6, Integer.parseInt(bIndent) + 1);
            }
        });

    }

    private void replyShape( String strGroup, String strStep) {
    }


    private void upHit( String bId) {

        String query = "update mvc_board set bHit = bHit + 1 where bId = ?";

        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, Integer.parseInt(bId));
            }
        });

    }

}
