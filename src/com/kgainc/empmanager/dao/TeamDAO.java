package com.kgainc.empmanager.dao;

import java.util.List;

import com.kgainc.empmanager.entity.Employee;
import com.kgainc.empmanager.entity.EmployeeTeam;
import com.kgainc.empmanager.entity.Team;

public interface TeamDAO {

	public List<Team> getTeams();
	
	public void saveTeam(Team theTeam);

	public Team getTeam(int theId);

	public void deleteTeam(int theId);

	public List<Team> searchTeams(String theSearchName);
	
}
