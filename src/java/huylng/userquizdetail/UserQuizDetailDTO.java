/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.userquizdetail;

import java.io.Serializable;

/**
 *
 * @author Shi
 */ 
public class UserQuizDetailDTO implements Serializable {
    private String userQuizDetailId;
    private String userAnswerContent;
    private String quizDetailId;
    private String userQuizId;

    public UserQuizDetailDTO() {
    }

    public UserQuizDetailDTO(String userQuizDetailId, String userAnswerContent, String quizDetailId, String userQuizId) {
        this.userQuizDetailId = userQuizDetailId;
        this.userAnswerContent = userAnswerContent;
        this.quizDetailId = quizDetailId;
        this.userQuizId = userQuizId;
    }


    /**
     * @return the userQuizId
     */
    public String getUserQuizId() {
        return userQuizId;
    }

    /**
     * @param userQuizId the userQuizId to set
     */
    public void setUserQuizId(String userQuizId) {
        this.userQuizId = userQuizId;
    }

  
    /**
     * @return the userAnswerContent
     */
    public String getUserAnswerContent() {
        return userAnswerContent;
    }

    /**
     * @param userAnswerContent the userAnswerContent to set
     */
    public void setUserAnswerContent(String userAnswerContent) {
        this.userAnswerContent = userAnswerContent;
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
     * @return the userQuizDetailId
     */
    public String getUserQuizDetailId() {
        return userQuizDetailId;
    }

    /**
     * @param userQuizDetailId the userQuizDetailId to set
     */
    public void setUserQuizDetailId(String userQuizDetailId) {
        this.userQuizDetailId = userQuizDetailId;
    }
    
    
}
