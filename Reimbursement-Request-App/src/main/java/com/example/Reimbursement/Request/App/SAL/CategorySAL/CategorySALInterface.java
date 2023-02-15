package com.example.Reimbursement.Request.App.SAL.CategorySAL;

import com.example.Reimbursement.Request.App.Entities.Category;

import java.util.List;

public interface CategorySALInterface {
    Category serviceAddCategory(Category category);

    List<Category> serviceGetAllCategories();
    Category serviceGetCategory(int categoryId);
    Category serviceUpdateCategory(Category category);
    boolean serviceDeleteCategory(int categoryId);
}
