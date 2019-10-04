package com.employee.service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.employee.configration.EmployeeBeanConfiguration;
import com.employee.database.EmployeeDb;
import com.employee.model.Employee;

public class EmployeeService {
	
	AbstractApplicationContext context = new AnnotationConfigApplicationContext(EmployeeBeanConfiguration.class);
	public  EmployeeDb empdb = (EmployeeDb)context.getBean("empDao");
	
	ArrayList<Employee> employeeList = new ArrayList<Employee>();
	//EmployeeDb db = new EmployeeDb();
	java.util.Scanner sc= new java.util.Scanner(System.in);

	public void add(Employee employee) throws SQLException {
		empdb.addEmployee(employee);
				
	}

	public void viewAll() throws SQLException {
		empdb.displayEmployee();
		
	}

	public void update(int id) throws SQLException {
		empdb.updateEmployee(id);
		
	}

	public void delete(int id) throws SQLException {
		
		empdb.deteleEmployee(id);
		
		
	}

	public void view(int id) throws SQLException {
		
			empdb.viewEmployee(id);
	}

	public void imported() throws SQLException {
		// TODO Auto-generated method stub
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"C:\\Users\\Himanshu Raj\\Desktop\\EmployeeInformation.txt"));
			String line = reader.readLine();
			while (line != null) {
				//System.out.println(line);
				String []tokens = line.split(",");
				Employee emp = new Employee(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3]);
				empdb.addEmployee(emp);
				line = reader.readLine();
			}
			reader.close();
			System.out.println("File imported successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exported() {
		 try{    
	           FileWriter fw=new FileWriter("details.txt"); 
	           ArrayList<Employee> employeeList= new ArrayList<Employee>();
	           employeeList = (ArrayList<Employee>) empdb.exportEmployees();
	           for(Employee e : employeeList)
	           {
	        	   fw.write(e.getId()+","+e.getName()+","+e.getDes()+","+e.getSal()+"\n"); 
	        	   
	           }
	              
	           fw.close();    
	          }
		 catch(Exception e){System.out.println(e);}    
	          System.out.println("File exported succesfully");    
		
	}

	

}