/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.userquiz;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Shi
 */
public class UserQuizDTO implements Serializable{
    private String userQuizId;
    private float totalPoint;
    private Date timeDuration;
    private String email;
    private String subjectName;

    public UserQuizDTO() {
    }

    public UserQuizDTO(String userQuizId, float totalPoint, Date timeDuration, String email, String subjectName) {
        this.userQuizId = userQuizId;
        this.totalPoint = totalPoint;
        this.timeDuration = timeDuration;
        this.email = email;
        this.subjectName = subjectName;
    }


    /**
     * @return the userQuizId
     */
    public String getUserQuizDetailId() {
        return getUserQuizId();
    }

    /**
     * @param userQuizId the userQuizId to set
     */
    public void setUserQuizDetailId(String userQuizId) {
        this.setUserQuizId(userQuizId);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the totalPoint
     */
    public float getTotalPoint() {
        return totalPoint;
    }

    /**
     * @param totalPoint the totalPoint to set
     */
    public void setTotalPoint(float totalPoint) {
        this.totalPoint = totalPoint;
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
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @param subjectName the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
}
