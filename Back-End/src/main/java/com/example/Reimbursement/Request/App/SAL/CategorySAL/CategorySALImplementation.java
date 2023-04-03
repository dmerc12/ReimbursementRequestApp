package com.example.Reimbursement.Request.App.SAL.CategorySAL;

import com.example.Reimbursement.Request.App.DAL.CategoryDAL.CategoryDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Data.Category;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategorySALImplementation implements CategorySALInterface{
    private CategoryDALImplementation categoryDAO;
    public static Logger logger = LogManager.getLogger(CategorySALImplementation.class);
    public CategorySALImplementation(CategoryDALImplementation companyDAO) {
        this.categoryDAO = companyDAO;
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
            List<Category> categoryList = categoryDAO.getAllCategories();
            boolean exists = false;
            for (Category existingCategory: categoryList) {
                if (existingCategory.getCategoryName().equals(category.getCategoryName())) {
                    exists = true;
                    break;
                }
            } if (exists) {
                logger.warn("SAL method update category, category already exists");
                throw new GeneralError("Category with this name already exists, please try again!");
            } else {
                Category result = categoryDAO.addCategory(category);
                logger.info("Finishing SAL method add category with category: " + result);
                return result;
            }
        }
    }

    @Override
    public List<Category> getAllCategories() {
        logger.info("Beginning SAL method get all categories");
        List<Category> categoryList = categoryDAO.getAllCategories();
        if (categoryList.size() <= 1) {
            logger.warn("SAL method get all categories, category list empty");
            throw new GeneralError("No categories found, please try again!");
        } else {
            logger.info("Finishing SAL method get all categories with category list: " + categoryList);
            return categoryList;
        }
    }

    @Override
    public Category getCategory(int categoryId) {
        logger.info("Beginning SAL method get category with category ID: " + categoryId);
        Category result = categoryDAO.getCategoryById(categoryId);
        if (result == null) {
            logger.warn("SAL method get category, no category found");
            throw new GeneralError("Category not found, please try again!");
        } else {
            logger.info("Finishing SAL method get category with category: " + result);
            return result;
        }
    }

    @Override
    public Category updateCategory(Category category) {
        logger.info("Beginning SAL method update category with category: " + category);
        if (category.getCategoryName().equals("")) {
            logger.warn("SAL method update category, category name left empty");
            throw new GeneralError("Category name field cannot be left empty, please try again!");
        } else if (category.getCategoryName().length() > 60) {
            logger.warn("SAL method update category, category name too long");
            throw new GeneralError("Category name field cannot exceed 60 characters, please try again!");
        } else {
            List<Category> categoryList = categoryDAO.getAllCategories();
            boolean exists = false;
            Category existingCategoryInformation = getCategory(category.getCategoryId());
            if (category.getCategoryName().equals(existingCategoryInformation.getCategoryName())) {
                logger.warn("SAL method update category, nothing changed");
                throw new GeneralError("Nothing changed, please try again!");
            } for (Category existingCategory: categoryList) {
                if (existingCategory.getCategoryName().equals(category.getCategoryName())) {
                    exists = true;
                    break;
                }
            } if (exists) {
                logger.warn("SAL method update category, category already exists");
                throw new GeneralError("Category with this name already exists, please try again!");
            } else {
                Category result = categoryDAO.updateCategory(category);
                logger.info("Finishing SAL method update category with category: " + result);
                return result;
            }
        }
    }

    @Override
    public int deleteCategory(int categoryId) {
        logger.info("Beginning SAL method delete category with category ID: " + categoryId);
        getCategory(categoryId);
        int result = categoryDAO.deleteCategory(categoryId);
        logger.info("Finishing SAL method delete category with result: " + result);
        return result;
    }
}
