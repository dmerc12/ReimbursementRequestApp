package com.example.Reimbursement.Request.App.SAL.CategorySAL;

import com.example.Reimbursement.Request.App.DAL.CategoryDAL.CategoryDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategorySALImplementation implements CategorySALInterface{
    private CategoryDALImplementation categoryDAO;
    public static Logger logger = LogManager.getLogger(CategorySALImplementation.class);
    public CategorySALImplementation(CategoryDALImplementation companyDAO) {
        this.categoryDAO = categoryDAO;
    }
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
    public int deleteCategory(int categoryId) {
        return 0;
    }
}
