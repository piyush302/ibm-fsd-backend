import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;


public class EmployeeMain extends EmployeeService {

	public static void main(String[] args) throws IOException, SQLException {
		int option = 0;
		startDatabase();
		while(option!=6) {
		System.out.println("Menu -");
		System.out.println("1- Add Employee\n2- View Employee\n3- Update Employee\n4- Delete Employee\n5- View All Employees");
		System.out.print("6- Import Employee\n7- Export Employee\n8- Exit\nEnter Your Option-");
		Scanner sc = new Scanner(System.in);
		option = sc.nextInt();
		
		if(option==1) {
			add();
		}
		if(option==2) {
			view();
		}
		if(option==3) {
			update();
		}
		if(option==4) {
			delete();
		}
		if(option==5) {
			viewAll();
		}
		if(option==6) {
			importEmployee();
		}
		if(option==7) {
			exportEmployee();
		}
		if(option==8) {
			break;
		}

	}


}
}