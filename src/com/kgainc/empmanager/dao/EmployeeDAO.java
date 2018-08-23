package com.kgainc.empmanager.dao;

import java.util.List;

import com.kgainc.empmanager.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> getEmployees();

	public void saveEmployee(Employee theEmployee);

	public Employee getEmployee(int theId);

	public void deleteEmployee(int theId);

	public List<Employee> searchEmployees(String theSearchName);
	
}
