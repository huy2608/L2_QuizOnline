/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.answer;

/**
 *
 * @author Shi
 */
public class AnswerErr {
    private String answerALengthErr;
    private String answerBLengthErr;
    private String answerCLengthErr;
    private String answerDLengthErr;
    private String answerDuplicated;
    
    public AnswerErr() {
    }

    public AnswerErr(String answerALengthErr, String answerBLengthErr, String answerCLengthErr, String answerDLengthErr, String answerDuplicated) {
        this.answerALengthErr = answerALengthErr;
        this.answerBLengthErr = answerBLengthErr;
        this.answerCLengthErr = answerCLengthErr;
        this.answerDLengthErr = answerDLengthErr;
        this.answerDuplicated = answerDuplicated;
    }


    /**
     * @return the answerALengthErr
     */
    public String getAnswerALengthErr() {
        return answerALengthErr;
    }

    /**
     * @param answerALengthErr the answerALengthErr to set
     */
    public void setAnswerALengthErr(String answerALengthErr) {
        this.answerALengthErr = answerALengthErr;
    }

    /**
     * @return the answerBLengthErr
     */
    public String getAnswerBLengthErr() {
        return answerBLengthErr;
    }

    /**
     * @param answerBLengthErr the answerBLengthErr to set
     */
    public void setAnswerBLengthErr(String answerBLengthErr) {
        this.answerBLengthErr = answerBLengthErr;
    }

    /**
     * @return the answerCLengthErr
     */
    public String getAnswerCLengthErr() {
        return answerCLengthErr;
    }

    /**
     * @param answerCLengthErr the answerCLengthErr to set
     */
    public void setAnswerCLengthErr(String answerCLengthErr) {
        this.answerCLengthErr = answerCLengthErr;
    }

    /**
     * @return the answerDLengthErr
     */
    public String getAnswerDLengthErr() {
        return answerDLengthErr;
    }

    /**
     * @param answerDLengthErr the answerDLengthErr to set
     */
    public void setAnswerDLengthErr(String answerDLengthErr) {
        this.answerDLengthErr = answerDLengthErr;
    }

    /**
     * @return the answerDuplicated
     */
    public String getAnswerDuplicated() {
        return answerDuplicated;
    }

    /**
     * @param answerDuplicated the answerDuplicated to set
     */
    public void setAnswerDuplicated(String answerDuplicated) {
        this.answerDuplicated = answerDuplicated;
    }
   
}
