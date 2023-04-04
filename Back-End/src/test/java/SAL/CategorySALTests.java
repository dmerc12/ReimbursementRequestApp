package SAL;

import DAL.CategoryDAL.CategoryDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Category;
import SAL.CategorySAL.CategorySALImplementation;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategorySALTests {
    int currentCategoryId = 1;
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO);
    Category successCategory = new Category(0, "success");
    Category updateCategory = new Category(currentCategoryId, "updated");

    @Test
    public void a_getAllCategoriesNoneFound() {
        try {
            categorySAO.getAllCategories();
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No categories found, please try again!");
        }
    }

    @Test
    public void b_addCategoryNameEmpty() {
        try {
            Category testCategory = new Category(0, "");
            categorySAO.addCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot be left empty, please try " +
                    "again!");
        }
    }

    @Test
    public void c_addCategoryNameTooLong() {
        try {
            Category testCategory = new Category(0, "this is way too long and so it should fail " +
                    "and bring about the desired error message");
            categorySAO.addCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void d_addCategoryNameAlreadyTaken() {
        try {
            Category testCategory = new Category(0, "test");
            categorySAO.addCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category with this name already exists, please try again!");
        }
    }

    @Test()
    public void e_addCategorySuccess() {
        Category result = categorySAO.addCategory(successCategory);
        Assert.assertNotEquals(result.getCategoryId(), 0);
    }

    @Test
    public void f_getAllCategoriesSuccess() {
        List<Category> result = categorySAO.getAllCategories();
        Assert.assertTrue(result.size() >= 1);
    }

    @Test
    public void g_getCategoryNoneFound() {
        try {
            categorySAO.getCategory(-5000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category not found, please try again!");
        }
    }

    @Test
    public void h_getCategorySuccess(){
        Category result = categorySAO.getCategory(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void i_updateCategoryNameEmpty() {
        try {
            Category testCategory = new Category(currentCategoryId, "");
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot be left empty, please try" +
                    " again!");
        }

    }

    @Test
    public void j_updateCategoryNameTooLong() {
        try {
            Category testCategory = new Category(currentCategoryId, "this is way too long and so it " +
                    "should fail and bring about the desired error message");
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category name field cannot exceed 60 characters, " +
                    "please try again!");
        }

    }

    @Test
    public void k_updateCategoryNameNotChanged() {
        try {
            Category testCategory = new Category(currentCategoryId, successCategory.getCategoryName());
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Nothing changed, please try again!");
        }

    }

    @Test
    public void l_updateCategoryNotFound() {
        try {
            Category testCategory = new Category(-500000000, updateCategory.getCategoryName());
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category not found, please try again!");
        }

    }

    @Test
    public void m_updateCategoryNameAlreadyExists() {
        try {
            Category testCategory = new Category(currentCategoryId, "test");
            categorySAO.updateCategory(testCategory);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category with this name already exists, please try again!");
        }

    }

    @Test
    public void n_updateCategorySuccess() {
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
