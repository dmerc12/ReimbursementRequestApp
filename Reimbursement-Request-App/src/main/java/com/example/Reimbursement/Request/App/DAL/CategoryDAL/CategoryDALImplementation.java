package com.example.Reimbursement.Request.App.DAL.CategoryDAL;

import com.example.Reimbursement.Request.App.Entities.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryDALImplementation implements CategoryDALInterface {
    public static Logger logger = LogManager.getLogger(CategoryDALImplementation.class);

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return null;
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public boolean deleteCategory(String categoryId) {
        return false;
    }
}
