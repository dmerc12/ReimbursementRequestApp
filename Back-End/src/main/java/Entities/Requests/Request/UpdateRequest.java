package Entities.Requests.Request;

public class UpdateRequest {
    private int sessionId;
    private int requestId;
    private int categoryId;
    private String comment;
    private double amount;
    public UpdateRequest () {

    }

    public UpdateRequest (int sessionId, int requestId, int categoryId, String comment, double amount) {
        this.sessionId = sessionId;
        this.requestId = requestId;
        this.categoryId = categoryId;
        this.comment = comment;
        this.amount = amount;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
