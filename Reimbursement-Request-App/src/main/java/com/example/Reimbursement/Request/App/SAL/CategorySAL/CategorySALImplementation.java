package com.example.Reimbursement.Request.App.SAL.CategorySAL;

import com.example.Reimbursement.Request.App.DAL.CategoryDAL.CategoryDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Category;

import java.util.List;

public class CategorySALImplementation implements CategorySALInterface{

    private CategoryDALImplementation categoryDAO;

    @Override
    public Category serviceAddCategory(Category category) {
        return null;
    }

    @Override
    public List<Category> serviceGetAllCategories() {
        return null;
    }

    @Override
    public Category serviceGetCategory(int categoryId) {
        return null;
    }

    @Override
    public Category serviceUpdateCategory(Category category) {
        return null;
    }

    @Override
    public boolean serviceDeleteCategory(int categoryId) {
        return false;
    }
}
