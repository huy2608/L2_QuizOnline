/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.userquiz;

import static huylng.servlet.ViewQuestionServlet.NUMBEROFQUESTIONINPAGE;
import huylng.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Shi
 */
public class UserQuizDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean create(String userQuizId, float totalPoint, Date timeDuration, String email, String subjectName) throws SQLException, NamingException {
        try {
            String sql = "Insert Into UserQuiz(userQuizId, totalPoint, timeDuration, email, subjectName) "
                    + "Values (?,?,?,?,?)";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, userQuizId);
                ps.setFloat(2, totalPoint);
                ps.setTimestamp(3, new Timestamp(timeDuration.getTime()));
                ps.setString(4, email);
                ps.setString(5, subjectName);
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

    public boolean checExist(String userQuizId) throws SQLException, NamingException {

        try {
            String sql = "Select userQuizId "
                    + "From UserQuiz "
                    + "Where userQuizId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, userQuizId);
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

    private List<UserQuizDTO> history;

    public List<UserQuizDTO> getHistory() {
        return this.history;
    }

    public List<UserQuizDTO> getUserQuizByEmail(String email, int pageNo) throws SQLException, NamingException {
        int recordIndex = NUMBEROFQUESTIONINPAGE * (pageNo - 1);
        try {
            String sql = "Select userQuizId, totalPoint, timeDuration, email, subjectName "
                    + "From UserQuiz "
                    + "Where email = ? "
                    + "Order By userQuizId Asc OFFSET " + recordIndex + " Rows Fetch next " + NUMBEROFQUESTIONINPAGE + " Rows Only";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userQuizId = rs.getString("userQuizId");
                    float totalPoint = rs.getFloat("totalPoint");
                    Time timeDuration = rs.getTime("timeDuration");
                    email = rs.getString("email");
                    String subjectName = rs.getString("subjectName");
                    UserQuizDTO dto = new UserQuizDTO(userQuizId, totalPoint, timeDuration, email, subjectName);
                    if (this.history == null) {
                        this.history = new ArrayList<>();
                    }
                    this.history.add(dto);
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
        return this.history;
    }

    public List<UserQuizDTO> getUserQuizBySubjectName(String subjectName, String email) throws SQLException, NamingException {
        try {
            String sql = "Select userQuizId, totalPoint, timeDuration, email, subjectName "
                    + "From UserQuiz "
                    + "Where subjectName = ? and email = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectName);
                ps.setString(2, email);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userQuizId = rs.getString("userQuizId");
                    float totalPoint = rs.getFloat("totalPoint");
                    Time timeDuration = rs.getTime("timeDuration");
                    email = rs.getString("email");
                    subjectName = rs.getString("subjectName");
                    UserQuizDTO dto = new UserQuizDTO(userQuizId, totalPoint, timeDuration, email, subjectName);
                    if (this.history == null) {
                        this.history = new ArrayList<>();
                    }
                    this.history.add(dto);
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
        return this.history;
    }

    public int getNumberOfUserQuizByEmail(String email) throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select userQuizId, totalPoint, timeDuration, email, subjectName "
                    + "From UserQuiz "
                    + "Where email = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
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

    public int getNumberOfUserQuizBySubjectName(String subjectName) throws SQLException, NamingException {
        int i = 0;
        try {
            String sql = "Select userQuizId, totalPoint, timeDuration, email, subjectName "
                    + "From UserQuiz "
                    + "Where subjectName = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectName);
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
