/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.servlet;

import huylng.accessgoogle.GooglePojo;
import huylng.accessgoogle.GoogleUtils;
import huylng.subject.SubjectDAO;
import huylng.subject.SubjectDTO;
import huylng.user.UserDAO;
import huylng.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Shi
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage";
    private final String VIEW_SUBJECT_PAGE = "viewSubjectPage";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String code = request.getParameter("code");
        String accessToken;
        GooglePojo googlePojo = null;
        try {
            if (code != null) {
                accessToken = GoogleUtils.getToken(code);
                googlePojo = GoogleUtils.getUserInfo(accessToken);
            }
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("EMAIL");
            if (email != null) {
                url = VIEW_SUBJECT_PAGE;
            } else {
                if (username != null) {
                    UserDAO dao = new UserDAO();
                    String key = DigestUtils.sha256Hex(password);
                    UserDTO result = dao.checkLogin(username, key);

                    if (result != null) {
                        if (result.isRole() == true) {
                            session.setAttribute("ADMIN", 1);
                        }
                        url = VIEW_SUBJECT_PAGE;
                        session.setAttribute("FULLNAME", result.getName());
                        session.setAttribute("EMAIL", username);
                        SubjectDAO subdao = new SubjectDAO();
                        subdao.loadSubjects();
                        List<SubjectDTO> listsub = subdao.getSubject();
                        session.setAttribute("SUBJECTLIST", listsub);
                    } else {
                        url = "errorPage";
                    }
                }
                if (username == null || password == null) {
                    UserDAO userdao = new UserDAO();
                    if (googlePojo != null) {
                        username = googlePojo.getEmail();
                        if (username != null) {
                            String[] output = googlePojo.getEmail().split("@");
                            String name = output[0];
                            UserDTO result = userdao.checkLoginWithGoogle(username);
                            if (result != null) {
                                if (result.isRole() == true) {
                                    session.setAttribute("ADMIN", 1);
                                }
                            } else {
                                userdao.createAccount(username, name);
                            }
                            url = VIEW_SUBJECT_PAGE;
                            session.setAttribute("FULLNAME", name);
                            session.setAttribute("EMAIL", username);
                            SubjectDAO subdao = new SubjectDAO();
                            subdao.loadSubjects();
                            List<SubjectDTO> listsub = subdao.getSubject();
                            session.setAttribute("SUBJECTLIST", listsub);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log("LoginServlet _ SQL" + e.getMessage());
        } catch (NamingException e) {
            log("LoginServlet _ NAMING" + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
