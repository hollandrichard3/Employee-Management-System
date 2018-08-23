package com.kgainc.empmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgainc.empmanager.dao.TeamDAO;
import com.kgainc.empmanager.entity.EmployeeTeam;
import com.kgainc.empmanager.entity.Team;

@Service
public class TeamServiceImpl implements TeamService {

	// need to inject team DAO
	@Autowired
	private TeamDAO teamDAO;
	
	@Override
	@Transactional
	public List<Team> getTeams() {
		return teamDAO.getTeams();
	}

	@Override
	@Transactional
	public void saveTeam(Team theTeam) {

		teamDAO.saveTeam(theTeam);
	}

	@Override
	@Transactional
	public Team getTeam(int theId) {

		return teamDAO.getTeam(theId);
	}

	@Override
	@Transactional
	public void deleteTeam(int theId) {
		
		teamDAO.deleteTeam(theId);
	}

	@Override
	@Transactional
	public List<Team> searchTeams(String theSearchName) {

		return teamDAO.searchTeams(theSearchName);
	}

}
