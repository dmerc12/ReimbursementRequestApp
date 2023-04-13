package SAL;

import DAL.CategoryDAL.CategoryDALImplementation;
import DAL.EmployeeDAL.EmployeeDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Category;
import SAL.CategorySAL.CategorySALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategorySALTests {
    int currentCategoryId = 1;
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO, employeeSAO);
    Category successCategory = new Category(0, -1, "success");
    Category updateCategory = new Category(currentCategoryId, successCategory.getEmployeeId(), "updated");

    @Test
    public void aa_addCategoryNameEmpty() {
        try {
            Category testCategory = new Category(0, -1, "");
            categorySAO.addCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot be left empty, please try " +
                    "again!");
        }
    }

    @Test
    public void ab_addCategoryNameTooLong() {
        try {
            Category testCategory = new Category(0, -1, "this is way too long and so it should fail " +
                    "and bring about the desired error message");
            categorySAO.addCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void ac_addCategoryNameAlreadyTaken() {
        try {
            Category testCategory = new Category(0, -1, "test");
            categorySAO.addCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category with this name already exists, please try again!");
        }
    }

    @Test
    public void ad_addCategoryEmployeeNotFound() {
        try {
            Category testCategory = new Category(0, -5000000, "this is fine");
            categorySAO.addCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void ae_addCategorySuccess() {
        Category result = categorySAO.addCategory(successCategory);
        Assert.assertNotEquals(result.getCategoryId(), 0);
    }

    @Test
    public void ba_getAllCategoriesNoneFound() {
        try {
            categorySAO.getAllCategories(-2);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No categories found, please try again!");
        }
    }

    @Test
    public void bb_getAllCategoriesNoEmployeeFound() {
        try {
            categorySAO.getAllCategories(-500000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void bc_getAllCategoriesSuccess() {
        List<Category> result = categorySAO.getAllCategories(-1);
        Assert.assertTrue(result.size() >= 1);
    }

    @Test
    public void ca_getCategoryNoneFound() {
        try {
            categorySAO.getCategory(-5000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category not found, please try again!");
        }
    }

    @Test
    public void cb_getCategorySuccess(){
        Category result = categorySAO.getCategory(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void da_updateCategoryNameEmpty() {
        try {
            Category testCategory = new Category(currentCategoryId, -1, "");
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot be left empty, please try" +
                    " again!");
        }

    }

    @Test
    public void db_updateCategoryNameTooLong() {
        try {
            Category testCategory = new Category(currentCategoryId, -1, "this is way too " +
                    "long and so it should fail and bring about the desired error message");
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot exceed 60 characters, " +
                    "please try again!");
        }

    }

    @Test
    public void dc_updateCategoryNameNotChanged() {
        try {
            Category testCategory = new Category(currentCategoryId,successCategory.getEmployeeId(),
                    successCategory.getCategoryName());
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Nothing changed, please try again!");
        }

    }

    @Test
    public void dd_updateCategoryNotFound() {
        try {
            Category testCategory = new Category(-500000000, -1, updateCategory.getCategoryName());
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category not found, please try again!");
        }
    }

    @Test
    public void de_updateCategoryEmployeeNotFound() {
        try {
            Category testCategory = new Category(currentCategoryId, -50000000, updateCategory.getCategoryName());
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void df_updateCategoryNameAlreadyExists() {
        try {
            Category testCategory = new Category(currentCategoryId, -1, "test");
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category with this name already exists, please try again!");
        }

    }

    @Test
    public void dg_updateCategorySuccess() {
        Category result = categorySAO.updateCategory(updateCategory);
        Assert.assertEquals(updateCategory.getCategoryName(), result.getCategoryName());
    }

    @Test
    public void o_deleteCategoryNoneFound() {
        try {
            categorySAO.deleteCategory(-500000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category not found, please try again!");
        }
    }

    @Test
    public void p_deleteCategorySuccess() {
        int result = categorySAO.deleteCategory(currentCategoryId);
        Assert.assertTrue(result != 0);
    }
}
