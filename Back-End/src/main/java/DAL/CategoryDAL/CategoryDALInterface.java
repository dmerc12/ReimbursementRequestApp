package DAL.CategoryDAL;

import Entities.Data.Category;

import java.util.List;

public interface CategoryDALInterface {
    Category addCategory(Category category);
    List<Category> getAllCategories(int employeeId);
    Category getCategoryById(int categoryId);
    Category updateCategory(Category category);
    int deleteCategory(int categoryId);
}
