package webSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webSpring.backend.EmpService;
import webSpring.model.Employee;

@Controller
public class EmpController {
    private EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @ModelAttribute
    public Employee provideEmployee(){
        return new Employee();
    }

    @RequestMapping( value = "/", method = RequestMethod.POST )
    public String saveEmployee(@ModelAttribute Employee employee, RedirectAttributes redirectAtt){
        empService.saveEmployee( employee.getName() );
        redirectAtt.addFlashAttribute("message",
                "Employee has been saved: " + employee.getName());
        return "redirect:/";
    }

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public ModelAndView listEmployees(){
        return new ModelAndView("index", "employeeList",
                empService.listEmployees());
    }

}
