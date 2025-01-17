package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (employee_id, name, job_title, division, salary, ssn) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getEmployeeId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getJobTitle());
            pstmt.setString(4, employee.getDivision());
            pstmt.setDouble(5, employee.getSalary());
            pstmt.setString(6, employee.getSsn());
            pstmt.executeUpdate();
        }
    }

    public List<Employee> searchEmployeeByName(String name) throws SQLException {
        String sql = "SELECT * FROM employee WHERE name LIKE ?";
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("job_title"),
                        rs.getString("division"),
                        rs.getDouble("salary"),
                        rs.getString("ssn")));
            }
        }
        return employees;
    }

    public void updateSalary(double lowerBound, double upperBound, double percentage) throws SQLException {
        String sql = "UPDATE employee SET salary = salary + (salary * ?) WHERE salary >= ? AND salary < ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, percentage / 100);
            pstmt.setDouble(2, lowerBound);
            pstmt.setDouble(3, upperBound);
            pstmt.executeUpdate();
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET name = ?, job_title = ?, division = ?, salary = ?, ssn = ? WHERE employee_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getJobTitle());
            pstmt.setString(3, employee.getDivision());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setString(5, employee.getSsn());
            pstmt.setInt(6, employee.getEmployeeId());
            pstmt.executeUpdate();
        }
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
        }
    }

    public List<String> getTotalPayByDivision() throws SQLException {
        String sql = "SELECT division, SUM(salary) AS total_pay FROM employee GROUP BY division";
        List<String> report = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String division = rs.getString("division");
                double totalPay = rs.getDouble("total_pay");
                report.add("Division: " + division + ", Total Pay: " + totalPay);
            }
        }
        return report;
    }

    public List<String> getTotalPayByJobTitle() throws SQLException {
        String sql = "SELECT job_title, SUM(salary) AS total_pay FROM employee GROUP BY job_title";
        List<String> report = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String jobTitle = rs.getString("job_title");
                double totalPay = rs.getDouble("total_pay");
                report.add("Job Title: " + jobTitle + ", Total Pay: " + totalPay);
            }
        }
        return report;
    }
    
    public Employee searchEmployeeBySSN(String ssn) throws SQLException {
        String sql = "SELECT * FROM employee WHERE ssn = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ssn);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("job_title"),
                        rs.getString("division"),
                        rs.getDouble("salary"),
                        rs.getString("ssn"));
            }
        }
        return null; // Return null if no employee is found
    }

    public Employee searchEmployeeById(int employeeId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("job_title"),
                        rs.getString("division"),
                        rs.getDouble("salary"),
                        rs.getString("ssn"));
            }
        }
        return null; // Return null if no employee is found
    }
    
    public void addSSNColumn() throws SQLException {
        String sql = "ALTER TABLE employee ADD COLUMN IF NOT EXISTS ssn VARCHAR(9)";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
    
    
    
}

