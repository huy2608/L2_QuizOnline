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
@WebServlet(name = "ConfirmServlet", urlPatterns = {"/ConfirmServlet"})
public class ConfirmServlet extends HttpServlet {

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
        String questionId = request.getParameter("txtQuestionId");
        String searchValue = request.getParameter("txtSearchValue");
        String question_content = request.getParameter("txtQuestionContent");
        String status = request.getParameter("cbStatus");
        String statusofsearch = request.getParameter("cbStatusOfSearch");
        String subjectName = request.getParameter("subjectName");
        String page = request.getParameter("page");
        String answerA = request.getParameter("1");
        String answerB = request.getParameter("2");
        String answerC = request.getParameter("3");
        String answerD = request.getParameter("4");
        String answer = request.getParameter("Answer");
        boolean error = false;
        String url = "updatePage";
        try {
            QuestionError questionErrors = new QuestionError();
            AnswerErr answerErrors = new AnswerErr();
            if (question_content.isEmpty() || question_content == null) {
                question_content = "";
            }
            if (!question_content.matches(".{1,300}")) {
                error = true;
                questionErrors.setQuestionContentLengthErr("Out of range, please try again!");
            }
            if (!answerA.matches(".{1,100}")) {
                error = true;
                answerErrors.setAnswerALengthErr("Out of range, please try again!");
            }
            if (!answerB.matches(".{1,100}")) {
                error = true;
                answerErrors.setAnswerBLengthErr("Out of range, please try again!");
            }
            if (!answerC.matches(".{1,100}")) {
                error = true;
                answerErrors.setAnswerCLengthErr("Out of range, please try again!");
            }
            if (!answerD.matches(".{1,100}")) {
                error = true;
                answerErrors.setAnswerDLengthErr("Out of range, please try again!");
            }
            if (answerA.matches(answerB) || answerA.matches(answerC) || answerA.matches(answerD) || answerB.matches(answerC) || answerB.matches(answerD) || answerC.matches(answerD)) {
                error = true;
                answerErrors.setAnswerDuplicated("Answer is duplicated. Please try again!");
            }
            if (error == true) {
                request.setAttribute("QUESTIONERROR", questionErrors);
                request.setAttribute("ANSWERERROR", answerErrors);
                request.setAttribute("QUESTIONCONTENT", question_content);
                request.setAttribute("QUESTIONID", questionId);
                request.setAttribute("SUBJECTNAME", subjectName);
                request.setAttribute("SEARCHVALUE", searchValue);
                request.setAttribute("STATUSOFSEARCH", statusofsearch);
                request.setAttribute("STATUS", status);
            } else {
                QuestionDAO questiondao = new QuestionDAO();
                questiondao.update(questionId, question_content, status);
                AnswerDAO answerdao = new AnswerDAO();
                answerdao.updateAnswerContent(answerA, "A", questionId);
                answerdao.updateAnswerContent(answerB, "B", questionId);
                answerdao.updateAnswerContent(answerC, "C", questionId);
                answerdao.updateAnswerContent(answerD, "D", questionId);
                if (answer.equals("A")) {
                    answerdao.updateAnswerCorrect(true, "A", questionId);
                    answerdao.updateAnswerCorrect(false, "B", questionId);
                    answerdao.updateAnswerCorrect(false, "C", questionId);
                    answerdao.updateAnswerCorrect(false, "D", questionId);
                }
                if (answer.equals("B")) {
                    answerdao.updateAnswerCorrect(false, "A", questionId);
                    answerdao.updateAnswerCorrect(true, "B", questionId);
                    answerdao.updateAnswerCorrect(false, "C", questionId);
                    answerdao.updateAnswerCorrect(false, "D", questionId);
                }
                if (answer.equals("C")) {
                    answerdao.updateAnswerCorrect(false, "A", questionId);
                    answerdao.updateAnswerCorrect(false, "B", questionId);
                    answerdao.updateAnswerCorrect(true, "C", questionId);
                    answerdao.updateAnswerCorrect(false, "D", questionId);
                }
                if (answer.equals("D")) {
                    answerdao.updateAnswerCorrect(false, "A", questionId);
                    answerdao.updateAnswerCorrect(false, "B", questionId);
                    answerdao.updateAnswerCorrect(false, "C", questionId);
                    answerdao.updateAnswerCorrect(true, "D", questionId);
                }
                url = "search?txtSearchValue=" + searchValue + "&subjectName=" + subjectName + "&cbStatusOfSearch=" + statusofsearch + "&page=" + page;
                request.setAttribute("SUBJECTNAME", subjectName);
            }
        } catch (SQLException e) {
            log("Error at UpdateQuestionServlet_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at UpdateQuestionServlet_Naming: " + e.getMessage());
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
