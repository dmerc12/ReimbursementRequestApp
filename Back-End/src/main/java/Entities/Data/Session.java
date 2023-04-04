package Entities.Data;

import java.time.LocalDateTime;

public class Session {
    private int sessionId;
    private int employeeId;
    private LocalDateTime expiration;

    public Session () {

    }

    public Session (int sessionId, int employeeId, LocalDateTime expiration) {
        this.sessionId = sessionId;
        this.employeeId = employeeId;
        this.expiration = expiration;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }
}
