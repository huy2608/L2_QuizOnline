/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.quiz;

import java.io.Serializable;

/**
 *
 * @author Shi
 */
public class QuizError implements Serializable{
    private String invalidStartDate;
    private String invalidEndDate;
    private String invalidTimeDuration;
    private String numberOfQuestionOutOfOrder;
    
    public QuizError() {
    }

    public QuizError(String invalidStartDate, String invalidEndDate, String invalidTimeDuration, String numberOfQuestionOutOfOrder) {
        this.invalidStartDate = invalidStartDate;
        this.invalidEndDate = invalidEndDate;
        this.invalidTimeDuration = invalidTimeDuration;
        this.numberOfQuestionOutOfOrder = numberOfQuestionOutOfOrder;
    }

    /**
     * @return the invalidStartDate
     */
    public String getInvalidStartDate() {
        return invalidStartDate;
    }

    /**
     * @param invalidStartDate the invalidStartDate to set
     */
    public void setInvalidStartDate(String invalidStartDate) {
        this.invalidStartDate = invalidStartDate;
    }

    /**
     * @return the invalidEndDate
     */
    public String getInvalidEndDate() {
        return invalidEndDate;
    }

    /**
     * @param invalidEndDate the invalidEndDate to set
     */
    public void setInvalidEndDate(String invalidEndDate) {
        this.invalidEndDate = invalidEndDate;
    }

    /**
     * @return the invalidTimeDuration
     */
    public String getInvalidTimeDuration() {
        return invalidTimeDuration;
    }

    /**
     * @param invalidTimeDuration the invalidTimeDuration to set
     */
    public void setInvalidTimeDuration(String invalidTimeDuration) {
        this.invalidTimeDuration = invalidTimeDuration;
    }

    /**
     * @return the numberOfQuestionOutOfOrder
     */
    public String getNumberOfQuestionOutOfOrder() {
        return numberOfQuestionOutOfOrder;
    }

    /**
     * @param numberOfQuestionOutOfOrder the numberOfQuestionOutOfOrder to set
     */
    public void setNumberOfQuestionOutOfOrder(String numberOfQuestionOutOfOrder) {
        this.numberOfQuestionOutOfOrder = numberOfQuestionOutOfOrder;
    }


   
    
}
