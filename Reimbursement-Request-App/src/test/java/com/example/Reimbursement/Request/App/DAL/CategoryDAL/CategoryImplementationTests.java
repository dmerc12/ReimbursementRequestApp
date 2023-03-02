package com.example.Reimbursement.Request.App.DAL.CategoryDAL;

import com.example.Reimbursement.Request.App.Entities.Category;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.CategoryAlreadyExists;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.NoneFound;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CategoryImplementationTests {
    CategoryImplementation categoryDAO = new CategoryImplementation();

    Category successfulCategory = new Category(0,"success");
    Category updatedCategory = new Category(0,"updated");

    @Test(expected = NoneFound.class)
    public void getAllCategoriesNoneFound() {

    }

    @Test
    public void addCategorySuccess() {

    }

    @Test(expected = CategoryAlreadyExists.class)
    public void addCategoryNameAlreadyTaken() {

    }

    @Test
    public void getAllCategoriesSuccess() {

    }

    @Test(expected = NoneFound.class)
    public void getCategoryByNameDoesNotExist() {

    }

    @Test
    public void getCategoryByNameSuccess() {

    }

    @Test(expected = NoneFound.class)
    public void getCategoryByIdDoesNotExist() {

    }

    @Test
    public void getCategoryByIdSuccess() {

    }

    @Test(expected = NoneFound.class)
    public void updateCategoryNotFound() {

    }

    @Test(expected = CategoryAlreadyExists.class)
    public void updateCategoryAlreadyExists() {

    }

    @Test
    public void updateCategorySuccess() {

    }

    @Test(expected = NoneFound.class)
    public void deleteCategoryNotFound() {

    }

    @Test
    public void deleteCategorySuccess() {

    }
}
