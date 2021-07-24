package webSpring.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webSpring.backend.EmpService;
import webSpring.model.Employee;

import java.util.Locale;

@Controller
@RequestMapping( "/employee" )
public class CertainEmployeeController {
    private EmpService empService;
    private MessageSource messageSource;

    public CertainEmployeeController(EmpService empService, MessageSource messageSource) {
        this.empService = empService;
        this.messageSource = messageSource;
    }

    @ModelAttribute
    public Employee returnEmployeeById( @PathVariable("id") long id ){
        return empService.findEmpById(id);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public ModelAndView findEmpById(){
        return new ModelAndView("employee");
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.POST)
    public String saveEMployee(@ModelAttribute Employee ee, RedirectAttributes redirectAttr, Locale locale){
        empService.updateEmployee(ee);
        String message = messageSource.getMessage("employee.modified", new Object[]{ee.getName()}, locale);
        redirectAttr.addFlashAttribute("message", message);
        return "redirect:/";
    }

}
