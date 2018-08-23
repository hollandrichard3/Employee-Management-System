package com.kgainc.empmanager.dao;

import java.util.List;

import com.kgainc.empmanager.entity.Employee;
import com.kgainc.empmanager.entity.EmployeeTeam;

public interface EmployeeTeamDAO {

	public List<EmployeeTeam> getEmployeeTeams();

	public List<Employee> getAsstManagers(int theTeamId);
	
	public void saveEmployeeTeam(EmployeeTeam theEmployeeTeam);

	public EmployeeTeam getEmployeeTeam(int theId);

	public void deleteEmployeeTeam(int theId);
	
}
