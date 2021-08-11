package com.mmm.spring.mvc_hibernate_aop;

import com.mmm.spring.mvc_hibernate_aop.dao.EmployeeDAO;
import com.mmm.spring.mvc_hibernate_aop.entity.Employee;
import com.mmm.spring.mvc_hibernate_aop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {

//    //this option used if we have NO service layer
//    @Autowired
//    private EmployeeDAO employeeDAO;

    //this option used if we have service layer
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String showAllEmployees (Model model) {

//        //this option used if we have NO service layer
//        List <Employee> allEmployees = employeeDAO.getAllEmployees();

          //this option used if we have NO service layer
        List <Employee> allEmployees = employeeService.getAllEmployees();


        model.addAttribute("allEmps", allEmployees);

        return "all-employees";
    }

    @RequestMapping ("/addNewEmployee")
    public String addNewEmployee (Model model) {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);


        return "employee-info";
    }

    @RequestMapping ("/saveEmployee")
    public String saveEmployee (@ModelAttribute ("employee") Employee employee) {

        employeeService.saveEmployee(employee);

        return "redirect:/";
    }
    @RequestMapping("/updateInfo")
    public String updateEmployee (@RequestParam ("empId") int empId, Model model){

        Employee employee = employeeService.getEmployee(empId);

        model.addAttribute("employee", employee);

        return "employee-info";

    }
}
