/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.quizdetail;

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
public class QuizDetailDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean create(String quizDetailId, String quizId, String questionContent, String answerContent, boolean isCorrect) throws SQLException, NamingException {
        try {
            String sql = "Insert Into QuizDetail(quizDetailId, quizId, questionContent, answerContent, isCorrect) "
                    + "Values(?,?,?,?,?)";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, quizDetailId);
                ps.setString(2, quizId);
                ps.setString(3, questionContent);
                ps.setString(4, answerContent);
                ps.setBoolean(5, isCorrect);
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

    public boolean checkExist(String quizDetailId) throws SQLException, NamingException {
        try {
            String sql = "Select quizDetailId "
                    + "From QuizDetail "
                    + "Where quizDetailId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, quizDetailId);
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

    private List<String> question;

    public List<String> getQuestion() {
        return this.question;
    }

    public List<String> getQuestionByQuizId(String quizId) throws SQLException, NamingException {
        try {
            String sql = "SELECT Distinct questionContent "
                    + "FROM QuizDetail "
                    + "Where quizId = ? ";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, quizId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionContent = rs.getString("questionContent");
                    if (this.question == null) {
                        this.question = new ArrayList<>();
                    }
                    this.question.add(questionContent);
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
        return this.question;
    }

    public List<String> getQuestionByQuizDetailId(String quizDetailId) throws SQLException, NamingException {
        try {
            String sql = "SELECT Distinct questionContent "
                    + "FROM QuizDetail "
                    + "Where quizDetailId = ? ";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, quizDetailId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionContent = rs.getString("questionContent");
                    if (this.question == null) {
                        this.question = new ArrayList<>();
                    }
                    this.question.add(questionContent);
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
        return this.question;
    }
    private List<QuizDetailDTO> answer;

    public List<QuizDetailDTO> getAnswer() {
        return this.answer;
    }

    public List<QuizDetailDTO> getAnswerHistoryByQuestionContentAndId(String questionContent, String quizId) throws SQLException, NamingException {
        try {
            String sql = "SELECT quizDetailId, answerContent, isCorrect "
                    + "FROM QuizDetail "
                    + "Where questionContent = ? And quizId = ? ";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionContent);
                ps.setString(2, quizId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String quizDetailId = rs.getString("quizDetailId");
                    String answercontent = rs.getString("answerContent");
                    boolean isCorrect = rs.getBoolean("isCorrect");
                    QuizDetailDTO dto = new QuizDetailDTO(quizDetailId, null, null, answercontent, isCorrect);
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

    public List<QuizDetailDTO> getAnswerByQuestionContent(String questionContent) throws SQLException, NamingException {
        try {
            String sql = "SELECT TOP (4) quizDetailId, answerContent, isCorrect "
                    + "FROM QuizDetail "
                    + "Where questionContent = ? "
                    + "ORDER BY NEWID() ";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionContent);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String quizDetailId = rs.getString("quizDetailId");
                    String answercontent = rs.getString("answerContent");
                    boolean isCorrect = rs.getBoolean("isCorrect");
                    QuizDetailDTO dto = new QuizDetailDTO(quizDetailId, null, null, answercontent, isCorrect);
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

    public String getCorrectAnswerByQuestionContent(String questionContent) throws SQLException, NamingException {
        String answerContent = null;
        try {
            String sql = "Select answerContent "
                    + "From QuizDetail "
                    + "Where isCorrect = 'True' and questionContent = ? ";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionContent);
                rs = ps.executeQuery();
                if (rs.next()) {
                    answerContent = rs.getString("answerContent");
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
        return answerContent;
    }

    public QuizDetailDTO getQuizDetailByQuizDetailId(String quizDetailId) throws SQLException, NamingException {
        QuizDetailDTO dto = null;
        try {
            String sql = "Select quizId, questionContent, answerContent "
                    + "From QuizDetail "
                    + "Where quizDetailId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, quizDetailId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionContent = rs.getString("questionContent");
                    String answerContent = rs.getString("answerContent");
                    String quizId = rs.getString("quizId");
                    dto = new QuizDetailDTO(quizDetailId, quizId, questionContent, answerContent, true);
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
        return dto;
    }

    public boolean delete(String quizId) throws SQLException, NamingException {
        try {
            String sql = "Delete From QuizDetail "
                    + "Where quizId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, quizId);
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
