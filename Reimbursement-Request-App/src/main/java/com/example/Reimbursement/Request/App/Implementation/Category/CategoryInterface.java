package com.example.Reimbursement.Request.App.Implementation.Category;

import com.example.Reimbursement.Request.App.Entities.Category;

import java.util.List;

public interface CategoryInterface {
    Category addCategory(Category category);

    List<Category> getAllCategories();
    Category getCategoryByName(String categoryName)
            ;    Category getCategoryById(String categoryId);
    Category updateCategory(Category category);
    boolean deleteCategory(String categoryId);
}
