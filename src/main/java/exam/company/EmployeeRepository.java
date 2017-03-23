package exam.company;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by lei on 23/03/2017.
 */
public interface EmployeeRepository extends MongoRepository<Employee, String>, EmpRepositoryCustom {

    List<Employee> findByEmployeeName(String employeeName);

    List<Employee> findByProjects(String project);

    public default String findFirstCommonManager(Employee employee1, Employee employee2){
        List<String> employee1AllManagers = getAllManagers(employee1);
        List<String> employee2AllManagers = getAllManagers(employee2);
        employee1AllManagers.retainAll(employee2AllManagers);
        if(employee1AllManagers.size() > 0){
            return employee1AllManagers.get(0);
        }
        return null;
    }

    public default String findCloseManagerForProject(String project) {
        List<Employee> employeeList = findByProjects(project);
        Map<String, Long> counted = employeeList.stream().map(Employee::getManagerName).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Long maxCount = 0L;
        String managerName = null;
        for (Map.Entry<String, Long> entry : counted.entrySet()){
            if(entry.getValue() > maxCount){
                maxCount = entry.getValue();
                managerName = entry.getKey();
            }
        }
        return managerName;
    }

    default List<String> getAllManagers(Employee employee) {
        List<String> employer1Managers = new ArrayList<>();
        Employee currentEmployee = employee;
        while (currentEmployee.getManagerName() != null) {
            employer1Managers.add(currentEmployee.getManagerName());
            currentEmployee = findByEmployeeName(currentEmployee.getManagerName()).get(0);
        }
        return employer1Managers;
    }

}
