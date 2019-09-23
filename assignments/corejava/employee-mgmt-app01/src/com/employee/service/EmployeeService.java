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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.employee.exception.IdAlreadyException;
import com.employee.exception.NoIdException;
import com.employee.model.Employee;


public class EmployeeService  {
	static Map<Integer, Employee> emplist1;
	static int id1;
	public class MyCallable implements Callable<Employee> {

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
			        emplist1.put(id,e2);
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
	
	
	public class UpdadeEmployee implements Callable<Employee> {
		@Override
		public Employee call() throws Exception {
			try {
					if(emplist1.get(id1)==null) {
						throw new NoIdException();
					}
					else {
						int id=id1;
						emplist1.remove(id);
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
						emplist1.put(id,new Employee(id,name,age,dept,desgn,country));
						System.out.println("The required employee details have been updated");	
					}
					}catch(NoIdException ex) {}
			
			return null;
		}
	}
	
	 public class AddEmployee implements Callable<Employee> {
		@Override
		public Employee call() throws Exception {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter empId-");
			int id=sc.nextInt();
			try {if(emplist1.get(id)!=null) {
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
				emplist1.put(id,new Employee(id,name,age,dept,desgn,country));	
			}
			}catch(IdAlreadyException ex) {}
			return null;
		}
	}
	 
	 public class ViewEmployee implements Callable<Employee> {
		@Override
		public Employee call() throws Exception {
			try {if(emplist1.get(id1)==null) {
				throw new NoIdException();
			}
			else {
				System.out.println("\n");
				System.out.println("The details-");
				System.out.println("\n");
				System.out.println(emplist1.get(id1));
				System.out.println("\n");
			}
			}catch(NoIdException ex) {}
			return null;
		}
	}
	 
	 
	 public class ViewAllEmployee implements Callable<Employee> {
		@Override
		public Employee call() throws Exception {
			System.out.println("\n");
			System.out.println("Details of all Employees-");
			for (Employee e : emplist1.values())  
	            System.out.println(e);
			System.out.println("\n");
			return null;
		}
	}
	 
	 public class DeleteEmployee implements Callable<Employee> {
			@Override
			public Employee call() throws Exception {
				System.out.print("Enter the empId-");
				Scanner sc = new Scanner(System.in);
				int id = sc.nextInt();
				try {
					if(emplist1.get(id)==null) {
						throw new NoIdException();
					}
					else {
						emplist1.remove(id);
						System.out.println("The required employee details have been deleted");
					}
					}catch(NoIdException ex) {
						
					}
				return null;
			}
		}
	 
	 
	 public class ExEmployee implements Callable<Employee> {
			@Override
			public Employee call() throws Exception {
				FileWriter writer = new FileWriter("C:\\Users\\PiyushDarshan\\Documents\\output2.txt");
				try { 
					for(Employee str: emplist1.values()) {
					  writer.write(str + "\n");
					}						
				} 
				catch (NullPointerException e) {
				}finally {
					writer.close();
				}
				return null;
			}
		}
	 
	 
	 
	 
	
	public static void add(HashMap<Integer,Employee> emplist){
		
		emplist1 = emplist;
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
	
	public static void view(int id,HashMap<Integer,Employee> emplist) {
		emplist1 = emplist;
		id1=id;	
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
	
	public static void viewAll(HashMap<Integer,Employee> emplist) {
		emplist1 = emplist;
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
	
	public static void delete(HashMap<Integer,Employee> emplist) {
		emplist1 = emplist;
		
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
	
	public static void update(int id,HashMap<Integer,Employee> emplist) {
		emplist1 = emplist;
		id1=id;		
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
		
	public static void importEmployee(HashMap<Integer,Employee> emplist) {
		emplist1 = emplist;
		
		EmployeeService eg = new EmployeeService();
		Callable<Employee> c1 = eg.new MyCallable();
		ExecutorService e = Executors.newFixedThreadPool(1);
		e.submit(c1);	
	}
	
	public static void exportEmployee(HashMap<Integer,Employee> emplist) throws IOException {
		emplist1 = emplist;
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

}
