package com.kgainc.empmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgainc.empmanager.dao.EmployeeDAO;
import com.kgainc.empmanager.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// need to inject employee DAO
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return employeeDAO.getEmployees();
	}

	@Override
	@Transactional
	public void saveEmployee(Employee theEmployee) {

		employeeDAO.saveEmployee(theEmployee);
	}

	@Override
	@Transactional
	public Employee getEmployee(int theId) {

		return employeeDAO.getEmployee(theId);
	}

	@Override
	@Transactional
	public void deleteEmployee(int theId) {
		
		employeeDAO.deleteEmployee(theId);
	}

	@Override
	@Transactional
	public List<Employee> searchEmployees(String theSearchName) {

		return employeeDAO.searchEmployees(theSearchName);
	}

}
