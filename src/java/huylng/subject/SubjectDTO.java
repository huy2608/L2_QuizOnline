/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.subject;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Shi
 */
public class SubjectDTO implements Serializable{
    private String subjectId;
    private String name;
    private int numberOfQuestion;
    private Date timeDuration;
    private Date startDate;
    private Date endDate;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectId, String name, int numberOfQuestion, Date timeDuration, Date startDate, Date endDate) {
        this.subjectId = subjectId;
        this.name = name;
        this.numberOfQuestion = numberOfQuestion;
        this.timeDuration = timeDuration;
        this.startDate = startDate;
        this.endDate = endDate;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the numberOfQuestion
     */
    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    /**
     * @param numberOfQuestion the numberOfQuestion to set
     */
    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    /**
     * @return the timeDuration
     */
    public Date getTimeDuration() {
        return timeDuration;
    }

    /**
     * @param timeDuration the timeDuration to set
     */
    public void setTimeDuration(Date timeDuration) {
        this.timeDuration = timeDuration;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
