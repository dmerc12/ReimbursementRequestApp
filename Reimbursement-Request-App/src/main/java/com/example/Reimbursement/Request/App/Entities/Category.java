package com.example.Reimbursement.Request.App.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "Categories")
public class Category {
    @Id
    private String categoryId;
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
