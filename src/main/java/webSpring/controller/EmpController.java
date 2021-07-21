package webSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import webSpring.Employee;
import webSpring.backend.EmpService;

import java.util.List;

@Controller
public class EmpController {
    private EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

//    version: 0
//    @RequestMapping("/")
//    public ModelAndView listEmployees() {
//        return new ModelAndView("index", "message", "Hello Thymeleaf!");
//    }

//    version: 1
//    @RequestMapping( "/" )
//    public ModelAndView listEmployees(){
//        return new ModelAndView("index", "employeeList",
//                List.of( new Employee("A"),
//                         new Employee("B")   ));
//        }

    @RequestMapping( "/" )
    public ModelAndView listEmployees(){
        return new ModelAndView("index", "employeeList",
                empService.listEmployees());
    }

}
