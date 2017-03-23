# newscorp

Assumption: mongodb is running at localhost:27017

Solution: Java 8, Spring boot / Spring data, MongoDB.
Build: Maven or Gradle.

Test cases covered the requirements

Task 1.

1.1 Employee is the model object.

1.2 EmpRepositoryCustom define interface findProjectTeamMates, it can search employee by manager name
    Done by mongoDB query.
    
1.3 EmployeeRepository define default interface findFirstCommonManager
    Assumption here is employee has only one manager and the company structure is not very deep.
    Implementation is get all the managers of both employee in two array. 
    closest manage is at beginning. 
    Arraylist retainAll keep the common managers and the first one is closest common manager
    
1.4 EmployeeRepository define default interface findCloseManagerForProject
    Search employee by project with mongodb query by findByProjects.
    Use java 8 stream api to count manager has most employee work on the project.
    (Alternative solution is use mongodb aggregation query)
    
Please check EmployeeRepositoryTests for scenarios

Task 2.

2.1 Ping object is defined with url and response.

2.2 PingService visitUrl can take a list of urls
    It use restTemplate to send requests to urls and collect responses.
    
2.3 While PingService visit urls, if the url is http://test.com then print out response.
    
2.4 PingRepositoryImpl implement findAttributeWithRegex which take attribute name and regex.
    Search is done by mongodb query

PingServiceTests 
    "before" step clean the collection and call PingService to send requests to defined urls.
    "findPingTest" search ping by 'response' contains 'Set-Cookie'


    

    
    