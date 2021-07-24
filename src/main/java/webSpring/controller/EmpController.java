package webSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webSpring.backend.EmpService;
import webSpring.model.Employee;

import java.util.Locale;

@Controller
public class EmpController {
    private EmpService empService;
    private MessageSource messageSource;

    public EmpController(EmpService empService, MessageSource messageSource) {
        this.empService = empService;
        this.messageSource = messageSource;
    }

    @ModelAttribute
    public Employee provideEmployee(){
        return new Employee();
    }

    @RequestMapping( value = "/", method = RequestMethod.POST )
    public String saveEmployee( @ModelAttribute Employee employee,
                               RedirectAttributes redirectAtt, Locale locale){
        empService.saveEmployee( employee.getName() );
        String message = messageSource.getMessage("employee.saved",
                new Object[]{employee.getName()}, locale);
        redirectAtt.addFlashAttribute("message", message);
//old:  redirectAtt.addFlashAttribute("message", "Employee has been saved: " + employee.getName());
        return "redirect:/";
    }

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public ModelAndView listEmployees(){
        return new ModelAndView("index", "employeeList",
                empService.listEmployees());
    }

}
