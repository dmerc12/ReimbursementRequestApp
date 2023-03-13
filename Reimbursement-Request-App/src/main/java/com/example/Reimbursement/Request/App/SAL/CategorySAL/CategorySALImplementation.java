package com.example.Reimbursement.Request.App.SAL.CategorySAL;

import com.example.Reimbursement.Request.App.DAL.CategoryDAL.CategoryDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Category;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategorySALImplementation implements CategorySALInterface{
    private CategoryDALImplementation categoryDAO;
    public static Logger logger = LogManager.getLogger(CategorySALImplementation.class);
    public CategorySALImplementation(CategoryDALImplementation companyDAO) {

    }
    @Override
    public Category addCategory(Category category) {
        logger.info("Beginning SAL method add category with category: " + category);
        if (category.getCategoryName().equals("")) {
            logger.warn("SAL method add category, category name left empty");
            throw new GeneralError("Category name field cannot be left empty, please try again!");
        } else if (category.getCategoryName().length() > 60) {
            logger.warn("SAL method add category, category name too long");
            throw new GeneralError("Category name field cannot exceed 60 characters, please try again!");
        } else {
            List<Category> categoryList = getAllCategories();
            if (categoryList.contains(category)) {
                logger.warn("SAL method add category, category name already taken");
                throw new GeneralError("Category with this name already exists, please try again!");
            } else {
                Category result = categoryDAO.addCategory(category);
                logger.info("Finishing SAL method add category with result: " + result);
                return result;
            }
        }
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
