package SAL.EmployeeSAL;

import Entities.Data.Employee;
import Entities.Requests.Employee.NewEmployeeRequest;

public interface EmployeeSALInterface {
    Employee addEmployee(NewEmployeeRequest employee);
    Employee getEmployeeById(int employeeId);
    Employee login(String email, String password);
    Employee updateEmployee(Employee employee);
    Employee changePassword(Employee employee, String confirmationPassword);
    int deleteEmployee(int employeeId);
}
