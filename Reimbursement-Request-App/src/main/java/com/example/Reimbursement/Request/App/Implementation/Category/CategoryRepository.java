package com.example.Reimbursement.Request.App.Implementation.Category;

import com.example.Reimbursement.Request.App.Entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
