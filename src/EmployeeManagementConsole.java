package src;

import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeManagementConsole {
    private final EmployeeDAO employeeDAO;
    private final Scanner scanner;

    public EmployeeManagementConsole() {
        employeeDAO = new EmployeeDAO();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee by Name");
            System.out.println("3. Search Employee by SSN");
            System.out.println("4. Search Employee by ID");
            System.out.println("5. Update Employee Details");
            System.out.println("6. Delete Employee");
            System.out.println("7. Update Salaries in Range");
            System.out.println("8. View Total Pay by Division");
            System.out.println("9. View Total Pay by Job Title");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> searchEmployeeByName();
                    case 3 -> searchEmployeeBySSN();
                    case 4 -> searchEmployeeById();
                    case 5 -> updateEmployeeDetails();
                    case 6 -> deleteEmployee();
                    case 7 -> updateSalariesInRange();
                    case 8 -> viewTotalPayByDivision();
                    case 9 -> viewTotalPayByJobTitle();
                    case 10 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private void addEmployee() throws SQLException {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();

        System.out.print("Enter Division: ");
        String division = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        System.out.print("Enter SSN (no dashes): ");
        String ssn = scanner.next();

        Employee employee = new Employee(id, name, jobTitle, division, salary, ssn);
        employeeDAO.addEmployee(employee);
        System.out.println("Employee added successfully!\n");
    }

    private void searchEmployeeByName() throws SQLException {
        System.out.print("Enter Name to Search: ");
        String name = scanner.nextLine();
        var employees = employeeDAO.searchEmployeeByName(name);
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            employees.forEach(System.out::println);
        }
    }

    private void searchEmployeeBySSN() throws SQLException {
        System.out.print("Enter SSN to Search: ");
        String ssn = scanner.nextLine();
        var employee = employeeDAO.searchEmployeeBySSN(ssn);
        if (employee == null) {
            System.out.println("No employee found.");
        } else {
            System.out.println(employee);
        }
    }

    private void searchEmployeeById() throws SQLException {
        System.out.print("Enter Employee ID to Search: ");
        int id = scanner.nextInt();
        var employee = employeeDAO.searchEmployeeById(id);
        if (employee == null) {
            System.out.println("No employee found.");
        } else {
            System.out.println(employee);
        }
    }

    private void updateEmployeeDetails() throws SQLException {
        System.out.print("Enter Employee ID to Update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Updated Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Updated Job Title: ");
        String jobTitle = scanner.nextLine();

        System.out.print("Enter Updated Division: ");
        String division = scanner.nextLine();

        System.out.print("Enter Updated Salary: ");
        double salary = scanner.nextDouble();

        System.out.print("Enter Updated SSN (no dashes): ");
        String ssn = scanner.next();

        Employee updatedEmployee = new Employee(id, name, jobTitle, division, salary, ssn);
        employeeDAO.updateEmployee(updatedEmployee);
        System.out.println("Employee details updated successfully!\n");
    }

    private void deleteEmployee() throws SQLException {
        System.out.print("Enter Employee ID to Delete: ");
        int id = scanner.nextInt();
        employeeDAO.deleteEmployee(id);
        System.out.println("Employee deleted successfully!\n");
    }

    private void updateSalariesInRange() throws SQLException {
        System.out.print("Enter lower salary bound: ");
        double lower = scanner.nextDouble();

        System.out.print("Enter upper salary bound: ");
        double upper = scanner.nextDouble();

        System.out.print("Enter percentage increase: ");
        double percentage = scanner.nextDouble();

        employeeDAO.updateSalary(lower, upper, percentage);
        System.out.println("Salaries updated successfully!\n");
    }

    private void viewTotalPayByDivision() throws SQLException {
        var reports = employeeDAO.getTotalPayByDivision();
        if (reports.isEmpty()) {
            System.out.println("No data found.");
        } else {
            reports.forEach(System.out::println);
        }
    }

    private void viewTotalPayByJobTitle() throws SQLException {
        var reports = employeeDAO.getTotalPayByJobTitle();
        if (reports.isEmpty()) {
            System.out.println("No data found.");
        } else {
            reports.forEach(System.out::println);
        }
    }
}
