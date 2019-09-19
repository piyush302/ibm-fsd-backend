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

public class EmployeeService  {
	static Map<Integer, Employee> emplist1;
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
	        	
	        } finally {
	            if (input != null) {
	            	input.close();
	            }
	        }
			return null;
		}
		
	}
	
	public static void add(HashMap<Integer,Employee> emplist) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter empId-");
		int id=sc.nextInt();
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
		emplist.put(id,new Employee(id,name,age,dept,desgn,country));
		
		
		
	}
	
	public static void view(int id,HashMap<Integer,Employee> emplist) {
		System.out.println("The details-");
		System.out.println(emplist.get(id));	
	}
	
	public static void viewAll(HashMap<Integer,Employee> emplist) {
		System.out.println("Details of all Employees-");
		for (Employee e : emplist.values())  
            System.out.println(e);
	}
	
	public static void delete(HashMap<Integer,Employee> emplist) {
		System.out.print("Enter the empId-");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		emplist.remove(id);
		System.out.println("The required employee details have been deleted");
						
	}
	
	public static void update(int id,HashMap<Integer,Employee> emplist) {
		int id1=id;
		emplist.remove(id);
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
		emplist.put(id1,new Employee(id1,name,age,dept,desgn,country));
		

		System.out.println("The required employee details have been updated");
						
	}
		
	public static void importEmployee(HashMap<Integer,Employee> emplist) throws FileNotFoundException {
		emplist1 = emplist;
		
		EmployeeService eg = new EmployeeService();
		Callable<Employee> c1 = eg.new MyCallable();
		ExecutorService e = Executors.newFixedThreadPool(1);
		e.submit(c1);	
	}
	
	public static void exportEmployee(HashMap<Integer,Employee> emplist) throws IOException {
		FileWriter writer = new FileWriter("C:\\Users\\PiyushDarshan\\Documents\\output2.txt");
		try { 
			for(Employee str: emplist.values()) {
			  writer.write(str + "\n");
			}						
		} 
		catch (NullPointerException e) {
		}finally {
			writer.close();
		}


	}

	
	
		
	
}
