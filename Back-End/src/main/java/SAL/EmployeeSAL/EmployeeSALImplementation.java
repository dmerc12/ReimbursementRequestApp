package SAL.EmployeeSAL;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import Entities.Requests.Employee.NewEmployeeRequest;
import Utilities.CustomHashing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class EmployeeSALImplementation implements EmployeeSALInterface{
    private final EmployeeDALImplementation employeeDAO;
    public static Logger logger = LogManager.getLogger(EmployeeSALImplementation.class);
    public EmployeeSALImplementation(EmployeeDALImplementation employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    @Override
    public Employee addEmployee(NewEmployeeRequest employee) {
        logger.info("Beginning SAL method add employee with firstName; " + employee.getFirstName() +
                ", lastName: " + employee.getLastName() + ", email: " + employee.getEmail() + ", password: " +
                employee.getPassword(), ", phoneNumber: " + employee.getPhoneNumber() + ", address: " +
                employee.getAddress() + ", confirmationPassword: " + employee.getConfirmationPassword());
        if (employee.getFirstName().equals("")) {
            logger.warn("SAL method add employee, first name left empty");
            throw new GeneralError("The first name field cannot be left empty, please try again!");
        } else if (employee.getFirstName().length() > 36) {
            logger.warn("SAL method add employee, first name too long");
            throw new GeneralError("The first name field cannot exceed 36 characters, please try again!");
        } else if (employee.getLastName().equals("")) {
            logger.warn("SAL method add employee, last name left empty");
            throw new GeneralError("The last name field cannot be left empty, please try again!");
        } else if (employee.getLastName().length() > 36) {
            logger.warn("SAL method add employee, last name too long");
            throw new GeneralError("The last name field cannot exceed 36 characters, please try again!");
        } else if (employee.getEmail().equals("")) {
            logger.warn("SAL method add employee, email left empty");
            throw new GeneralError("The email field cannot be left empty, please try again!");
        } else if (employee.getEmail().length() > 60) {
            logger.warn("SAL method add employee, email too long");
            throw new GeneralError("The email field cannot exceed 60 characters, please try again!");
        } else if (employee.getPassword().equals("")) {
            logger.warn("SAL method add employee, password left empty");
            throw new GeneralError("The password field cannot be left empty, please try again!");
        } else if (employee.getPassword().length() > 60) {
            logger.warn("SAL method add employee, password too long");
            throw new GeneralError("The password field cannot exceed 60 characters, please try again!");
        } else if (employee.getPhoneNumber().equals("")) {
            logger.warn("SAL method add employee, phone number left empty");
            throw new GeneralError("The phone number field cannot be left empty, please try again!");
        } else if (employee.getPhoneNumber().length() > 13) {
            logger.warn("SAL method add employee, phone number too long");
            throw new GeneralError("The phone number field cannot exceed 13 characters, please try again!");
        } else if (!employee.getPhoneNumber().matches("\\d{3}-\\d{3}-\\d{4}")) {
            logger.warn("SAL method add employee, phone number in incorrect format");
            throw new GeneralError("The phone number field cannot vary from the format 'xxx-xxx-xxxx', " +
                    "please try again!");
        } else if (employee.getAddress().equals("")) {
            logger.warn("SAL method add employee, address left empty");
            throw new GeneralError("The address field cannot be left empty, please try again!");
        } else if (employee.getAddress().length() > 60) {
            logger.warn("SAL method add employee, address too long");
            throw new GeneralError("The address field cannot exceed 60 characters, please try again!");
        } else if (employee.getConfirmationPassword().equals("")) {
            logger.warn("SAL method add employee, confirmation password left empty");
            throw new GeneralError("The confirmation password field cannot be left empty, please try again!");
        } else if (employee.getConfirmationPassword().length() > 60) {
            logger.warn("SAL method add employee, confirmation password too long");
            throw new GeneralError("The confirmation password field cannot exceed 60 characters, please try again!");
        } else if (!(employee.getPassword().equals(employee.getConfirmationPassword()))) {
            logger.warn("SAL method add employee, passwords don't match");
            throw new GeneralError("The passwords do not match, please try again!");
        } else {
            Employee existingEmployee = employeeDAO.getEmployeeByEmail(employee.getEmail());
            if (existingEmployee != null) {
                logger.warn("SAL method add employee, employee already exists");
                throw new GeneralError("An employee with this email already exists, please try again!");
            } else {
                String hashedPassword = CustomHashing.hash(employee.getPassword());
                Employee verifiedNewEmployee = new Employee(0, employee.getFirstName(),
                        employee.getLastName(), employee.getEmail(), hashedPassword, employee.getPhoneNumber(),
                        employee.getAddress());
                Employee result = employeeDAO.addEmployee(verifiedNewEmployee);
                String employeeString = String.format("employeeId: %s, firstName: %s, lastName: %s, email: %s, " +
                                "password: %s, phoneNumber: %s, address: %s", result.getEmployeeId(),
                        result.getFirstName(), result.getLastName(), result.getEmail(), result.getPassword(),
                        result.getPhoneNumber(), result.getAddress());
                logger.info("Finishing SAL method add employee with result: " + employeeString);
                return result;
            }
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        logger.info("Beginning SAL method get employee by ID with employee ID: " + employeeId);
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        if (employee == null) {
            logger.warn("SAL method get employee by ID, not found");
            throw new GeneralError("No employee found, please try again!");
        } else {
            String employeeString = String.format("employeeId: %s, firstName: %s, lastName: %s, email: %s, " +
                            "password: %s, phoneNumber: %s, address: %s", employee.getEmployeeId(),
                    employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPassword(),
                    employee.getPhoneNumber(), employee.getAddress());
            logger.info("Finishing SAL method get employee by ID with employee: " + employeeString);
            return employee;
        }
    }

    @Override
    public Employee login(String email, String password) {
        logger.info("Beginning SAL method login with email: " + email + ", and password: " + password);
        if (email.equals("")) {
            logger.warn("SAL method login, email left empty");
            throw new GeneralError("The email field cannot be left empty, please try again!");
        } else if (password.equals("")) {
            logger.warn("SAL method login, password left empty");
            throw new GeneralError("The password field cannot be left empty, please try again!");
        } else {
            Employee employee = employeeDAO.login(email, CustomHashing.hash(password));
            if (employee == null) {
                logger.warn("SAL method login, not existing credentials");
                throw new GeneralError("Either the email or the password is incorrect, please try again!");
            } else {
                String employeeString = String.format("employeeId: %s, firstName: %s, lastName: %s, email: %s, " +
                                "password: %s, phoneNumber: %s, address: %s", employee.getEmployeeId(),
                        employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPassword(),
                        employee.getPhoneNumber(), employee.getAddress());
                logger.info("Finishing SAL method login with employee: " + employeeString);
                return employee;
            }
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        logger.info("Beginning SAL method update employee with employeeId: " + employee.getEmployeeId() +
                ", firstName; " + employee.getFirstName() + ", lastName: " + employee.getLastName() +
                ", email: " + employee.getEmail() + ", password: " + employee.getPassword(),", phoneNumber: " +
                employee.getPhoneNumber() + ", address: " + employee.getAddress());
        if (employee.getFirstName().equals("")) {
            logger.warn("SAL method update employee, first name left empty");
            throw new GeneralError("The first name field cannot be left empty, please try again!");
        } else if (employee.getFirstName().length() > 36) {
            logger.warn("SAL method update employee, first name too long");
            throw new GeneralError("The first name field cannot exceed 36 characters, please try again!");
        } else if (employee.getLastName().equals("")) {
            logger.warn("SAL method update employee, last name left empty");
            throw new GeneralError("The last name field cannot be left empty, please try again!");
        } else if (employee.getLastName().length() > 36) {
            logger.warn("SAL method update employee, last name too long");
            throw new GeneralError("The last name field cannot exceed 36 characters, please try again!");
        } else if (employee.getEmail().equals("")) {
            logger.warn("SAL method update employee, email left empty");
            throw new GeneralError("The email field cannot be left empty, please try again!");
        } else if (employee.getEmail().length() > 60) {
            logger.warn("SAL method update employee, email too long");
            throw new GeneralError("The email field cannot exceed 60 characters, please try again!");
        } else if (employee.getPhoneNumber().equals("")) {
            logger.warn("SAL method update employee, phone number left empty");
            throw new GeneralError("The phone number field cannot be left empty, please try again!");
        } else if (employee.getPhoneNumber().length() > 13) {
            logger.warn("SAL method update employee, phone number too long");
            throw new GeneralError("The phone number field cannot exceed 13 characters, please try again!");
        } else if (employee.getAddress().equals("")) {
            logger.warn("SAL method update employee, address left empty");
            throw new GeneralError("The address field cannot be left empty, please try again!");
        } else if (employee.getAddress().length() > 60) {
            logger.warn("SAL method update employee, address too long");
            throw new GeneralError("The address field cannot exceed 60 characters, please try again!");
        } else if (!employee.getPhoneNumber().matches("\\d{3}-\\d{3}-\\d{4}")) {
            logger.warn("SAL method add employee, phone number in incorrect format");
            throw new GeneralError("The phone number field cannot vary from the format 'xxx-xxx-xxxx', " +
                    "please try again!");
        } else {
            Employee existingInformation = getEmployeeById(employee.getEmployeeId());
            if (employee.getFirstName().equals(existingInformation.getFirstName()) &&
                    employee.getLastName().equals(existingInformation.getLastName()) &&
                    employee.getEmail().equals(existingInformation.getEmail()) &&
                    employee.getPhoneNumber().equals(existingInformation.getPhoneNumber()) &&
                    employee.getAddress().equals(existingInformation.getAddress())) {
                logger.warn("SAL method update employee, nothing changed");
                throw new GeneralError("Nothing has changed, please try again!");
            } else {
                Employee updatedInformation = employeeDAO.updateEmployee(employee);
                String employeeString = String.format("employeeId: %s, firstName: %s, lastName: %s, email: %s, " +
                                "password: %s, phoneNumber: %s, address: %s", updatedInformation.getEmployeeId(),
                        updatedInformation.getFirstName(), updatedInformation.getLastName(),
                        updatedInformation.getEmail(), updatedInformation.getPassword(),
                        updatedInformation.getPhoneNumber(), updatedInformation.getAddress());
                logger.info("Finishing SAL method update employee with resulting employee information: " +
                        employeeString);
                return updatedInformation;
            }
        }
    }

    @Override
    public Employee changePassword(Employee employee, String confirmationPassword) {
        logger.info("Beginning SAL method change password with employee ID: " + employee.getEmployeeId() +
                ", password: " + employee.getPassword());
        if (employee.getPassword().equals("")) {
            logger.warn("SAL method change password, password left empty");
            throw new GeneralError("The password field cannot be left empty, please try again!");
        } else if (employee.getPassword().length() > 60) {
            logger.warn("SAL method change password, password too long");
            throw new GeneralError("The password field cannot exceed 60 characters, please try again!");
        } else if (confirmationPassword.equals("")) {
            logger.warn("SAL method change password, confirmation password left empty");
            throw new GeneralError("The confirmation password field cannot be left empty, please try again!");
        } else if (confirmationPassword.length() > 60) {
            logger.warn("SAL method change password, confirmation password too long");
            throw new GeneralError("The confirmation password field cannot exceed 60 characters, please try again!");
        } else if (!Objects.equals(employee.getPassword(), confirmationPassword)) {
            logger.warn("SAL method change password, passwords don't match");
            throw new GeneralError("The confirmation password field must match the password field, please try again!");
        } else {
            String currentEmployeePassword = getEmployeeById(employee.getEmployeeId()).getPassword();
            if (Objects.equals(CustomHashing.hash(employee.getPassword()), currentEmployeePassword)) {
                logger.warn("SAL method change password, nothing changed");
                throw new GeneralError("Nothing has changed, please try again!");
            } else {
                employee.setPassword(CustomHashing.hash(employee.getPassword()));
                Employee changedEmployee = employeeDAO.changePassword(employee);
                logger.info("Finishing SAL method change password");
                return changedEmployee;
            }
        }
    }


    @Override
    public int deleteEmployee(int employeeId) {
        logger.info("Beginning SAL method delete employee with employee ID: " + employeeId);
        getEmployeeById(employeeId);
        int result = employeeDAO.deleteEmployee(employeeId);
        logger.info("Finishing SAL method delete employee");
        return result;
    }
}
