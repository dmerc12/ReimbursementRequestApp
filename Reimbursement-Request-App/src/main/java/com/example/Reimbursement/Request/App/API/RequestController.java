package com.example.Reimbursement.Request.App.API;

import com.example.Reimbursement.Request.App.Entities.Category;
import com.example.Reimbursement.Request.App.SAL.CategorySAL.CategorySALImplementation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class RequestController {
    private final CategorySALImplementation categorySALImplementation;

    @PostMapping("/add/category")
    public Category addCategory(Category category) {
        return categorySALImplementation.serviceAddCategory(category);
    }
}
