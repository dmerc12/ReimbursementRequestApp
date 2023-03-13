package com.example.Reimbursement.Request.App.SAL;

import com.example.Reimbursement.Request.App.DAL.CategoryDAL.CategoryDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Category;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.SAL.CategorySAL.CategorySALImplementation;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CategorySALTests {
    int currentCategoryId = 1;
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO);
    Category successCategory = new Category(0, "success");
    Category updateCategory = new Category(currentCategoryId, "updated");

    @Test(expected = GeneralError.class)
    public void getAllCategoriesNoneFound() {
        try {
            categorySAO.getAllCategories();
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No categories found, please try again!");
        }
    }

    @Test(expected = GeneralError.class)
    public void addCategoryNameEmpty() {
        try {
            Category testCategory = new Category(0, "");
            categorySAO.addCategory(testCategory);
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot be left empty, please try " +
                    "again!");
        }
    }

    @Test(expected = GeneralError.class)
    public void addCategoryNameTooLong() {
        try {
            Category testCategory = new Category(0, "this is way too long and so it should fail " +
                    "and bring about the desired error message");
            categorySAO.addCategory(testCategory);
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test(expected = GeneralError.class)
    public void addCategoryNameAlreadyTaken() {
        try {
            Category testCategory = new Category(0, "test");
            categorySAO.addCategory(testCategory);
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category with this name already exists, please try again!");
        }
    }

    @Test()
    public void addCategorySuccess() {
        Category result = categorySAO.addCategory(successCategory);
        Assert.assertNotEquals(result.getCategoryId(), 0);
    }

    @Test
    public void getAllCategoriesSuccess() {
        List<Category> result = categorySAO.getAllCategories();
        Assert.assertFalse(result.size() <= 1);
    }

    @Test(expected = GeneralError.class)
    public void getCategoryNoneFound() {
        try {
            categorySAO.getCategory(-5000000);
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category not found, please try again!");
        }
    }

    @Test
    public void getCategorySuccess(){
        Category result = categorySAO.getCategory(-1);
        Assert.assertNotNull(result);
    }

    @Test(expected = GeneralError.class)
    public void updateCategoryNameEmpty() {
        try {
            Category testCategory = new Category(currentCategoryId, "");
            categorySAO.updateCategory(testCategory);
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot be left empty, please try" +
                    " again!");
        }

    }

    @Test(expected = GeneralError.class)
    public void updateCategoryNameTooLong() {
        try {
            Category testCategory = new Category(currentCategoryId, "this is way too long and so it " +
                    "should fail and bring about the desired error message");
            categorySAO.updateCategory(testCategory);
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot exceed 60 characters, " +
                    "please try again!");
        }

    }

    @Test(expected = GeneralError.class)
    public void updateCategoryNameNotChanged() {
        try {
            Category testCategory = new Category(currentCategoryId, successCategory.getCategoryName());
            categorySAO.updateCategory(testCategory);
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Nothing changed, please try again!");
        }

    }

    @Test(expected = GeneralError.class)
    public void updateCategoryNotFound() {
        try {
            Category testCategory = new Category(-500000000, updateCategory.getCategoryName());
            categorySAO.updateCategory(testCategory);
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category not found, please try again!");
        }

    }

    @Test(expected = GeneralError.class)
    public void updateCategoryNameAlreadyExists() {
        try {
            Category testCategory = new Category(currentCategoryId, "test");
            categorySAO.updateCategory(testCategory);
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category with this name already exists, please try again!");
        }

    }

    @Test
    public void updateCategorySuccess() {
        Category result = categorySAO.updateCategory(updateCategory);
        Assert.assertEquals(updateCategory.getCategoryName(), result.getCategoryName());
    }

    @Test(expected = GeneralError.class)
    public void deleteCategoryNoneFound() {
        try {
            categorySAO.deleteCategory(-500000000);
            assert false;
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category not found, please try again!");
        }
    }

    @Test
    public void deleteCategorySuccess() {
        int result = categorySAO.deleteCategory(currentCategoryId);
        Assert.assertTrue(result != 0);
    }
}
