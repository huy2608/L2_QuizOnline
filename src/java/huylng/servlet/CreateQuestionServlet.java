/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.servlet;

import huylng.answer.AnswerDAO;
import huylng.answer.AnswerErr;
import huylng.question.QuestionDAO;
import huylng.question.QuestionError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "CreateQuestionServlet", urlPatterns = {"/CreateQuestionServlet"})
public class CreateQuestionServlet extends HttpServlet {

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
        String answer1 = request.getParameter("txtAnswer1");
        String answer2 = request.getParameter("txtAnswer2");
        String answer3 = request.getParameter("txtAnswer3");
        String answer4 = request.getParameter("txtAnswer4");
        String answer = request.getParameter("Answer");
        String question_content = request.getParameter("txtQuestionContent");
        String subjectName = request.getParameter("subjectName");
        String url = "createPage";
        boolean error = false;
        try {
            QuestionError questionErrs = new QuestionError();
            QuestionDAO questionDAO = new QuestionDAO();
            if (question_content.trim().length() < 1) {
                questionErrs.setQuestionContentLengthErr("not allow empty content");
                error = true;
            } else {
                boolean exist = questionDAO.checkQuestionExist(question_content);
                if (exist == true) {
                    error = true;
                    questionErrs.setQuestionContentLengthErr("Question already exist. Please try again!");
                }
            }
            AnswerErr answerErrors = new AnswerErr();
            if (!answer1.matches(".{1,100}")) {
                error = true;
                answerErrors.setAnswerALengthErr("Error length, must from 1 - 100 characters");
            }
            if (!answer2.matches(".{1,100}")) {
                error = true;
                answerErrors.setAnswerBLengthErr("Error length, must from 1 - 100 characters");
            }
            if (!answer3.matches(".{1,100}")) {
                error = true;
                answerErrors.setAnswerCLengthErr("Error length, must from 1 - 100 characters");
            }
            if (!answer4.matches(".{1,100}")) {
                error = true;
                answerErrors.setAnswerDLengthErr("Error length, must from 1 - 100 characters");
            }
            if (answer1.matches(answer2) || answer1.matches(answer3) || answer1.matches(answer4) || answer2.matches(answer3) || answer2.matches(answer4) || answer3.matches(answer4)) {
                error = true;
                answerErrors.setAnswerDuplicated("Answer is duplicated. Please try again!");
            }
            if (error == true) {
                request.setAttribute("QUESTIONERR", questionErrs);
                request.setAttribute("ANSWERERR", answerErrors);
            } else {
                AnswerDAO answerDAO = new AnswerDAO();
                HttpSession session = request.getSession();
                String subjectId = (String) session.getAttribute("SUBJECTID");
                UUID questionId = UUID.randomUUID();
                while (questionDAO.checkExist(questionId.toString())) {
                    questionId = UUID.randomUUID();
                }
                questionDAO.createQuestion(questionId.toString(), subjectId, question_content, "Activate");
                if (answer.equals("A")) {
                    answerDAO.create(questionId.toString(), "A", answer1, true);
                    answerDAO.create(questionId.toString(), "B", answer2, false);
                    answerDAO.create(questionId.toString(), "C", answer3, false);
                    answerDAO.create(questionId.toString(), "D", answer4, false);
                }
                if (answer.equals("B")) {
                    answerDAO.create(questionId.toString(), "A", answer1, false);
                    answerDAO.create(questionId.toString(), "B", answer2, true);
                    answerDAO.create(questionId.toString(), "C", answer3, false);
                    answerDAO.create(questionId.toString(), "D", answer4, false);
                }
                if (answer.equals("C")) {
                    answerDAO.create(questionId.toString(), "A", answer1, false);
                    answerDAO.create(questionId.toString(), "B", answer2, false);
                    answerDAO.create(questionId.toString(), "C", answer3, true);
                    answerDAO.create(questionId.toString(), "D", answer4, false);
                }
                if (answer.equals("D")) {
                    answerDAO.create(questionId.toString(), "A", answer1, false);
                    answerDAO.create(questionId.toString(), "B", answer2, false);
                    answerDAO.create(questionId.toString(), "C", answer3, false);
                    answerDAO.create(questionId.toString(), "D", answer4, true);
                }
                url = "viewQuestion?subjectId=" + subjectId + "&subjectName=" + subjectName;
            }
        } catch (SQLException e) {
            log("Error at CreateServlet_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at CreateServlet_Naming: " + e.getMessage());
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
