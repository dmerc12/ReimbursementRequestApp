package com.example.Reimbursement.Request.App.Implementation.Category;

import com.example.Reimbursement.Request.App.Entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
