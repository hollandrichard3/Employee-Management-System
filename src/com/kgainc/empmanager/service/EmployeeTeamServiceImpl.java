package com.kgainc.empmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgainc.empmanager.dao.EmployeeTeamDAO;
import com.kgainc.empmanager.entity.Employee;
import com.kgainc.empmanager.entity.EmployeeTeam;

@Service
public class EmployeeTeamServiceImpl implements EmployeeTeamService {

	// need to inject employee DAO
	@Autowired
	private EmployeeTeamDAO employeeTeamDAO;
	
	@Override
	@Transactional
	public List<EmployeeTeam> getEmployeeTeams() {
		return employeeTeamDAO.getEmployeeTeams();
	}
	
	@Transactional
	@Override
	public List<Employee> getAsstManagers(int theTeamId) {
		return employeeTeamDAO.getAsstManagers(theTeamId);
	}
	
	@Override
	@Transactional
	public void saveEmployeeTeam(EmployeeTeam theEmployeeTeam) {

		employeeTeamDAO.saveEmployeeTeam(theEmployeeTeam);

	}

	@Override
	@Transactional
	public EmployeeTeam getEmployeeTeam(int theId) {

		return employeeTeamDAO.getEmployeeTeam(theId);
	}

	@Override
	@Transactional
	public void deleteEmployeeTeam(int theId) {
		
		employeeTeamDAO.deleteEmployeeTeam(theId);
	}

}
