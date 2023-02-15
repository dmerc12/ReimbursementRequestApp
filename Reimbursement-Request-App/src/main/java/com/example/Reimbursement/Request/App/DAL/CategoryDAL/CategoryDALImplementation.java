package com.example.Reimbursement.Request.App.DAL.CategoryDAL;


import com.example.Reimbursement.Request.App.Entities.Category;

import java.util.List;

public abstract class CategoryDALImplementation implements CategoryDALInterface {
    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getCategory(int categoryId) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        return false;
    }
}
