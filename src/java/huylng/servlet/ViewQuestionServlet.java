/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.servlet;

import huylng.answer.AnswerDAO;
import huylng.answer.AnswerDTO;
import huylng.question.QuestionDAO;
import huylng.question.QuestionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
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
@WebServlet(name = "ViewQuestionServlet", urlPatterns = {"/ViewQuestionServlet"})
public class ViewQuestionServlet extends HttpServlet {

public static final int NUMBEROFQUESTIONINPAGE = 5;
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
        String subjectId = request.getParameter("subjectId");
        String name = request.getParameter("subjectName");
        String status = request.getParameter("cbStatus");
        String questionId = "";
        String url = "viewSubjectPage";
        int noOfRecords = 0;
        int noOfPage = 1;
        int currentPage = 1;
        String page = request.getParameter("page");
        try {
            QuestionDAO dao = new QuestionDAO();
            if (page != null) {
                currentPage = Integer.parseInt(page);
            }
            HttpSession session = request.getSession();
            session.removeAttribute("CURRENTPAGE");
            session.removeAttribute("NOOFRECOED");
            session.removeAttribute("NOOFPAGE");
            session.removeAttribute("QUESTIONLIST");
            session.removeAttribute("SUBJECTID");
            session.removeAttribute("ANSWERLIST");
            session.removeAttribute("ANSWERCORRECT");
            dao.loadQuestion(subjectId, currentPage);
            List<QuestionDTO> questionlist = dao.getQuestion();
            if (questionlist != null) {
                HashMap<String, List<AnswerDTO>> answer = new HashMap<>();
                HashMap<String, String> correctAnswer = new HashMap<>();
                if (questionlist != null) {
                    for (QuestionDTO questionDTO : questionlist) {
                        AnswerDAO answerdao = new AnswerDAO();
                        questionId = questionDTO.getQuestionId();
                        answerdao.getAnswerOfQuestion(questionId);
                        List<AnswerDTO> answerdto = answerdao.getAnswer();
                        String answerId = answerdao.getCorrectAnswerFromAQuestion(questionId);
                        if (answerdto != null) {
                            answer.put(questionId, answerdto);
                        }
                        if (!answerId.equals("")) {
                            correctAnswer.put(questionId, answerId);
                        }
                    }
                    noOfRecords = dao.getNumberOfQuestionBySubjectId(subjectId);
                    noOfPage = (int) Math.ceil(noOfRecords * 1.0 / NUMBEROFQUESTIONINPAGE);
                    session.setAttribute("CURRENTPAGE", currentPage);
                    session.setAttribute("NOOFRECOED", noOfRecords);
                    session.setAttribute("NOOFPAGE", noOfPage);
                    session.setAttribute("QUESTIONLIST", questionlist);
                    session.setAttribute("ANSWERLIST", answer);
                    session.setAttribute("ANSWERCORRECT", correctAnswer);
                }
            }
            session.setAttribute("SUBJECTID", subjectId);
            request.setAttribute("SUBJECTNAME", name);
            request.setAttribute("STATUS", status);
            url = "viewQuestionPage";
        } catch (SQLException e) {
            log("Error at ViewQuestionServlet_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at ViewQuestionServlet_Naming: " + e.getMessage());
        } catch (NumberFormatException e) {
            log("Error at ViewQuestionServlet_NumberFormat: " + e.getMessage());
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
