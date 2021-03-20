/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.servlet;

import static huylng.servlet.ViewQuestionServlet.NUMBEROFQUESTIONINPAGE;
import huylng.userquiz.UserQuizDAO;
import huylng.userquiz.UserQuizDTO;
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

/**
 *
 * @author Shi
 */
@WebServlet(name = "ViewHistoryServlet", urlPatterns = {"/ViewHistoryServlet"})
public class ViewHistoryServlet extends HttpServlet {

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
        String url = "viewHistoryPage";
        int noOfRecords = 0;
        int noOfPage = 1;
        int currentPage = 1;
        String page = request.getParameter("page");
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                if (page != null) {
                    currentPage = Integer.parseInt(page);
                }
                String email = (String) session.getAttribute("EMAIL");
                UserQuizDAO userQuizDAO = new UserQuizDAO();
                userQuizDAO.getUserQuizByEmail(email, currentPage);
                List<UserQuizDTO> list = userQuizDAO.getHistory();
                noOfRecords = userQuizDAO.getNumberOfUserQuizByEmail(email);
                noOfPage = (int) Math.ceil(noOfRecords * 1.0 / NUMBEROFQUESTIONINPAGE);
                session.setAttribute("QUIZHISTORY", list);
                session.setAttribute("CURRENTPAGEHISTORY", currentPage);
                session.setAttribute("NOOFRECORDHISTORY", noOfRecords);
                session.setAttribute("NOOFPAGEHISTORY", noOfPage);
            }
        } catch (SQLException e) {
            log("Error at ViewHistoryServlet_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at ViewHistoryServlet_Naming: " + e.getMessage());
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
