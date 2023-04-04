package Entities.Data;

public class Request {
    private int requestId;
    private int employeeId;
    private int categoryId;
    private String comment;
    private double amount;
    public Request () {

    }

    public Request(int requestId, int employeeId, int categoryId, String comment, double amount) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.categoryId = categoryId;
        this.comment = comment;
        this.amount = amount;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
