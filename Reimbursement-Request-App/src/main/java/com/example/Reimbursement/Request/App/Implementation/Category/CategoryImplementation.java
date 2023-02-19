package com.example.Reimbursement.Request.App.Implementation.Category;

import com.example.Reimbursement.Request.App.Entities.Category;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.CategoryAlreadyExists;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.NoneFound;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CategoryImplementation implements CategoryInterface {
    private CategoryRepository categoryRepository;

    public static Logger logger = LogManager.getLogger(CategoryImplementation.class);

    @Override
    public Category addCategory(Category categoryNameInput) {
        logger.info("Beginning category implementation method add category with data: " + categoryNameInput + ".");
        Category existingCategory = getCategoryByName(categoryNameInput.getCategoryName());
        if (existingCategory == null) {
            Category newCategory = new Category();
            newCategory.setCategoryName(categoryNameInput.getCategoryName());
            Category insertedCategory = categoryRepository.mongoTemplate.insert(newCategory);
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
        List<Category> categoryList = categoryRepository.mongoTemplate.findAll(Category.class);
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
        Query query = new Query();
        query.addCriteria(Criteria.where("categoryName").is(categoryName));
        Category resultingCategory = categoryRepository.mongoTemplate.findOne(query, Category.class);
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
        logger.info("Beginning category implementation method get category by ID with data: " + categoryId + ".");
        Query query = new Query();
        query.addCriteria(Criteria.where("categoryId").is(categoryId));
        Category resultingCategory = categoryRepository.mongoTemplate.findOne(query, Category.class);
        if (resultingCategory == null) {
            logger.warn("Error with category implementation method get category by ID, category not found.");
            throw new NoneFound("No category found, please try again!");
        } else {
            logger.info("Finishing category implementation method get category by ID with result: " +
                    resultingCategory + ".");
            return resultingCategory;
        }
    }

    @Override
    public Category updateCategory(Category category) {
        logger.info("Beginning category implementation method update category with data: " + category + ".");
        Query query = new Query();
        query.addCriteria(Criteria.where("categoryId").is(category.getCategoryId()));
        Update update = new Update();
        update.set("categoryId", category.getCategoryId());
        update.set("categoryName", category.getCategoryName());
        categoryRepository.mongoTemplate.updateFirst(query, update, Category.class);
        Category updatedCategory = getCategoryById(category.getCategoryId());
                logger.info("Finishing category implementation method update category with result: " + updatedCategory + ".");
        return updatedCategory;
    }

    @Override
    public boolean deleteCategory(String categoryId) {
        logger.info("Beginning category implementation method delete category with data: " + categoryId + ".");
        Query query = new Query();
        query.addCriteria(Criteria.where("categoryId").is(categoryId));
        categoryRepository.mongoTemplate.remove(query, Category.class);
        return true;
    }

}
