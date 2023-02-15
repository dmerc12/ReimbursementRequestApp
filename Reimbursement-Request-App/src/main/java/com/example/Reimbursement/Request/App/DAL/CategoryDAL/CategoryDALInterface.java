package com.example.Reimbursement.Request.App.DAL.CategoryDAL;

import com.example.Reimbursement.Request.App.Entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryDALInterface extends MongoRepository<Category, String> {
    Category addCategory(Category category);

    List<Category> getAllCategories();
    Category getCategory(int categoryId);
    Category updateCategory(Category category);
    boolean deleteCategory(int categoryId);
}
