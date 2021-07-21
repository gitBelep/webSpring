package webSpring.backend;

import org.springframework.stereotype.Service;
import webSpring.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmpService {
    private AtomicInteger id = new AtomicInteger();
    private List<webSpring.Employee> employeeList = Collections.synchronizedList(
            new ArrayList<>( List.of(
                    new Employee(id.incrementAndGet(), "Aba"),
                    new Employee(id.incrementAndGet(), "Samu")
            )));

    public List<webSpring.Employee> listEmployees(){
        return new ArrayList<>( employeeList );
    }

    public void saveEmployee(String name){
        employeeList.add(new Employee(id.incrementAndGet(), name));
    }

    public Employee findEmpById(long id){
        return employeeList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such id" + id));
    }

    public void updateEmployee(Employee e){
        Employee employeeToModify = findEmpById(e.getId());
        employeeToModify.setName(e.getName());
    }

    public void reset(){
        id.set( 0 );
        employeeList.clear();
    }

}
