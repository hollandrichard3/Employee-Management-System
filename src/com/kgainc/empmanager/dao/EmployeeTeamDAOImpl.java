package com.kgainc.empmanager.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kgainc.empmanager.entity.Employee;
import com.kgainc.empmanager.entity.EmployeeTeam;
import com.kgainc.empmanager.entity.Team;
import com.kgainc.empmanager.service.EmployeeService;
import com.kgainc.empmanager.service.EmployeeTeamService;
import com.kgainc.empmanager.service.TeamService;

@Repository //allows this to show up in component scanning
public class EmployeeTeamDAOImpl implements EmployeeTeamDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	// inject employeeTeam service
	@Autowired
	private TeamService teamService;	
	
	// inject employeeTeam service
	@Autowired
	private EmployeeService employeeService;	
	
	@Override
	public List<EmployeeTeam> getEmployeeTeams() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by last name
		Query<EmployeeTeam> theQuery =
					currentSession.createQuery("from employees_teams order by team_id",
												EmployeeTeam.class);
		
		// execute query and get result list
		List<EmployeeTeam> employeeTeams = theQuery.getResultList();
		
		// return the results
		
		return employeeTeams;
	}
	
	@Override
	public List<Employee> getAsstManagers(int theTeamId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by team name
		Query<Integer> theQuery =
					currentSession.createQuery("SELECT employee.id from EmployeeTeam where team.id=:TeamId and role=2");
		theQuery.setParameter("TeamId", theTeamId);
		
		// execute query and get result list
		List<Integer> results = theQuery.getResultList();
		
		List<Employee> asstManagers = new ArrayList<>();
		
		for(Integer result: results) {
			Employee asstManager = employeeService.getEmployee(result);
			asstManagers.add(asstManager);
		}
		
		return asstManagers;
	}
	
	@Override
	public void saveEmployeeTeam(EmployeeTeam theEmployeeTeam) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the employee ...
		currentSession.saveOrUpdate(theEmployeeTeam);
	}

	@Override
	public EmployeeTeam getEmployeeTeam(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from the database using the primary key (id)
		EmployeeTeam theEmployeeTeam = currentSession.get(EmployeeTeam.class, theId);
		
		return theEmployeeTeam;
	}

	@Override
	public void deleteEmployeeTeam(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery =
				currentSession.createQuery("delete from EmployeeTeam where employee.id=:employeeTeamId");
		theQuery.setParameter("employeeTeamId", theId);
		
		theQuery.executeUpdate();
		
	}

}
