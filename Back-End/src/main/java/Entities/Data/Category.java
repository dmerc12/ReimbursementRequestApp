package Entities.Data;

import java.util.Objects;

public class Category {
    private int categoryId;
    private int employeeId;
    private String categoryName;
    public Category() {

    }

    public Category(int categoryId, int employeeId, String categoryName) {
        this.categoryId = categoryId;
        this.employeeId = employeeId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
