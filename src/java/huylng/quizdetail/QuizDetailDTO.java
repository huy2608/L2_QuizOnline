/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.quizdetail;

import java.io.Serializable;

/**
 *
 * @author Shi
 */
public class QuizDetailDTO implements Serializable{
    private String quizDetailId;
    private String quizId;
    private String questionContent;
    private String answerContent;
    private boolean isCorrect;

    public QuizDetailDTO() {
    }

    public QuizDetailDTO(String quizDetailId, String quizId, String questionContent, String answerContent, boolean isCorrect) {
        this.quizDetailId = quizDetailId;
        this.quizId = quizId;
        this.questionContent = questionContent;
        this.answerContent = answerContent;
        this.isCorrect = isCorrect;
    }

    /**
     * @return the quizDetailId
     */
    public String getQuizDetailId() {
        return quizDetailId;
    }

    /**
     * @param quizDetailId the quizDetailId to set
     */
    public void setQuizDetailId(String quizDetailId) {
        this.quizDetailId = quizDetailId;
    }

    /**
     * @return the quizId
     */
    public String getQuizId() {
        return quizId;
    }

    /**
     * @param quizId the quizId to set
     */
    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    /**
     * @return the questionContent
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * @param questionContent the questionContent to set
     */
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    /**
     * @return the answerContent
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * @param answerContent the answerContent to set
     */
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    /**
     * @return the isCorrect
     */
    public boolean getIsCorrect() {
        return isCorrect;
    }

    /**
     * @param isCorrect the isCorrect to set
     */
    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
}
