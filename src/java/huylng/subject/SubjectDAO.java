/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.subject;

import huylng.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Shi
 */
public class SubjectDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    private List<SubjectDTO> subject;

    public List<SubjectDTO> getSubject() {
        return this.subject;
    }

    public List<SubjectDTO> loadSubjects() throws SQLException, NamingException {
        try {
            String sql = "Select subjectId, name, numberOfQuestion, timeDuration, startDate, endDate "
                    + "From Subject ";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("subjectId");
                    String name = rs.getString("name");
                    int numberOfQuestion = rs.getInt("numberOfQuestion");
                    Date timeduration = rs.getTime("timeDuration");
                    Date startDate = rs.getDate("startDate");
                    Date endDate = rs.getDate("endDate");
                    SubjectDTO dto = new SubjectDTO(id, name, numberOfQuestion, timeduration, startDate, endDate);
                    if (this.subject == null) {
                        this.subject = new ArrayList<>();
                    }
                    this.subject.add(dto);
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
        return this.subject;
    }

    public String getSubjectNameById(String subjectId) throws SQLException, NamingException {
        String subjectName = "";
        try {
            String sql = "Select name "
                    + "From Subject "
                    + "Where subjectId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    subjectName = rs.getString("name");
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
        return subjectName;
    }

    public String getSubjectIdByName(String subjectName) throws SQLException, NamingException {
        String subjectId = "";
        try {
            String sql = "Select subjectId "
                    + "From Subject "
                    + "Where name Like ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + subjectName + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    subjectId = rs.getString("subjectId");
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
        int numberOfQuestion = 0;
        try {
            String sql = "Select numberOfQuestion "
                    + "From Subject "
                    + "Where subjectId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numberOfQuestion = rs.getInt("numberOfQuestion");
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
        return numberOfQuestion;
    }

    public boolean update(String subjectId, int numberOfQuestion, Date timeDuration, Date startDate, Date endDate) throws SQLException, NamingException {
        try {
            String sql = "Update Subject "
                    + "Set numberOfQuestion = ?, timeDuration = ?, startDate = ?, endDate = ? "
                    + "Where subjectId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, numberOfQuestion);
                ps.setTimestamp(2, new Timestamp(timeDuration.getTime()));
                ps.setTimestamp(3, new Timestamp(startDate.getTime()));
                ps.setTimestamp(4, new Timestamp(endDate.getTime()));
                ps.setString(5, subjectId);
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

    public SubjectDTO getSubjectDTO(String subjectId, int numberOfQuestion) throws SQLException, NamingException {
        SubjectDTO dto = null;
        try {
            String sql = "Select timeDuration, startDate, endDate "
                    + "From Subject "
                    + "Where subjectId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Date timeDuration = rs.getTime("timeDuration");
                    Date startDate = rs.getDate("startDate");
                    Date endDate = rs.getDate("endDate");
                    dto = new SubjectDTO(null, null, numberOfQuestion, timeDuration, startDate, endDate);
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

    public Date getTimeDurationBySubjectId(String subjectId) throws SQLException, NamingException {
        try {
            String sql = "Select timeDuration "
                    + "From Subject "
                    + "Where subjectId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Date timeDuration = rs.getTime("timeDuration");
                    return timeDuration;
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
        return null;
    }

}
