/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.question;

/**
 *
 * @author Shi
 */
public class QuestionError {

    private String questionIdLengthErr;
    private String questionIdDuplicated;
    private String questionContentLengthErr;

    public QuestionError() {
    }

    public QuestionError(String questionIdLengthErr, String questionIdDuplicated, String questionContentLengthErr) {
        this.questionIdLengthErr = questionIdLengthErr;
        this.questionIdDuplicated = questionIdDuplicated;
        this.questionContentLengthErr = questionContentLengthErr;
    }

    /**
     * @return the questionIdDuplicated
     */
    public String getQuestionIdDuplicated() {
        return questionIdDuplicated;
    }

    /**
     * @param questionIdDuplicated the questionIdDuplicated to set
     */
    public void setQuestionIdDuplicated(String questionIdDuplicated) {
        this.questionIdDuplicated = questionIdDuplicated;
    }

    /**
     * @return the questionContentLengthErr
     */
    public String getQuestionContentLengthErr() {
        return questionContentLengthErr;
    }

    /**
     * @param questionContentLengthErr the questionContentLengthErr to set
     */
    public void setQuestionContentLengthErr(String questionContentLengthErr) {
        this.questionContentLengthErr = questionContentLengthErr;
    }

    /**
     * @return the questionIdLengthErr
     */
    public String getQuestionIdLengthErr() {
        return questionIdLengthErr;
    }

    /**
     * @param questionIdLengthErr the questionIdLengthErr to set
     */
    public void setQuestionIdLengthErr(String questionIdLengthErr) {
        this.questionIdLengthErr = questionIdLengthErr;
    }

}
