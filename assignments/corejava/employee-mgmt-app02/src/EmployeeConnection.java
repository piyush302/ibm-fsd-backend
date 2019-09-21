import java.sql.Statement;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.RowSet;


public class EmployeeConnection{
	static Connection conn = null;
	public static Connection startDatabase() throws SQLException {
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/jdbctraining";

		// Database credentials
		String USER = "training";
	    String PASS = "training";
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true); // enable transaction
		Statement pstmt = null;
		ResultSet rs =null;
		String insertQueryForPrepareStmt = "SELECT * FROM employee";
		pstmt =conn.createStatement();
		rs = pstmt.executeQuery(insertQueryForPrepareStmt);

		System.out.println("Connection estabilished: " + conn);
		return conn;
	}
	
	
	public static void addEmployee(Employee e) throws SQLException {
		PreparedStatement pstmt = null;
		String insertQueryForPrepareStmt = "INSERT INTO employee (name, age, designation, department, country) VALUES (?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(insertQueryForPrepareStmt);
		pstmt.setString(1, e.getName());
		pstmt.setInt(2, e.getAge());
		pstmt.setString(3, e.getDesgn());
		pstmt.setString(4, e.getDept());
		pstmt.setString(5, e.getCountry());
		int insertCount = pstmt.executeUpdate();
		pstmt.close();
		
	}
	
	public static void viewEmployee(int id1) throws SQLException {
		Statement pstmt = null;
		ResultSet rs =null;
		String insertQueryForPrepareStmt = "SELECT * FROM employee";
		pstmt =conn.createStatement();		
		rs = pstmt.executeQuery(insertQueryForPrepareStmt);
		System.out.format("\t%s \t%s \t%s \t%s \t%s \t%s\n", "Id", "Age", "Name", "Designation", "Department",
				"Country");
		while (rs.next()) {
			int id = rs.getInt("id");
			if(id==id1) {
				int age = rs.getInt("age");
				String name = rs.getString("name");
				String designation = rs.getString("designation");
				String department = rs.getString("department");
				String country = rs.getString("country");
				System.out.format("\t%d \t%d \t%s \t%s \t%s \t%s\n", id, age, name, designation, department, country);
				break;
			}
		}
	}
	
	public static void viewEveryEmp() throws SQLException {
		Statement pstmt = null;
		ResultSet rs =null;
		String select = "SELECT * FROM employee";
		pstmt =conn.createStatement();
		
		rs = pstmt.executeQuery(select);	
		System.out.format("\t%s \t%s \t%s \t%s \t%s \t%s\n", "Id", "Age", "Name", "Designation", "Department",
				"Country");
		while (rs.next()) {
			int id = rs.getInt("id");
			int age = rs.getInt("age");
			String name = rs.getString("name");
			String designation = rs.getString("designation");
			String department = rs.getString("department");
			String country = rs.getString("country");

			// Display values
			System.out.format("\t%d \t%d \t%s \t%s \t\t%s \t\t%s\n", id, age, name, designation, department, country);
		}
	}
	
	public static void deleteEmp(int id) throws SQLException {
		String deleteQuery = "DELETE FROM employee WHERE id = ?";
		PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public static int findId(int id1) throws SQLException {
		int val=0;
		
		Statement pstmt = null;
		ResultSet rs =null;
		String insertQueryForPrepareStmt = "SELECT * FROM employee";
		pstmt =conn.createStatement();
		
		rs = pstmt.executeQuery(insertQueryForPrepareStmt);
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			if(id1==id) {
				val=0;
				break;
			}
			else {
				val=1;;
			}
			
		}
		return val;
	}
	
	public static void expEmp() throws SQLException, IOException {
		Statement pstmt = null;
		ResultSet rs =null;
		String select = "SELECT * FROM employee";
		pstmt =conn.createStatement();
		FileWriter writer = new FileWriter("C:\\Users\\PiyushDarshan\\Documents\\output2.txt");
		rs = pstmt.executeQuery(select);	
		while (rs.next()) {
			int id = rs.getInt("id");
			int age = rs.getInt("age");
			String name = rs.getString("name");
			String designation = rs.getString("designation");
			String department = rs.getString("department");
			String country = rs.getString("country");
			Employee e = new Employee(id,name, age, department, designation, country);
			writer.write(e + "\n");
		}
		writer.close();
	}
	
	public static void updateEmp(int id) throws SQLException {
		 Scanner sc = new Scanner(System.in);
			System.out.print("Enter modified name-");
			String name = sc.next();
			System.out.print("Enter modified age-");
			int age = sc.nextInt();
			System.out.print("Enter modified dept-");
			String dept = sc.next();
			System.out.print("Enter modified desgn-");
			String desgn = sc.next();
			System.out.print("Enter modified country-");
			String country = sc.next();
			
			String updateQuery = "UPDATE employee SET designation = ?,name = ?,age = ?,country =?,department = ? WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, desgn);
			pstmt.setString(2, name);
			pstmt.setInt(6, id);
			pstmt.setInt(3, age);
			pstmt.setString(4, country);
			pstmt.setString(5, dept);
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("The required employee details have been updated");
	}
}