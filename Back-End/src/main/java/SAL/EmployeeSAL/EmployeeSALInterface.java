package SAL.EmployeeSAL;

import Entities.Data.Employee;

public interface EmployeeSALInterface {
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(int employeeId);
    Employee login(String email, String password);
    Employee updateEmployee(Employee employee);
    int deleteEmployee(int employeeId);
}
