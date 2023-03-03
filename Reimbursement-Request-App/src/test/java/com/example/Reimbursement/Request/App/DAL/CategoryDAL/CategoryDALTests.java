package com.example.Reimbursement.Request.App.DAL.CategoryDAL;

import com.example.Reimbursement.Request.App.Entities.Category;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CategoryDALTests {
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();

    Category successfulCategory = new Category(0,"success");
    Category updatedCategory = new Category(successfulCategory.getCategoryId(),"updated");

    @Test
    public void addCategorySuccess() {
        Category result = categoryDAO.addCategory(successfulCategory);
        Assert.assertNotEquals(result.getCategoryId(), 0);
    }

    @Test
    public void getAllCategoriesSuccess() {
        List<Category> result = categoryDAO.getAllCategories();
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void getCategoryByIdSuccess() {
        Category result = categoryDAO.getCategoryById(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateCategorySuccess() {
        Category result = categoryDAO.updateCategory(updatedCategory);
        Assert.assertEquals(updatedCategory.getCategoryName(), result.getCategoryName());
    }

    @Test
    public void deleteCategorySuccess() {
        boolean result = categoryDAO.deleteCategory(successfulCategory.getCategoryId());
        Assert.assertTrue(result);
    }
}
