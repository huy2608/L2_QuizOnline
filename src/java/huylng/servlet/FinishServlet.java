/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.servlet;

import huylng.userquizdetail.UserQuizDetailDAO;
import huylng.quizdetail.QuizDetailDAO;
import huylng.quizdetail.QuizDetailDTO;
import huylng.userquiz.UserQuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
@WebServlet(name = "FinishServlet", urlPatterns = {"/FinishServlet"})
public class FinishServlet extends HttpServlet {

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
        String subjectName = request.getParameter("subjectname");
        String answer = "";
        String questionContent = "";
        String url = "viewPointPage";
        String correctAnswer = "";
        int numberOfQuestion = 0;
        float score = 0;
        float total = 0;
        String startDate = request.getParameter("startTime");
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                numberOfQuestion = (int) session.getAttribute("NUMBEROFQUESTION");
                
                HashMap<String, List<QuizDetailDTO>> question = (HashMap<String, List<QuizDetailDTO>>) session.getAttribute("QUESTIONLISTOFQUIZ");
                score = 10 / numberOfQuestion;
                String UserQuizId = UUID.randomUUID().toString();
                UserQuizDAO userQuizDAO = new UserQuizDAO();
                while (userQuizDAO.checExist(UserQuizId) == true) {
                    UserQuizId = UUID.randomUUID().toString();
                }
                if (question != null) {
                    for (Map.Entry<String, List<QuizDetailDTO>> questionDTO : question.entrySet()) {
                        questionContent = questionDTO.getKey();
                        answer = request.getParameter(questionContent);
                        QuizDetailDAO quizDetailDAO = new QuizDetailDAO();
                        correctAnswer = quizDetailDAO.getCorrectAnswerByQuestionContent(questionContent);
                        if (answer != null) {
                            if (answer.equals(correctAnswer)) {
                                total = total + score;
                            }
                        }

                    }
                    Date today = new Date();
                    String dayStop = df.format(today);
                    Date d1 = null;
                    Date d2 = null;
                    d1 = df.parse(startDate);
                    d2 = df.parse(dayStop);
                    long diff = d2.getTime() - d1.getTime();
                    String timeDuration = diff / (60 * 60 * 1000) + ":" + diff / (60 * 1000) + ":" + diff / 1000;
                    String email = (String) session.getAttribute("EMAIL");
                    userQuizDAO.create(UserQuizId, total, df.parse(timeDuration), email, subjectName);
                    for (Map.Entry<String, List<QuizDetailDTO>> questionDTO : question.entrySet()) {
                        answer = request.getParameter(questionContent);
                        String UserQuizDetailId = UUID.randomUUID().toString();
                        UserQuizDetailDAO userQuizDetailDAO = new UserQuizDetailDAO();
                        while (userQuizDetailDAO.checExist(UserQuizDetailId) == true) {
                            UserQuizDetailId = UUID.randomUUID().toString();
                        }
                        userQuizDetailDAO.createUserQuizDetail(UserQuizDetailId, answer, questionDTO.getValue().get(1).getQuizDetailId(), UserQuizId);
                    }
                    request.setAttribute("POINT", total);
                    request.setAttribute("SUBJECTNAME", subjectName);
                    session.removeAttribute("QUESTIONLISTOFQUIZ");
                    session.removeAttribute("SUBJECTIDOFQUIZ");
                    session.removeAttribute("ANSWERLISTOFQUIZ");
                    session.removeAttribute("ANSWERCORRECTQUIZ");
                }
            }
        } catch (SQLException e) {
            log("Error at FinishServlt_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at FinishServlt_Naming: " + e.getMessage());
        } catch (ParseException e) {
            log("Error at FinishServlt_Parse: " + e.getMessage());
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
