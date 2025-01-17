package src;


public class Employee {
    private int employeeId;
    private String name;
    private String jobTitle;
    private String division;
    private double salary;
    private String ssn;

    public Employee(int employeeId, String name, String jobTitle, String division, double salary, String ssn) {
        this.employeeId = employeeId;
        this.name = name;
        this.jobTitle = jobTitle;
        this.division = division;
        this.salary = salary;
        this.ssn = ssn;
    }

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId +
               "\nName: " + name +
               "\nJob Title: " + jobTitle +
               "\nDivision: " + division +
               "\nSalary: $" + salary +
               "\nSSN: " + ssn;
    }
}
