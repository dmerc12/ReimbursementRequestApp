package SAL.CategorySAL;

import Entities.Data.Category;

import java.util.List;

public interface CategorySALInterface {
    Category addCategory(Category category);
    List<Category> getAllCategories();
    Category getCategory(int categoryId);
    Category updateCategory(Category category);
    int deleteCategory(int categoryId);
}
