package com.example.Reimbursement.Request.App.Implementation.EmployeeDAL;

import com.example.Reimbursement.Request.App.Entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
