package com.example.Reimbursement.Request.App.API;

import com.example.Reimbursement.Request.App.DAL.CategoryDAL.CategoryImplementation;
import com.example.Reimbursement.Request.App.Entities.Category;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class RequestController {
    private final CategoryImplementation categoryImplementation;

    @PostMapping("/add/category")
    public Category addCategory(Category category) {
        return categoryImplementation.addCategory(category);
    }
}
