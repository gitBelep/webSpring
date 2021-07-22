package webSpring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;
import webSpring.backend.EmpService;
import webSpring.controller.EmpController;
import webSpring.model.Employee;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeTest {
        @Mock
        private EmpService eService;

        @InjectMocks
        private EmpController eController;

        @Test
        void tListEmp(){
            //return value of mocked class
            when(eService.listEmployees()).thenReturn(List.of(new Employee("Joe")));

            ModelAndView modelAndView = eController.listEmployees();

            assertEquals("index", modelAndView.getViewName());

            assertEquals(List.of("Joe"),
                    ( (List<Employee>) modelAndView.getModel().get("employeeList") )
                            .stream()
                            .map(Employee::getName)
                            .collect(Collectors.toList()));
        }

}
