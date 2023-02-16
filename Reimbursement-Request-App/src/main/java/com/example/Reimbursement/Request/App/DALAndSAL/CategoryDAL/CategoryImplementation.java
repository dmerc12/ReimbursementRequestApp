package com.example.Reimbursement.Request.App.DALAndSAL.CategoryDAL;


import com.example.Reimbursement.Request.App.Entities.Category;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.CategoryAlreadyExists;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public abstract class CategoryImplementation implements CategoryRepository {
    private CategoryRepository categoryDB;

    @Override
    public Category addCategory(Category categoryNameInput) {
        Category existingCategory = categoryDB.getCategoryByName(categoryNameInput.getCategoryName());
        if (existingCategory == null) {
            Category newCategory = new Category(categoryNameInput.getCategoryName());
            return categoryDB.insert(newCategory);
        } else {
            throw new CategoryAlreadyExists("A category already exists with that name, please try again!");
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return null;
    }

    @Override
    public Category getCategoryById(String categoryId) {
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
