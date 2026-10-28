Q1. How would you handle a StaleElementReferenceException in a dynamic web application where the DOM refreshes frequently? Explain the difference between re-locating the element, using explicit waits, and implementing a retry mechanism.

Ans -  Relocating element, using explicit wait and try again and again and for a max time are the 3 ways to handle a StaleElementReferenceException. 
Relocating element is locating with updated locators
explicit wait is waiting for a fixed period of time before again trying
and in the retry mechanism we use a loop to try again and again locating the elemnt until it is located and the max time is the condition 

Q2. Suppose you have multiple browser windows and nested frames, and an element is inside an iframe that loads dynamically. How would you switch between windows and frames, wait for the element, and return to the parent frame without making the test flaky?

Ans- For switch frame 
            "WebElement iframe = driver.findElement(By.id("iframe1"));
            driver.switchTo().frame(iframe);"
            and to return back to the parent we use "driver.switchTo().defaultContent();" // all the way up
            "driver.switchTo().parentFrame();" // 1 up like parent of the i frame

    for windows
            String windowId = driver.getWindowHandle();

            if now you click something and go to a new tab while returning 
            
            driver.switchTo().window("ID_String_of_target")

Q3. How would you validate a complex nested JSON response containing dynamic values, optional fields, arrays, and multiple objects using REST Assured? Explain how you would validate the response schema and handle data that changes on every request.

Ans- dyanmic values should be validated with datatypes, patterns , startswith, notnull
    optional fields is present
    arrays: check the size of the array,  chechk property of each field data types pattern and required or not
    multiple objects we need to see the size and go inside and check the each and every field

Q4. How would you automate an API workflow involving OAuth 2.0 authentication, token expiration, dependent API calls, and retry handling for HTTP 429 and 5xx responses? How would you design reusable request specifications and avoid unnecessary token generation?

Ans- The token manager caches the access token and expiry time, reuses valid tokens, and obtains them when necessary. 
Dependent API calls extract IDs from earlier responses and pass them into subsequent requests. 
For HTTP 429 and transient 5xx responses,Retry-After with exponential time . For authentication failures, I reacquire the token and retry.


Q5. Given a list of custom Employee objects, how would you remove duplicates based on employee ID while preserving insertion order, identify employees with duplicate IDs, and explain how equals() and hashCode() affect the solution? Compare using LinkedHashSet, HashMap, and Java Streams.

Ans - ```java
class Employee{
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id+" "+name;
    }

    @Override
    public boolean equals(Object obj) {
        Employee other = (Employee)obj;
        return this.id == other.id;

    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

```

```java
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(101, "A"),
                new Employee(104, "B"),
                new Employee(101, "C"),
                new Employee(103, "D"),
                new Employee(102, "E"),
                new Employee(102, "F")
        );

        System.out.println("Stream");
        //Java Streams
        Set<Integer> idLooked = new HashSet<>();

        List<Employee> uniqueEmployees = employees.stream()
                .filter(e -> idLooked.add(e.getId()))
                .collect(Collectors.toList());

        uniqueEmployees.forEach(System.out::println);

        System.out.println("HashMap");
        //HashMap
        HashMap<Integer, Employee> uniqueEmployeesById = new HashMap<>();
        List<Integer> duplicateId1 = new ArrayList<>();

        for(Employee e: employees ){
            uniqueEmployeesById.put(e.getId(), e);
        }

        uniqueEmployeesById.forEach((k,e)->System.out.println(e));


        //LinkedHashset
        System.out.println("LinkedHashSet");

        LinkedHashSet<Employee> linkedHashSet = new LinkedHashSet<>(employees);
        Set<Integer> duplicateId2 = new HashSet<>();


        linkedHashSet.forEach(System.out::println);
        System.out.println(duplicateId2);


    }
}

 
```