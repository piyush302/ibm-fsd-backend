package com.examples.empapp.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.examples.empapp.dao.EmployeeDAO;
import com.examples.empapp.model.Employee;

public class EmployeeService {

	EmployeeDAO employeeDao;

	Comparator<Employee> EMPLOYEE_NAME_ASC_SORT = new Comparator<Employee>() {
		@Override
		public int compare(Employee o1, Employee o2) {
			return o1.getName().compareTo(o2.getName());

		}
	};

	public EmployeeService() {
		employeeDao = new EmployeeDAO();
	}

	public boolean create(Employee employee) {
		return employeeDao.create(employee);		
	}

	public Employee get(int id) {
		return employeeDao.get(id);
	}

	public List<Employee> getAll() {
		return employeeDao.getAll();
	}

	public boolean update(Employee employee) {
		return employeeDao.update(employee);
	}

	public boolean delete(int id) {
		return employeeDao.delete(id);
	}


}
