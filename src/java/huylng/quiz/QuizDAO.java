/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.quiz;

import huylng.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Shi
 */
public class QuizDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean create(String quizId, String subjectId, Date createDate) throws SQLException, NamingException {
        try {
            String sql = "Insert Quiz(quizId, createDate, subjectId) "
                    + "Values (?,?,?)";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, quizId);
                ps.setString(3, subjectId);
                ps.setTimestamp(2, new Timestamp(createDate.getTime()));
                int result = ps.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public String getQuizIdBySubjectId(String subjectId) throws SQLException, NamingException {
        String quizId = null;
        try {
            String sql = "Select quizId "
                    + "From Quiz "
                    + "Where createDate = (Select Max(createDate) "
                    + "From Quiz "
                    + "Where subjectId = ?)";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    quizId = rs.getString("quizId");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return quizId;
    }

    private List<QuizDTO> quiz;

    public List<QuizDTO> getQuiz() {
        return this.quiz;
    }

    public List<QuizDTO> getQuizByEmail(String email) throws SQLException, NamingException {
        try {
            String sql = "Select quizId, email, subjectId, total, createDate "
                    + "From Quiz "
                    + "Where email = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String quizId = rs.getString("quizId");
                    email = rs.getString("email");
                    String subjectId = rs.getString("subjectId");
                    String createDate = rs.getString("createDate");
                    String total = rs.getString("total");
                    QuizDTO dto = new QuizDTO(quizId, email, subjectId, total, createDate);
                    if (this.quiz == null) {
                        this.quiz = new ArrayList<>();
                    }
                    this.quiz.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return this.quiz;
    }

    public boolean checkExist(String quizId) throws SQLException, NamingException {
        try {
            String sql = "Select quizId "
                    + "From Quiz "
                    + "Where quizId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, quizId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean delete(String subjectId) throws SQLException, NamingException {
        try {
            String sql = "Delete From Quiz "
                    + "Where subjectId = ?";
            con = DBHelper.makeConnection();
            if(con!=null){
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                int result = ps.executeUpdate();
                if(result > 0){
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
