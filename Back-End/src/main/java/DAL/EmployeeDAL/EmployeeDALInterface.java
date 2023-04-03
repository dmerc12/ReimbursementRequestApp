package DAL.EmployeeDAL;

import Entities.Data.Employee;

public interface EmployeeDALInterface {
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(int employeeId);
    Employee getEmployeeByEmail(String email);
    Employee login(String email, String password);
    Employee updateEmployee(Employee employee);
    int deleteEmployee(int employeeId);
}
