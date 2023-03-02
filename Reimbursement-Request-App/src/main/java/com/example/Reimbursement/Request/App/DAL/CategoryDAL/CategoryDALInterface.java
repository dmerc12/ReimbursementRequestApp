package com.example.Reimbursement.Request.App.DAL.CategoryDAL;

import com.example.Reimbursement.Request.App.Entities.Category;

import java.util.List;

public interface CategoryDALInterface {
    Category addCategory(Category category);

    List<Category> getAllCategories();
    Category getCategoryById(int categoryId);
    Category updateCategory(Category category);
    boolean deleteCategory(int categoryId);
}
