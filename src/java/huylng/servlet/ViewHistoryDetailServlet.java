/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.servlet;

import huylng.quizdetail.QuizDetailDAO;
import huylng.quizdetail.QuizDetailDTO;
import huylng.userquizdetail.UserQuizDetailDAO;
import huylng.userquizdetail.UserQuizDetailDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;
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
@WebServlet(name = "ViewHistoryDetailServlet", urlPatterns = {"/ViewHistoryDetailServlet"})
public class ViewHistoryDetailServlet extends HttpServlet {

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
        String userQuizId = request.getParameter("userQuizId");
        String url = "viewHistoryDetailPage";
        try {
            UserQuizDetailDAO dao = new UserQuizDetailDAO();
            dao.getUserQuizDetailByUserQuizId(userQuizId);
            List<UserQuizDetailDTO> list = dao.getUserQuizDetail();
            for (UserQuizDetailDTO userQuizDetailDTO : list) {
                HashMap<String, String> userAnswer = new HashMap<>();
                String quizDetailId = userQuizDetailDTO.getQuizDetailId();
                QuizDetailDAO quizdetaildao = new QuizDetailDAO();
                String userAnswerContent = dao.getUserAnswer(quizDetailId);
                String quizId = quizdetaildao.getQuizDetailByQuizDetailId(quizDetailId).getQuizId();
                quizdetaildao.getQuestionByQuizDetailId(quizDetailId);
                List<String> questionContentList = quizdetaildao.getQuestion();
                if (questionContentList != null) {
                    HashMap<String, List<QuizDetailDTO>> question = new HashMap<>();
                    HashMap<String, String> correctAnswer = new HashMap<>();
                    for (String questionContent : questionContentList) {
                        quizdetaildao = new QuizDetailDAO();
                        quizdetaildao.getAnswerHistoryByQuestionContentAndId(questionContent, quizId);
                        List<QuizDetailDTO> answerContentList = quizdetaildao.getAnswer();
                        if (answerContentList != null) {
                            question.put(questionContent, answerContentList);
                        }
                        String answerContent = quizdetaildao.getCorrectAnswerByQuestionContent(questionContent);
                        if (!answerContent.equals("")) {
                            correctAnswer.put(questionContent, answerContent);
                        }
                        userAnswer.put(questionContent, userAnswerContent);
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("QUESTIONLISTOFQUIZ", question);
                    session.setAttribute("ANSWEROFUSER", userAnswer);
                    session.setAttribute("ANSWERCORRECT", correctAnswer);
                }
            }

        } catch (SQLException e) {
            log("Error at ViewHistoryDetailServlet_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at ViewHistoryDetailServlet_Naming: " + e.getMessage());
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            response.sendRedirect(url);
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
