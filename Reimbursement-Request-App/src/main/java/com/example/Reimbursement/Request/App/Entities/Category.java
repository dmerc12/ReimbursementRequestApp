package com.example.Reimbursement.Request.App.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Category {
    @Id
    private String categoryId;
    private String categoryName;
}
