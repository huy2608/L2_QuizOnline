/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.question;

import static huylng.servlet.ViewQuestionServlet.NUMBEROFQUESTIONINPAGE;
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
public class QuestionDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private List<QuestionDTO> question;

    public List<QuestionDTO> getQuestion() {
        return this.question;
    }

    public List<QuestionDTO> loadQuestion(String subjectId, int pageNo) throws SQLException, NamingException {
        int recordIndex = NUMBEROFQUESTIONINPAGE * (pageNo - 1);
        try {
            String sql = "Select questionId, subjectId, question_content, status "
                    + "From Question "
                    + "Where subjectId = ? "
                    + "Order By questionId Asc OFFSET " + recordIndex + " Rows Fetch next " + NUMBEROFQUESTIONINPAGE + " Rows Only";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionId = rs.getString("questionId");
                    subjectId = rs.getString("subjectId");
                    String question_content = rs.getString("question_content");
                    String status = rs.getString("status");
                    QuestionDTO dto = new QuestionDTO(questionId, subjectId, question_content, status);
                    if (this.question == null) {
                        this.question = new ArrayList<>();
                    }
                    this.question.add(dto);
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

    public boolean update(String questionId, String question_content, String status) throws SQLException, NamingException {
        try {
            String sql = "Update Question "
                    + "Set question_content = ?, status = ? "
                    + "Where questionId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, question_content);
                ps.setString(2, status);
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

    public QuestionDTO getQuestionIdBySubjectId(String subjectId) throws SQLException, NamingException {
        QuestionDTO dto = null;
        try {
            String sql = "Select questionId, subjectId, question_content, status "
                    + "From Question "
                    + "Where subjectId = ? and status = 'Activate'";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionId = rs.getString("questionId");
                    subjectId = rs.getString("subjectId");
                    String question_content = rs.getString("question_content");
                    String status = rs.getString("status");
                    dto = new QuestionDTO(questionId, subjectId, question_content, status);
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

    public boolean updateStatus(String questionId) throws SQLException, NamingException {
        try {
            String sql = "Update Question "
                    + "Set status = 'DeActivate' "
                    + "Where questionId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionId);
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

    public void createQuestion(String questionId, String subjectId, String question_content, String status) throws SQLException, NamingException {
        try {
            String sql = "Insert into Question (questionId, subjectId, question_content, status) "
                    + "Values (?,?,?,?)";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionId);
                ps.setString(2, subjectId);
                ps.setString(3, question_content);
                ps.setString(4, status);
                ps.executeUpdate();
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
    }

    public List<QuestionDTO> getQuestionBySubjectId(String subjectId, int numberOfQuestion) throws SQLException, NamingException {
        try {
            String sql = "SELECT TOP (?) questionId, subjectId, question_content, status "
                    + "FROM Question "
                    + "Where status = 'Activate' and subjectId = ? "
                    + "ORDER BY NEWID() ";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, numberOfQuestion);
                ps.setString(2, subjectId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionId = rs.getString("questionId");
                    subjectId = rs.getString("subjectId");
                    String question_content = rs.getString("question_content");
                    String status = rs.getString("status");
                    QuestionDTO dto = new QuestionDTO(questionId, subjectId, question_content, status);
                    if (this.question == null) {
                        this.question = new ArrayList<>();
                    }
                    this.question.add(dto);
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

    public List<QuestionDTO> getQuestionByName(String subjectId, String question_content, int pageNo) throws SQLException, NamingException {
        int recordIndex = NUMBEROFQUESTIONINPAGE * (pageNo - 1);
        String sql = "Select questionId, subjectId, question_content, status "
                + "From Question "
                + "Where question_content Like ? and subjectId = ? "
                + "Order By questionId Asc OFFSET " + recordIndex + " Rows Fetch next " + NUMBEROFQUESTIONINPAGE + " Rows Only";
        try {
            con = DBHelper.makeConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + question_content + "%");
            ps.setString(2, subjectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String questionId = rs.getString("questionId");
                subjectId = rs.getString("subjectId");
                question_content = rs.getString("question_content");
                String status = rs.getString("status");
                QuestionDTO dto = new QuestionDTO(questionId, subjectId, question_content, status);
                if (this.question == null) {
                    this.question = new ArrayList<>();
                }
                this.question.add(dto);
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

    public List<QuestionDTO> getQuestionByStatus(String subjectId, String status, int pageNo) throws SQLException, NamingException {
        int recordIndex = NUMBEROFQUESTIONINPAGE * (pageNo - 1);
        try {
            String sql = "Select questionId, subjectId, question_content, status "
                    + "From Question "
                    + "Where subjectId = ? and status = ? "
                    + "Order By questionId Asc OFFSET " + recordIndex + " Rows Fetch next " + NUMBEROFQUESTIONINPAGE + " Rows Only";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                ps.setString(2, status);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionId = rs.getString("questionId");
                    subjectId = rs.getString("subjectId");
                    String question_content = rs.getString("question_content");
                    status = rs.getString("status");
                    QuestionDTO dto = new QuestionDTO(questionId, subjectId, question_content, status);
                    if (this.question == null) {
                        this.question = new ArrayList<>();
                    }
                    this.question.add(dto);
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

    public List<QuestionDTO> getQuestion(String subjectId, String question_content, String status, int pageNo) throws SQLException, NamingException {
        int recordIndex = NUMBEROFQUESTIONINPAGE * (pageNo - 1);
        try {
            String sql = "Select questionId, subjectId, question_content, status "
                    + "From Question "
                    + "Where subjectId = ? and status = ? and question_content Like ? "
                    + "Order By questionId Asc OFFSET " + recordIndex + " Rows Fetch next " + NUMBEROFQUESTIONINPAGE + " Rows Only";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                ps.setString(2, status);
                ps.setString(3, "%" + question_content + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionId = rs.getString("questionId");
                    subjectId = rs.getString("subjectId");
                    question_content = rs.getString("question_content");
                    status = rs.getString("status");
                    QuestionDTO dto = new QuestionDTO(questionId, subjectId, question_content, status);
                    if (this.question == null) {
                        this.question = new ArrayList<>();
                    }
                    this.question.add(dto);
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

    public boolean checkExist(String questionId) throws SQLException, NamingException {
        try {
            String sql = "Select questionId "
                    + "From Question "
                    + "Where questionId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionId);
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

    public String getSubjectIdByQuestionId(String questionId) throws SQLException, NamingException {
        String subjectId = "";
        try {
            String sql = "Select subjectId "
                    + "From Question "
                    + "Where questionId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, questionId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    subjectId = rs.getString("questionId");
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
        return subjectId;
    }

    public int getNumberOfQuestionBySubjectId(String subjectId) throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select questionId "
                    + "From Question "
                    + "Where subjectId = ? and status = 'Activate'";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNumberOfQuestionBySubjectIdAndName(String subjectId, String question_content) throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select questionId "
                    + "From Question "
                    + "Where subjectId = ? and question_content like ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                ps.setString(2, "%" + question_content + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }
    public boolean checkQuestionExist(String questionContent) throws SQLException, NamingException{
        try {
            String sql = "Select question_content "
                    + "From Question "
                    + "Where question_content = ?";
            con = DBHelper.makeConnection();
            if(con!=null){
                ps = con.prepareStatement(sql);
                ps.setString(1, questionContent);
                rs = ps.executeQuery();
                if(rs.next()){
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
        }return false;
    }
    public int getNumberOfQuestionBySubjectIdAndStatus(String subjectId, String status) throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select questionId "
                    + "From Question "
                    + "Where subjectId = ? and status = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                ps.setString(2, status);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }

    public int getNumberOfQuestionBySubjectIdAndNameAndStatus(String subjectId, String question_content, String status) throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select questionId "
                    + "From Question "
                    + "Where subjectId = ? and question_content like ? and status = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                ps.setString(2, "%" + question_content + "%");
                ps.setString(3, status);
                rs = ps.executeQuery();
                while (rs.next()) {
                    i++;
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
        return i;
    }
}
