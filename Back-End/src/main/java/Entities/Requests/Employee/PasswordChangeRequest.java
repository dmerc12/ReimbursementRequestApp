package Entities.Requests.Employee;

public class PasswordChangeRequest {
    private int sessionId;
    private String password;

    public PasswordChangeRequest() {

    }

    public PasswordChangeRequest(int sessionId, String password) {
        this.sessionId = sessionId;
        this.password = password;
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
}
