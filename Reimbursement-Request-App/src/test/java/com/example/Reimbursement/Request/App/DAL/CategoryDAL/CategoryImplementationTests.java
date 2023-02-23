package com.example.Reimbursement.Request.App.DAL.CategoryDAL;

import com.example.Reimbursement.Request.App.Entities.Category;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.CategoryAlreadyExists;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.NoneFound;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CategoryImplementationTests {
    CategoryImplementation categoryDAO = new CategoryImplementation();

    Category successfulCategory = new Category("success");
    Category updatedCategory = new Category("updated");

    @Test(expected = NoneFound.class)
    public void getAllCategoriesNoneFound() {
        try {
            categoryDAO.getAllCategories();
            Assert.fail();
        } catch (NoneFound error) {
            Assert.assertEquals(error.getMessage(), "No categories found, please try again!");
        }
    }

    @Test
    public void addCategorySuccess() {
        Category result = categoryDAO.addCategory(successfulCategory);
        Assert.assertNotNull(result.getCategoryId());
    }

    @Test(expected = CategoryAlreadyExists.class)
    public void addCategoryNameAlreadyTaken() {
        try {
            Category testCategory = new Category("test");
            categoryDAO.addCategory(testCategory);
            Assert.fail();
        } catch(CategoryAlreadyExists error) {
            Assert.assertEquals(error.getMessage(),
                    "A category already exists with that name, please try again!");
        }
    }

    @Test
    public void getAllCategoriesSuccess() {
        List<Category> result = categoryDAO.getAllCategories();
        Assert.assertNotNull(result);
    }

    @Test(expected = NoneFound.class)
    public void getCategoryByNameDoesNotExist() {
        try {
            categoryDAO.getCategoryByName("does not exist");
            Assert.fail();
        } catch (NoneFound error) {
            Assert.assertEquals(error.getMessage(), "No category found, please try again!");
        }
    }

    @Test
    public void getCategoryByNameSuccess() {
        Category result = categoryDAO.getCategoryByName(successfulCategory.getCategoryName());
        Assert.assertNotNull(result);
    }

    @Test(expected = NoneFound.class)
    public void getCategoryByIdDoesNotExist() {
        try {
            categoryDAO.getCategoryByName("does not exist");
            Assert.fail();
        } catch (NoneFound error) {
            Assert.assertEquals(error.getMessage(), "No category found, please try again!");
        }
    }

    @Test
    public void getCategoryByIdSuccess() {
        Category result = categoryDAO.getCategoryById(successfulCategory.getCategoryId());
        Assert.assertNotNull(result);
    }

    @Test(expected = NoneFound.class)
    public void updateCategoryNotFound() {
        try {
            Category nonExistentCategory = new Category("I don't exist");
            categoryDAO.updateCategory(nonExistentCategory);
            Assert.fail();
        } catch (NoneFound error) {
            Assert.assertEquals(error.getMessage(), "No category found, please try again!");
        }
    }

    @Test(expected = CategoryAlreadyExists.class)
    public void updateCategoryAlreadyExists() {
        try {
            categoryDAO.updateCategory(successfulCategory);
            Assert.fail();
        } catch (CategoryAlreadyExists error) {
            Assert.assertEquals(error.getMessage(),
                    "A category already exists with that name, please try again!");
        }
    }

    @Test
    public void updateCategorySuccess() {
        Category result = categoryDAO.updateCategory(updatedCategory);
        Assert.assertEquals(result.getCategoryName(), updatedCategory.getCategoryName());
    }

    @Test(expected = NoneFound.class)
    public void deleteCategoryNotFound() {
        try {
            categoryDAO.deleteCategory("does not exist");
            Assert.fail();
        } catch (NoneFound error) {
            Assert.assertEquals(error.getMessage(), "No category found, please try again!");
        }
    }

    @Test
    public void deleteCategorySuccess() {
        boolean result = categoryDAO.deleteCategory(successfulCategory.getCategoryId());
        Assert.assertTrue(result);
    }
}
