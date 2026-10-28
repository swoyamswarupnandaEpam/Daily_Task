Q1. How would you handle a StaleElementReferenceException in a dynamic web application where the DOM refreshes frequently? Explain the difference between re-locating the element, using explicit waits, and implementing a retry mechanism.
 
Solution:
 
staleElementReferenceException  occur when we try to interact with an element that is no longer valid in the current dom.
it can  be handled by 3 ways:
1)relocating the element
2)by using Explicit wait
3)we can make use of retryanalyzer
 
if the element can be found again we can make use "relocating the element", and if the element will appear if we wait for some specific condtion we can make use of "explicit wait ", and if a test fail because of staleElementReferenceException we can retry a failed test using "retryanalyzer " and try to interact with element again



Q2. Suppose you have multiple browser windows and nested frames, and an element is inside an iframe that loads dynamically. How would you switch between windows and frames, wait for the element, and return to the parent frame without making the test flaky?


Solution:

i  would use getWindowHandles() to identify and switch to the required browser window, then use explicit waits  and ExpectedConditions.frameToBeAvailableAndSwitchToIt() to wait for the dynamically loaded iframe and  switch into it.and i would use driver.switchTo().parentFrame();

Q3. How would you validate a complex nested JSON response containing dynamic values, optional fields, arrays, and multiple objects using REST Assured? Explain how you would validate the response schema and handle data that changes on every request.

Solution:

i would use rest assured to validate the status code, content type, nested JSON fields, arrays, and business rules, use Json Schema validation to verify the response structure and data types, handle optional fields according to the api contract, and validate dynamic values by checking their format or comparing them with data from previous api responses instead of hardcoding expected values


Q4. How would you automate an API workflow involving OAuth 2.0 authentication, token expiration, dependent API calls, and retry handling for HTTP 429 and 5xx responses? How would you design reusable request specifications and avoid unnecessary token generation?

Solution:

i would generate an OAuth2.0 access token and reuse it for multiple api calls until it expires, get a new token when needed, and pass the token between dependent api requests, i would create reusable request specification to avoid repeating common configuration  and retry HTTP 429 and 5xx errors a limited number of times  to handle temporary failure and avoid generating unnecessary tokens


Q5. Given a list of custom Employee objects, how would you remove duplicates based on employee ID while preserving insertion order, identify employees with duplicate IDs, and explain how equals() and hashCode() affect the solution? Compare using LinkedHashSet, HashMap, and Java Streams.

```java
Solution :
 
using linkedhashset:
 
 
Plain Text
package com.banking;
import java.util.*;
class Employee {
    private int id;
    private String name;
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return id + " - " + name;
    }
}
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(101, "Sufiyan"),
                new Employee(102, "Suraj"),
                new Employee(101, "Tapo"),
                new Employee(103, "Tej"),
                new Employee(102, "Swayum ")
        );

        Set<Integer> ids = new LinkedHashSet<>();
        List<Employee> uniqueEmployees = new ArrayList<>();
        for (Employee emp : employees) {
            if (ids.add(emp.getId())) {
                uniqueEmployees.add(emp);
            }
        }
        System.out.println("unique employees:");
        uniqueEmployees.forEach(System.out::println);
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new LinkedHashSet<>();
        for (Employee emp : employees) {
            if (!seen.add(emp.getId())) {
                duplicates.add(emp.getId());
            }
        }
        System.out.println("Duplicate Employee ids:");
        duplicates.forEach(System.out::println);
    }
}

 
 
 
using Linkedhashmap:
 
 
Plain Text
package com.banking;

import java.util.*;
class Employee {
    private int id;
    private String name;
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return id + " - " + name;
    }
}
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(101, "Sufiyan"),
                new Employee(102, "Suraj"),
                new Employee(101, "Tapo"),
                new Employee(103, "Tej"),
                new Employee(102, "Swayum")
        );

        Map<Integer, Employee> employeeMap = new LinkedHashMap<>();
        for (Employee emp : employees) {
            employeeMap.putIfAbsent(emp.getId(), emp);
        }
        System.out.println("Unique Employees:");
        employeeMap.values().forEach(System.out::println);
        Map<Integer, Integer> frequencymap = new HashMap<>();
        for (Employee emp : employees) {
            frequencymap.put(
                    emp.getId(),
                    frequencymap.getOrDefault(emp.getId(), 0) + 1
            );
        }
        System.out.println("Duplicate Employee ids:");
        frequencymap.forEach((id, count) -> {
            if (count > 1) {
                System.out.println(id);
            }
        });
    }
}
 
 
 
using stream:
 
 
Plain Text
package com.banking;
import java.util.*;
import java.util.stream.*;
class Employee {
    private int id;
    private String name;
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return id + " - " + name;
    }
}
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(101, "Sufiyan"),
                new Employee(102, "Suraj"),
                new Employee(101, "Tapo"),
                new Employee(103, "Tej"),
                new Employee(102, "Swayum")
        );

        System.out.println("unique employees:");
        employees.stream()
                .collect(Collectors.toMap(
                        emp -> emp.getId(),
                        emp -> emp,
                        (a, b) -> a,
                        LinkedHashMap::new
                ))
                .values()
                .forEach(System.out::println);

        System.out.println("Duplicate Employee iids:");
        employees.stream()
                .collect(Collectors.groupingBy(
                        emp -> emp.getId(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry ->
                        System.out.println(
                                entry.getKey() + " -> " + entry.getValue()
                        )
                );
    }
}
```