package webSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webSpring.backend.EmpService;
import webSpring.model.Employee;

@Controller
@RequestMapping( "/employee" )
public class CertainEmployeeController {
    private EmpService empService;

    public CertainEmployeeController(EmpService empService) {
        this.empService = empService;
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
    public String saveEMployee(@ModelAttribute Employee ee, RedirectAttributes redirectAttr){
        empService.updateEmployee(ee);
        redirectAttr.addFlashAttribute("message", "Employee has been modified: " + ee.getName());
        return "redirect:/";
    }

}
