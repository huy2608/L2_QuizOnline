/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.question;

import java.io.Serializable;

/**
 *
 * @author Shi
 */
public class QuestionDTO implements Serializable {

    private String questionId;
    private String subjectId;
    private String question_content;
    private String status;

    public QuestionDTO() {
    }

    public QuestionDTO(String questionId, String subjectId, String question_content, String status) {
        this.questionId = questionId;
        this.subjectId = subjectId;
        this.question_content = question_content;
        this.status = status;
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
     * @return the subjectId
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return the question_content
     */
    public String getQuestion_content() {
        return question_content;
    }

    /**
     * @param question_content the question_content to set
     */
    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
