/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.user;

/**
 *
 * @author Shi
 */
public class UserError {
    private String EmailFormatError;
    private String PasswordConfirmError;

    public UserError() {
    }

    public UserError(String EmailFormatError, String PasswordConfirmError) {
        this.EmailFormatError = EmailFormatError;
        this.PasswordConfirmError = PasswordConfirmError;
    }

    /**
     * @return the EmailFormatError
     */
    public String getEmailFormatError() {
        return EmailFormatError;
    }

    /**
     * @param EmailFormatError the EmailFormatError to set
     */
    public void setEmailFormatError(String EmailFormatError) {
        this.EmailFormatError = EmailFormatError;
    }

    /**
     * @return the PasswordConfirmError
     */
    public String getPasswordConfirmError() {
        return PasswordConfirmError;
    }

    /**
     * @param PasswordConfirmError the PasswordConfirmError to set
     */
    public void setPasswordConfirmError(String PasswordConfirmError) {
        this.PasswordConfirmError = PasswordConfirmError;
    }
    
}
