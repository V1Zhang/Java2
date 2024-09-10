import java.time.LocalDateTime;
import java.util.*;

class Employee {
    private String name;
    private int age;
    private int id; // Unique ID for each employee
    private LocalDateTime loginTime;

    public Employee(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id; // Set the unique ID
        this.loginTime = LocalDateTime.now(); // Set current time
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id; // Add getter for ID
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", ID: " + id + ", Login Time: " + loginTime;
    }
}

class EmployeeManager {
    private List<Employee> employees;
    private int nextId; // To assign unique IDs

    public EmployeeManager() {
        this.employees = new ArrayList<>();
        this.nextId = 1; // Start IDs from 1
    }

    public Integer findEmployeeId(String name) {
        for (Employee emp : employees) {
            if (emp.getName().equals(name)) {
                return emp.getId(); // Return the ID if found
            }
        }
        return null; // Return null if not found
    }

    public void addEmployee(String name, int age) {
        Employee newEmployee = new Employee(name, age, nextId++);
        employees.add(newEmployee);
    }

    public void deleteEmployee(int id) {
        employees.removeIf(emp -> emp.getId() == id);
    }

    public void printEmployees() {
        Collections.sort(employees, Comparator.comparing(Employee::getName));
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeManager manager = new EmployeeManager();
        System.out.println("Welcome to Employee Management System!");

        String mode;
        while (true) {
            System.out.print("Enter command (add, delete, find, all, finish): ");
            mode = sc.nextLine();

            if (mode.equals("finish")) {
                break; // Exit the loop
            }

            switch (mode) {
                case "add":
                    System.out.println("Enter employee info (name age), type 'over' to finish:");
                    String employee = sc.nextLine();
                    while(!employee.equals("over")){
                        String[] parts = employee.split(" ");
                        System.out.println(parts[0]+parts[1]);
                        String name = parts[0];
                        int age = Integer.parseInt(parts[1]);
                        manager.addEmployee(name, age);
                        employee = sc.nextLine();
                    }
                    break;

                case "delete":
                    System.out.print("Enter employee ID to delete: ");
                    try {
                        int id = sc.nextInt();
                        System.out.println(id);
                        sc.nextInt();// Consume the newline
                        manager.deleteEmployee(id);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Please enter a valid integer ID.");
                        sc.nextLine();
                    }
                    break;

                case "find":
                    System.out.print("Enter employee name to find: ");
                    String name = sc.nextLine();
                    Integer foundId = manager.findEmployeeId(name);
                    if (foundId != null) {
                        System.out.println("Employee ID: " + foundId);
                    } else {
                        System.out.println("This employee does not exist.");
                    }
                    break;

                case "all":
                    System.out.println("Employees List:");
                    manager.printEmployees();
                    break;

                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }
        sc.close(); // Close the scanner
    }
}