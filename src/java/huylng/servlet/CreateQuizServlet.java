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
import huylng.quiz.QuizDAO;
import huylng.quiz.QuizError;
import huylng.quizdetail.QuizDetailDAO;
import huylng.subject.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shi
 */
@WebServlet(name = "CreateQuizServlet", urlPatterns = {"/CreateQuizServlet"})
public class CreateQuizServlet extends HttpServlet {

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
        String start = request.getParameter("startdate");
        String end = request.getParameter("enddate");
        String timeduration = request.getParameter("timeDuration");
        String numberOfQuestion = request.getParameter("numberOfQuestion");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat datetime = new SimpleDateFormat("hh:mm:ss");
        String subjectId = request.getParameter("subjectId");
        boolean error = false;
        String url = "createQuizPage";
        try {
            QuizError errors = new QuizError();
            SubjectDAO subjectdao = new SubjectDAO();
            QuestionDAO questiondao = new QuestionDAO();
            QuizDetailDAO quizdetaildao = new QuizDetailDAO();
            if (start.isEmpty()) {
                error = true;
                errors.setInvalidStartDate("Please choose a date!");
            }
            if (end.isEmpty()) {
                errors.setInvalidEndDate("Please choose a date!");
                error = true;
            }
            if (timeduration.isEmpty()) {
                error = true;
                errors.setInvalidTimeDuration("Please input time for doing the quiz!");
            }
            if (numberOfQuestion.isEmpty()) {
                error = true;
                errors.setNumberOfQuestionOutOfOrder("Please input number or question per quiz!");
            }
            if (error == false) {
                if (sdf.parse(end).compareTo(sdf.parse(start)) <= 0) {
                    error = true;
                    errors.setInvalidEndDate("Must be a date after startDate");
                } else {
                    if (sdf.parse(end).compareTo(sdf.parse(sdf.format(new Date()))) <= 0) {
                        errors.setInvalidEndDate("Must be a date after today");
                        error = true;
                    }
                }
                if (!timeduration.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]")) {
                    error = true;
                    errors.setInvalidTimeDuration("Invalid time. Ex: 23:59:59");
                }
                int result = questiondao.getNumberOfQuestionBySubjectId(subjectId);
                if (Integer.parseInt(numberOfQuestion) <= 0) {
                    error = true;
                    errors.setNumberOfQuestionOutOfOrder("Please input a number greater than 0");
                } else {
                    if (Integer.parseInt(numberOfQuestion) > result) {
                        error = true;
                        errors.setNumberOfQuestionOutOfOrder("Not enough question to create a quiz. Please try again!");
                    }
                }
                if (error == true) {
                    request.setAttribute("QUIZERROR", errors);
                } else {
                    QuizDAO quizdao = new QuizDAO();
                    UUID quizId = UUID.randomUUID();
                    while (quizdao.checkExist(quizId.toString())) {
                        quizId = UUID.randomUUID();
                    }
                    quizdao.create(quizId.toString(), subjectId, new Date());
                    List<QuestionDTO> questionList = questiondao.getQuestionBySubjectId(subjectId, Integer.parseInt(numberOfQuestion));
                    for (QuestionDTO questionDTO : questionList) {
                        String questionId = questionDTO.getQuestionId();
                        AnswerDAO answerdao = new AnswerDAO();
                        List<AnswerDTO> answerList = answerdao.getAnswerOfQuestion(questionId);
                        for (AnswerDTO answerDTO : answerList) {
                            UUID quizDetailId = UUID.randomUUID();
                            while (quizdetaildao.checkExist(quizDetailId.toString())) {
                                quizDetailId = UUID.randomUUID();
                            }
                            quizdetaildao.create(quizDetailId.toString(), quizId.toString(), questionDTO.getQuestion_content(), answerDTO.getAnswer_content(), answerDTO.isIsCorrect());
                        }
                    }
                    subjectdao.update(subjectId, Integer.parseInt(numberOfQuestion), datetime.parse(timeduration), sdf.parse(start), sdf.parse(end));
                    url = "viewSubjectPage";
                }
            } else {
                request.setAttribute("QUIZERROR", errors);
            }
        } catch (SQLException e) {
            log("Error at CreateQuizServlet_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at CreateQuizServlet_Naming: " + e.getMessage());
        } catch (ParseException e) {
            log("Error at CreateQuizServlet_Parse: " + e.getMessage());
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
