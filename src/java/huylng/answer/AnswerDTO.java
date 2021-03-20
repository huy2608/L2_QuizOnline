/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.answer;

import java.io.Serializable;

/**
 *
 * @author Shi
 */
public class AnswerDTO implements Serializable{
    private String answerId;
    private String questionId;
    private String answer_content;
    private boolean isCorrect;

    public AnswerDTO() {
    }

    public AnswerDTO(String answerId, String questionId, String answer_content, boolean isCorrect) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answer_content = answer_content;
        this.isCorrect = isCorrect;
    }



    /**
     * @return the questionId
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * @return the answer_content
     */
    public String getAnswer_content() {
        return answer_content;
    }

    /**
     * @param answer_content the answer_content to set
     */
    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    /**
     * @return the isCorrect
     */
    public boolean isIsCorrect() {
        return isCorrect;
    }

    /**
     * @param isCorrect the isCorrect to set
     */
    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    /**
     * @return the answerId
     */
    public String getAnswerId() {
        return answerId;
    }

    /**
     * @param answerId the answerId to set
     */
    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
    
}
