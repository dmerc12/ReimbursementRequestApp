package com.example.Reimbursement.Request.App.DAL.CategoryDAL;

import com.example.Reimbursement.Request.App.Entities.Category;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CategoryImplementationTests {
    private CategoryImplementation categoryDAO;

    Category testCategory = new Category("test");
    Category updatedCategory = new Category("updated");

    @Test
    public void addCategorySuccess() {
        Category result = categoryDAO.addCategory(testCategory);
        Assert.assertNotNull(result.getCategoryId());
    }

    @Test
    public void getAllCategoriesSuccess() {
        List<Category> result = categoryDAO.getAllCategories();
        Assert.assertNotNull(result);
    }

    @Test
    public void getCategorySuccess() {
        Category result = categoryDAO.getCategory(testCategory.getCategoryId());
        Assert.assertNotNull(testCategory.getCategoryId());
    }

    @Test
    public void updateCategorySuccess() {
        Category result = categoryDAO.updateCategory(updatedCategory);
        Assert.assertEquals(result.getCategoryName(), updatedCategory.getCategoryName());
    }

    @Test
    public void deleteCategorySuccess() {
        boolean result = categoryDAO.deleteCategory(testCategory.getCategoryId());
        Assert.assertTrue(result);
    }
}
