package com.example.Reimbursement.Request.App.DALAndSAL.CategoryDAL;

import com.example.Reimbursement.Request.App.Entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category addCategory(Category category);

    List<Category> getAllCategories();
    Category getCategoryByName(String categoryName)
;    Category getCategoryById(String categoryId);
    Category updateCategory(Category category);
    boolean deleteCategory(String categoryId);
}
