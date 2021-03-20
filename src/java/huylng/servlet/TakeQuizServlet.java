/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.servlet;

import huylng.quiz.QuizDAO;
import huylng.quizdetail.QuizDetailDAO;
import huylng.quizdetail.QuizDetailDTO;
import huylng.subject.SubjectDAO;
import huylng.subject.SubjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
@WebServlet(name = "TakeQuizServlet", urlPatterns = {"/TakeQuizServlet"})
public class TakeQuizServlet extends HttpServlet {

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
        String subjectId = request.getParameter("txtsubjectId");
        String subjectName = request.getParameter("txtSubjectName");
        String url = "viewSubjectPage";
        boolean error = false;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            QuizDAO quizdao = new QuizDAO();
            String quizId = quizdao.getQuizIdBySubjectId(subjectId);
            SubjectDAO subjectdao = new SubjectDAO();
            Date timeDuration = subjectdao.getTimeDurationBySubjectId(subjectId);
            int numberOfQuestion = subjectdao.getNumberOfQuestionBySubjectId(subjectId);
            SubjectDTO subjectDTO = subjectdao.getSubjectDTO(subjectId, numberOfQuestion);
            if (numberOfQuestion != 0) {
                if (subjectDTO.getStartDate().compareTo(new Date()) > 0) {
                    request.setAttribute("QUIZERR", "No quiz available, Please try again!");
                    error = true;
                }
            } else {
                request.setAttribute("QUIZERR", "No quiz available, Please try again!");
                error = true;
            }
            if (error == false) {
                QuizDetailDAO quizdetaildao = new QuizDetailDAO();
                quizdetaildao.getQuestionByQuizId(quizId);
                List<String> questionContentList = quizdetaildao.getQuestion();
                if (questionContentList != null) {
                    HashMap<String, List<QuizDetailDTO>> question = new HashMap<>();
                    for (String questionContent : questionContentList) {
                        quizdetaildao = new QuizDetailDAO();
                        quizdetaildao.getAnswerByQuestionContent(questionContent);
                        List<QuizDetailDTO> answerContentList = quizdetaildao.getAnswer();
                        question.put(questionContent, answerContentList);
                    }
                    Date today = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(today);
                    calendar.add(Calendar.HOUR, timeDuration.getHours());
                    calendar.add(Calendar.MINUTE, timeDuration.getMinutes());
                    calendar.add(Calendar.SECOND, timeDuration.getSeconds());
                    long milisecond = calendar.getTime().getTime() - today.getTime();

                    HttpSession session = request.getSession();
                    session.setAttribute("TIMEDURATION", milisecond);
                    session.setAttribute("SUBJECTIDOFQUIZ", subjectId);
                    session.setAttribute("QUESTIONLISTOFQUIZ", question);
                    session.setAttribute("NUMBEROFQUESTION", numberOfQuestion);
                    request.setAttribute("SUBJECTNAME", subjectName);
                    request.setAttribute("STARTTIME", sdf.format(today));
                    url = "takeQuizPage";
                } 
            }
        } catch (SQLException e) {
            log("Error at TakeQuizServlet_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at TakeQuizServlet_Naming: " + e.getMessage());
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
