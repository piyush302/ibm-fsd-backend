package com.employee.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.employee.database.EmployeeDb;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;



//@ComponentScan(basePackages= {"com.examples.springcore"})
@Configuration
public class EmployeeBeanConfiguration {
	@Lazy
	@Bean
	  public Employee employee() {
	      return new Employee();
	      
	  }    
	@Lazy
	  @Bean
	  public EmployeeService empService() {
	      return new EmployeeService();
	  }
	@Lazy
	  @Bean
	  public EmployeeDb empDao()
	  {
		  return new EmployeeDb();
	  }
}