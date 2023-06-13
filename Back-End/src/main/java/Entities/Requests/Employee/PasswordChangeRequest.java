package Entities.Requests.Employee;

public class PasswordChangeRequest {
    private int sessionId;
    private String password;
    private String confirmationPassword;

    public PasswordChangeRequest() {

    }

    public PasswordChangeRequest(int sessionId, String password, String confirmationPassword) {
        this.sessionId = sessionId;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationPassword() { return confirmationPassword; }

    public void setConfirmationPassword(String confirmationPassword) { this.confirmationPassword = confirmationPassword; }
}
