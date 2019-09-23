package com.employee.service;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.employee.connection.EmployeeConnection;
import com.employee.exception.IdAlreadyException;
import com.employee.exception.NoIdException;
import com.employee.model.Employee;


public class EmployeeService extends EmployeeConnection{
	
	public class AddEmployee implements Callable<Employee> {
		@Override
		public Employee call() throws Exception {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter empId-");
			int id=sc.nextInt();
			try {if(findId(id)==0) {
				throw new IdAlreadyException();
			}
			else {
				System.out.print("Enter name-");
				String name = sc.next();
				System.out.print("Enter age-");
				int age = sc.nextInt();
				System.out.print("Enter dept-");
				String dept = sc.next();
				System.out.print("Enter desgn-");
				String desgn = sc.next();
				System.out.print("Enter country-");
				String country = sc.next();
				Employee e =new Employee(id,name,age,dept,desgn,country);
				addEmployee(e);	
			}
			}catch(IdAlreadyException ex) {}
			

			return null;
		}
	}


	public static void add(){
		EmployeeService e1 = new EmployeeService();
		Callable<Employee> c2 = e1.new AddEmployee();
		ExecutorService e = Executors.newFixedThreadPool(5);
		Future<Employee> f = e.submit(c2);
		
		
			while(!f.isDone()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
			}
		
		
	}
	
	public class ViewEmployee implements Callable<Employee> {
		@Override
		public Employee call() throws Exception {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter empId-");
			int id=sc.nextInt();
			try {if(findId(id)==1) {
				throw new NoIdException();
			}
			else {
				viewEmployee(id);
			}
			}catch(NoIdException ex) {}
			return null;
		}
	}
	
	public static void view() {
		EmployeeService e1 = new EmployeeService();
		Callable<Employee> c2 = e1.new ViewEmployee();
		ExecutorService e = Executors.newFixedThreadPool(5);
		Future<Employee> f = e.submit(c2);
		
		
			while(!f.isDone()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
			}


	}
	
	public class ViewAllEmployee implements Callable<Employee> {
		@Override
		public Employee call() throws Exception {
			viewEveryEmp();
			return null;
		}
	}
	
	
	public static void viewAll() {
		EmployeeService e1 = new EmployeeService();
		Callable<Employee> c2 = e1.new ViewAllEmployee();
		ExecutorService e = Executors.newFixedThreadPool(5);
		Future<Employee> f = e.submit(c2);
		
		
			while(!f.isDone()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
			}
	}
	
	public class DeleteEmployee implements Callable<Employee> {
		@Override
		public Employee call() throws Exception {
			System.out.print("Enter the empId-");
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			try {
				if(findId(id)==1) {
					throw new NoIdException();
				}
				else {
					deleteEmp(id);
					System.out.println("The required employee details have been deleted");
				}
				}catch(NoIdException ex) {
					
				}
			return null;
		}
	}
	
	public static void delete() {
		EmployeeService e1 = new EmployeeService();
		Callable<Employee> c2 = e1.new DeleteEmployee();
		ExecutorService e = Executors.newFixedThreadPool(5);
		Future<Employee> f = e.submit(c2);
			while(!f.isDone()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
			}
	}
	
	public static void update() {	
		EmployeeService e1 = new EmployeeService();
		Callable<Employee> c2 = e1.new UpdadeEmployee();
		ExecutorService e = Executors.newFixedThreadPool(5);
		Future<Employee> f = e.submit(c2);
		
		
			while(!f.isDone()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
			}
		
						
	}
	
	public class UpdadeEmployee implements Callable<Employee> {
		@Override
		public Employee call() throws Exception {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter empId-");
			int id=sc.nextInt();
			try {
					if(findId(id)==1) {
						throw new NoIdException();
					}
					else {
						updateEmp(id);
					}
					}catch(NoIdException ex) {}
			
			return null;
		}
	}
	
	public static void importEmployee() {
		
		EmployeeService eg = new EmployeeService();
		Callable<Employee> c1 = eg.new impEmp();
		ExecutorService e = Executors.newFixedThreadPool(1);
		e.submit(c1);	
	}
	
	public class impEmp implements Callable<Employee> {

		@Override
		public Employee call() throws Exception {
	        Scanner input = null;
	        try {
	        	System.out.println("hello piyush");
	        	
	        	
	        	input = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream
						 ("C:\\Users\\PiyushDarshan\\Documents\\piyush.txt"))));
			    input.useDelimiter(",|\n");
			    
			    while(input.hasNext()) {
			        int id = input.nextInt();
			        String name = input.next();
			        int age = input.nextInt();
			        String department = input.next();
			        String desgn = input.next();			        
			        String country = input.next();

			        Employee e2 = new Employee(id,name, age, department, desgn, country);
			        addEmployee(e2);
			    }
	        	
	        }catch(FileNotFoundException e){
	        	System.out.println("Import file is not found");
	        } finally {
	            if (input != null) {
	            	input.close();
	            }
	        }
			return null;
		}
		
	}
	
	public static void exportEmployee() throws IOException {
		EmployeeService e1 = new EmployeeService();
		Callable<Employee> c2 = e1.new ExEmployee();
		ExecutorService e = Executors.newFixedThreadPool(5);
		Future<Employee> f = e.submit(c2);
		
		
			while(!f.isDone()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
			}


	}
	
	public class ExEmployee implements Callable<Employee> {
		@Override
		public Employee call() throws Exception {
			try { 
				expEmp();				
			} 
			catch (NullPointerException e) {}

			return null;
		}
	}

	
	
}
