package com.example.Reimbursement.Request.App.DAL.CategoryDAL;


import com.example.Reimbursement.Request.App.Entities.Category;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public abstract class CategoryImplementation implements CategoryRepository {
    private CategoryRepository categoryDB;

    @Override
    public Category addCategory(Category categoryNameInput) {
        Category newCategory = new Category(categoryNameInput.getCategoryName());
        return categoryDB.insert(newCategory);
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getCategory(String categoryId) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public boolean deleteCategory(String categoryId) {
        return false;
    }

}
