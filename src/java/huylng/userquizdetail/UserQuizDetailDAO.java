/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.userquizdetail;

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
public class UserQuizDetailDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean createUserQuizDetail(String userQuizDetailId, String userAnswerContent, String quizDetailId, String userQuizId) throws SQLException, NamingException {
        try {
            String sql = "Insert Into UserQuizDetail(userQuizDetailId, userAnswerContent, quizDetailId, userQuizId) "
                    + "Values (?,?,?,?)";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, userQuizDetailId);
                ps.setString(2, userAnswerContent);
                ps.setString(3, quizDetailId);
                ps.setString(4, userQuizId);
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
//    private List<UserQuizDTO> history;
//
//    public List<UserQuizDTO> getHistory() {
//        return this.history;
//    }

//    public List<UserQuizDTO> getListHistoryByQuizId(String quizId) throws SQLException, NamingException {
//        try {
//            String sql = "Select historyId, quizId "
//                    + "From History "
//                    + "Where quizId = ?";
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                ps = con.prepareStatement(sql);
//                ps.setString(1, quizId);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String historyId = rs.getString("historyId");
//                    quizId = rs.getString("quizId");
//                    UserQuizDTO dto = new UserQuizDTO(history);
//                    if (this.history == null) {
//                        this.history = new ArrayList<>();
//                    }
//                    this.history.add(dto);
//                }
//
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return this.history;
//    }
//
//    public String getHistoryIdByQuizId(String quizId) throws SQLException, NamingException {
//        String historyId = "";
//        try {
//            String sql = "Select historyId, quizId "
//                    + "From History "
//                    + "Where quizId = ?";
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                ps = con.prepareStatement(sql);
//                ps.setString(1, quizId);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    historyId = rs.getString("historyId");
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return historyId;
//    }
    public boolean checExist(String userDetailId) throws SQLException, NamingException {

        try {
            String sql = "Select userQuizDetailId "
                    + "From UserQuizDetail "
                    + "Where userQuizDetailId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, userDetailId);
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
    private List<UserQuizDetailDTO> userQuizDetail;

    public List<UserQuizDetailDTO> getUserQuizDetail() {
        return this.userQuizDetail;
    }

    public List<UserQuizDetailDTO> getUserQuizDetailByUserQuizId(String userQuizId) throws SQLException, NamingException {
        try {
            String sql = "Select userAnswerContent, quizDetailId, userQuizId "
                    + "From UserQuizDetail "
                    + "Where userQuizId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, userQuizId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userAnswerContent = rs.getString("userAnswerContent");
                    String quizDetailId = rs.getString("quizDetailId");
                    userQuizId = rs.getString("userQuizId");
                    UserQuizDetailDTO dto = new UserQuizDetailDTO(null, userAnswerContent, quizDetailId, userQuizId);
                    if (this.userQuizDetail == null) {
                        userQuizDetail = new ArrayList<>();
                    }
                    userQuizDetail.add(dto);
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
        return this.userQuizDetail;
    }

    public String getUserAnswer(String quizDetailId) throws SQLException, NamingException {
        String answerContent = "";
        try {
            String sql = "Select userAnswerContent "
                    + "From UserQuizDetail "
                    + "Where quizDetailId = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, quizDetailId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    answerContent = rs.getString("userAnswerContent");
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
        }return answerContent;
    }
}
