/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.servlet;

import huylng.subject.SubjectDAO;
import huylng.subject.SubjectDTO;
import huylng.user.UserDAO;
import huylng.user.UserDTO;
import huylng.user.UserError;
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
@WebServlet(name = "RegistNewAccountServlet", urlPatterns = {"/RegistNewAccountServlet"})
public class RegistNewAccountServlet extends HttpServlet {

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
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("confirm");
        String name = request.getParameter("fullName");
        String url = "registrationPage";
        boolean error = false;
        try {
            UserDAO dao = new UserDAO();
            UserError errors = new UserError();
            if (!email.matches("([a-zA-Z0-9]{2,20})@([a-zA-Z]{3,10}).([a-zA-Z]{1,10})([.][a-zA-Z]{1,10})?")) {
                error = true;
                errors.setEmailFormatError("Example: aa@aaa.a");
            }
            if (!password.trim().equals(confirmPassword)) {
                error = true;
                errors.setPasswordConfirmError("Password not match");
            }
            if (error == false) {
                HttpSession session = request.getSession();
                boolean result = dao.checkExist(email);
                String key = DigestUtils.sha256Hex(password);
                if (result == true) {
                    dao.update(email, key, name);
                } else {
                    dao.createAccount(email, key, name);
                }
                url = "viewSubjectPage";
                UserDTO dto = dao.checkLogin(email, key);

                if (dto != null) {
                    if (dto.isRole() == true) {
                        session.setAttribute("ADMIN", 1);
                    }
                    url = "viewSubjectPage";
                    session.setAttribute("FULLNAME", dto.getName());
                    session.setAttribute("EMAIL", email);
                    SubjectDAO subdao = new SubjectDAO();
                    subdao.loadSubjects();
                    List<SubjectDTO> listsub = subdao.getSubject();
                    session.setAttribute("SUBJECTLIST", listsub);
                }
            } else {
                request.setAttribute("ERROR", errors);
            }
        } catch (SQLException e) {
            log("RegistNewAccountServlet _ SQL" + e.getMessage());
        } catch (NamingException e) {
            log("RegistNewAccountServlet _ NAMING" + e.getMessage());
        } finally {
            if (error == true) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
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
