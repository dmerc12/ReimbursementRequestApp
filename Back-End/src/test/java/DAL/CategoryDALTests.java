package DAL;

import DAL.CategoryDAL.CategoryDALImplementation;
import Entities.Data.Category;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryDALTests {int currentCategoryId = 1;
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    Category successfulCategory = new Category(0, -1, "success");
    Category updatedCategory = new Category(currentCategoryId, successfulCategory.getEmployeeId(), "updated");

    @Test
    public void a_addCategorySuccess() {
        Category result = categoryDAO.addCategory(successfulCategory);
        Assert.assertNotEquals(result.getCategoryId(), 0);
    }

    @Test
    public void b_getAllCategoriesSuccess() {
        List<Category> result = categoryDAO.getAllCategories(-1);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void c_getCategoryByIdSuccess() {
        Category result = categoryDAO.getCategoryById(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void d_updateCategorySuccess() {
        Category result = categoryDAO.updateCategory(updatedCategory);
        Assert.assertEquals(updatedCategory.getCategoryName(), result.getCategoryName());
    }

    @Test
    public void e_deleteCategorySuccess() {
        int result = categoryDAO.deleteCategory(currentCategoryId);
        Assert.assertTrue(result != 0);
    }
}
