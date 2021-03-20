/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.answer;

import huylng.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Shi
 */
public class AnswerDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private List<AnswerDTO> answer;

    public List<AnswerDTO> getAnswer() {
        return this.answer;
    }

    public List<AnswerDTO> getAnswerOfQuestion(String questionId) throws SQLException, NamingException {
        try {
            String sql = "Select questionId,answerId, answer_content, isCorrect "
                    + "From Answer "
                    + "Where questionId = ? ";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    questionId = rs.getString("questionId");
                    String answerId = rs.getString("answerId");
                    String answer_content = rs.getString("answer_content");
                    boolean isCorrect = rs.getBoolean("isCorrect");
                    AnswerDTO dto = new AnswerDTO(answerId, questionId, answer_content, isCorrect);
                    if (this.answer == null) {
                        this.answer = new ArrayList<>();
                    }
                    this.answer.add(dto);
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
        return this.answer;
    }

    public String getCorrectAnswerFromAQuestion(String questionId) throws SQLException, NamingException {
        String answerId = "";
        try {
            String sql = "Select answerId "
                    + "From Answer "
                    + "Where isCorrect = 1 and questionId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    answerId = rs.getString("answerId");
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
        return answerId;
    }
    private List<String> wrongAnswer;

    public List<String> getWrongAnswer() {
        return this.wrongAnswer;
    }

    public List<String> getWrongAnswerFromAQuestion(String questionId) throws SQLException, NamingException {
        try {
            String sql = "Select answerId "
                    + "From Answer "
                    + "Where isCorrect = 0 and questionId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String answerId = rs.getString("answerId");
                    if (this.wrongAnswer == null) {
                        this.wrongAnswer = new ArrayList<>();
                    }
                    this.wrongAnswer.add(answerId);
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
        return this.wrongAnswer;
    }

    public boolean updateAnswerContent(String answer_content, String answerId, String questionId) throws SQLException, NamingException {
        try {
            String sql = "Update Answer "
                    + "Set answer_content = ? "
                    + "Where answerId = ? and questionId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, answer_content);
                ps.setString(2, answerId);
                ps.setString(3, questionId);
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

    public boolean updateAnswerCorrect(boolean isCorrect, String answerId, String questionId) throws SQLException, NamingException {
        try {
            String sql = "Update Answer "
                    + "Set isCorrect = ? "
                    + "Where answerId = ? and questionId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, isCorrect);
                ps.setString(2, answerId);
                ps.setString(3, questionId);
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

    public boolean create(String questionId, String answerId, String answer_content, boolean isCorrect) throws SQLException, NamingException {
        try {
            String sql = "Insert into Answer(questionId, answerId, answer_content, isCorrect) "
                    + "Values (?,?,?,?) ";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionId);
                ps.setString(2, answerId);
                ps.setString(3, answer_content);
                ps.setBoolean(4, isCorrect);
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
}
