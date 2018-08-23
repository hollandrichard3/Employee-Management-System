package com.kgainc.empmanager.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kgainc.empmanager.entity.Employee;
import com.kgainc.empmanager.entity.EmployeeTeam;
import com.kgainc.empmanager.entity.Team;
import com.kgainc.empmanager.service.EmployeeTeamService;

@Repository //allows this to show up in component scanning
public class TeamDAOImpl implements TeamDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	// inject employeeTeam service
	@Autowired
	private EmployeeTeamService employeeTeamService;
	
	@Override
	public List<Team> getTeams() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by team name
		Query<Team> theQuery =
					currentSession.createQuery("from Team order by teamName",
												Team.class);
		
		// execute query and get result list
		List<Team> teams = theQuery.getResultList();
		
		//LAZYTOEAGER
		for(Team team: teams) {
			Hibernate.initialize(team.getManager());
		}
		
		// return the results
		
		return teams;
	}
	
	@Override
	public void saveTeam(Team theTeam) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the team ...
		currentSession.saveOrUpdate(theTeam);
		System.out.println(theTeam);
	}

	@Override
	public Team getTeam(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from the database using the primary key (id)
		Team theTeam = currentSession.get(Team.class, theId);
		
		Hibernate.initialize(theTeam.getManager());
		
		//LAZYTOEAGER
		for(Employee theEmployee: theTeam.getEmployees()) {
			Hibernate.initialize(theEmployee);
		}
		
		return theTeam;
	}

	@Override
	public void deleteTeam(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQueryET =
				currentSession.createQuery("update EmployeeTeam set team.id=20, role.id=1 where team.id=:teamId");
		theQueryET.setParameter("teamId", theId);
		theQueryET.executeUpdate();
		
		// delete object with primary key
		Query theQuery =
				currentSession.createQuery("delete from Team where id=:teamId");
		theQuery.setParameter("teamId", theId);
		
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Team> searchTeams(String theSearchName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		// only search by name if theSearchName is not empty
		
		if(theSearchName!= null && theSearchName.trim().length() > 0 ) {
			
			// search for teamName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Team where lower(teamName) like :theName "
				  + "order by teamName", Team.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		}
		else {
			// theSearchName is empty ... so just get all the teams
			theQuery = currentSession.createQuery("from Team order by teamName", Team.class);
		}
		
		// execute query and get result list
		List<Team> teams = theQuery.getResultList();
		
		// return the results
		return teams;
		
	}

}
