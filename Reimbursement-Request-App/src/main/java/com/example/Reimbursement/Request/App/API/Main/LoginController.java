package com.example.Reimbursement.Request.App.API.Main;

import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.DAL.SessionDAL.SessionDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.Entities.Session;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;
import com.example.Reimbursement.Request.App.SAL.SessionSAL.SessionSALImplementation;
import com.example.Reimbursement.Request.App.Entities.Employee;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class LoginController {

    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);

    @GetMapping(value = {"/", "/login"})
    public String renderLoginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password,
                        RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        try {
            Employee employee = employeeSAO.login(email, password);
            LocalDateTime newSessionExpiration = LocalDateTime.now().plusMinutes(15);
            Session newSession = new Session(0, employee.getEmployeeId(), newSessionExpiration);
            newSession = sessionSAO.addSession(newSession);
            session.setAttribute("sessionId", newSession.getSessionId());
            return "redirect:/home";
        } catch (GeneralError error) {
            model.addAttribute("errorMessage", error.getMessage());
            return "login";
        }
    }
}
