package exam.company;

import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;


public class Employee {

    @Id
    private String id;

    private String employeeName;
    private String managerName;
    private List<String> projects;

    public Employee() {}

    public Employee(String employeeName, String managerName, String ... projects) {
        this.employeeName = employeeName;
        this.managerName = managerName;
        this.projects = Arrays.asList(projects);
    }

    @Override
    public String toString() {
        return String.format(
                "Employee[id=%s, employeeName='%s', managerName='%s', projects='%s']",
                id, employeeName, managerName,
                projects.stream().collect(joining(") (", "(", ")")));
    }

    public String getId() {
        return id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getManagerName() {
        return managerName;
    }

    public List<String> getProjects() {
        return projects;
    }

    public String getProjectNames(){
        return projects.stream().collect(joining("','", "'", "'"));
    }
}

