package exam.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;

import java.util.List;

/**
 * Created by lei on 23/03/2017.
 */
public class EmployeeRepositoryImpl implements EmpRepositoryCustom {

    private MongoOperations mongoOperations;

    @Autowired
    public EmployeeRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<Employee> findProjectTeamMates(Employee employee) {
        BasicQuery query = new BasicQuery(String.format("{ employeeName : {$ne : '%s'},  projects : {$in : [%s]} }", employee.getEmployeeName(), employee.getProjectNames()));
        return mongoOperations.find(query, Employee.class);
    }

}
