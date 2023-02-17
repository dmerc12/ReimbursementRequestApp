package com.example.Reimbursement.Request.App.DALAndSAL.CategoryDAL;


import com.example.Reimbursement.Request.App.Entities.Category;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.CategoryAlreadyExists;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.NoneFound;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@AllArgsConstructor
public abstract class CategoryImplementation implements CategoryRepository {
    private CategoryRepository categoryDB;

    public static Logger logger = LogManager.getLogger(CategoryImplementation.class);

    @Override
    public Category addCategory(Category categoryNameInput) {
        logger.info("Beginning category implementation method add category with data: " + categoryNameInput + ".");
        Category existingCategory = categoryDB.getCategoryByName(categoryNameInput.getCategoryName());
        if (existingCategory == null) {
            Category newCategory = new Category(categoryNameInput.getCategoryName());
            Category insertedCategory = categoryDB.insert(newCategory);
            logger.info("Finishing category implementation method add category with result: " + insertedCategory
                    + ".");
            return insertedCategory;
        } else {
            logger.warn("Error with category implementation method add category, category name already taken.");
            throw new CategoryAlreadyExists("A category already exists with that name, please try again!");
        }
    }

    @Override
    public List<Category> getAllCategories() {
        logger.info("Beginning category implementation method get all categories.");
        List<Category> categoryList = categoryDB.findAll();
        if (categoryList.isEmpty()) {
            logger.warn("Error with category implementation method get all categories, no categories found.");
            throw new NoneFound("No categories found, please try again!");
        } else {
            logger.info("Finishing category implementation method get all categories with result: " + categoryList
                    + ".");
            return categoryList;
        }
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        logger.info("Beginning category implementation method get category by name with data: " + categoryName + ".");
        Category resultingCategory = categoryDB.getCategoryByName(categoryName);
        if (resultingCategory == null) {
            logger.warn("Error with category implementation method get category by name, category not found.");
            throw new NoneFound("No category found, please try again!");
        } else {
            logger.info("Finishing category implementation method get category by name with result: " +
                    resultingCategory + ".");
            return resultingCategory;
        }
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
