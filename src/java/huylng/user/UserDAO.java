/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.user;

import huylng.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Shi
 */
public class UserDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public UserDTO checkLogin(String email, String password)
            throws SQLException, NamingException {
        UserDTO dto = null;
        try {
            String sql = "Select email, name, password, role, status "
                    + "From [User] "
                    + "Where email = ? and password = ? and status = 1";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    email = rs.getString("email");
                    String name = rs.getString("name");
                    password = rs.getString("password");
                    boolean role = rs.getBoolean("role");
                    boolean status = rs.getBoolean("status");
                    dto = new UserDTO(email, name, password, role, status);
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

    public boolean createAccount(String email, String password, String name) throws SQLException, NamingException {
        try {
            String sql = "Insert Into [User](email, name, password, role, status) "
                    + "Values (?,?,?,?,?)";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setBoolean(4, false);
                ps.setBoolean(5, true);
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

    public boolean update(String email, String password, String name) throws SQLException, NamingException {
        try {
            String sql = "Update [User] "
                    + "Set status = 1, password = ?, name = ? "
                    + "Where email = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, name);
                ps.setString(3, email);
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

    public boolean checkExist(String email) throws SQLException, NamingException {
        try {
            String sql = "Select email "
                    + "From [User] "
                    + "Where email = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
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

    public UserDTO checkLoginWithGoogle(String email)
            throws SQLException, NamingException {
        UserDTO dto = null;
        try {
            String sql = "Select email, name, role, status "
                    + "From [User] "
                    + "Where email = ?";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    email = rs.getString("email");
                    String name = rs.getString("name");
                    boolean role = rs.getBoolean("role");
                    boolean status = rs.getBoolean("status");
                    dto = new UserDTO(email, name, "", role, status);
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

    public boolean createAccount(String email, String name) throws SQLException, NamingException {
        try {
            String sql = "Insert Into [User](email, name, role, status) "
                    + "Values (?,?,?,?)";
            con = DBHelper.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setBoolean(3, false);
                ps.setBoolean(4, true);
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
