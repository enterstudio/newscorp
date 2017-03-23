package exam.company;
/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTests {

    private final String projectA = "ProjectA";
    private final String projectB = "ProjectB";
    private final String projectC = "ProjectC";

    @Autowired
    EmployeeRepository repository;

    Employee john = new Employee("John", "Lisa", projectA, projectB);
    Employee jack = new Employee("Jack", "Lisa", projectA);
    Employee james = new Employee("James", "Leonard", projectA, projectC);
    Employee lucy = new Employee("Lucy", "Leonard", projectB, projectC);
    Employee sam = new Employee("Sam", "Leonard", projectB);
    Employee lisa = new Employee("Lisa", "Simon");
    Employee leonard = new Employee("Leonard", "Simon");
    Employee simon = new Employee("Simon", null);

    @Before
    public void setUp() {

        repository.deleteAll();

        // create employees
        repository.save(john);
        repository.save(jack);
        repository.save(james);
        repository.save(lucy);
        repository.save(sam);
        repository.save(lisa);
        repository.save(leonard);
        repository.save(simon);
    }

    @Test
    public void findProjectTeamMates() {

        List<Employee> result = repository.findProjectTeamMates(james);

        assertThat(result).hasSize(3).extracting("employeeName").contains("John", "Jack", "Lucy");
    }

    @Test
    public void findFirstCommonManager() {

        String result1 = repository.findFirstCommonManager(john, jack);

        assertEquals("Lisa", result1);

        String result2 = repository.findFirstCommonManager(john, sam);

        assertEquals("Simon", result2);
    }

    @Test
    public void guessCloseManagerForProject(){

        String result = repository.findCloseManagerForProject(projectA);

        assertEquals("Lisa", result);
    }
}