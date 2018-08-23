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
public class EmployeeDAOImpl implements EmployeeDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	// inject employeeTeam service
	@Autowired
	private EmployeeTeamService employeeTeamService;
	
	@Override
	public List<Employee> getEmployees() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by last name
		Query<Employee> theQuery =
					currentSession.createQuery("from Employee order by lastName, firstName",
												Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		//LAZYTOEAGER
		for(Employee e: employees) {
			Hibernate.initialize(e.getEmployeeTeam());
		}
		
		// return the results
		
		return employees;
	}
	
	@Override
	public void saveEmployee(Employee theEmployee) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the employee ...
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public Employee getEmployee(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from the database using the primary key (id)
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		//LAZYTOEAGER
		Hibernate.initialize(theEmployee.getEmployeeTeam().getRole());
		Hibernate.initialize(theEmployee.getEmployeeTeam().getTeam());
		
		return theEmployee;
	}

	@Override
	public void deleteEmployee(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery =
				currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Employee> searchEmployees(String theSearchName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		// only search by name if theSearchName is not empty	
		if(theSearchName!= null && theSearchName.trim().length() > 0 ) {
			
			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Employee where lower(firstName) like :theName "
				  + "or lower(lastName) like :theName "
				  + "order by lastName, firstName", Employee.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		}
		else {
			// theSearchName is empty ... so just get all the employees
			theQuery = currentSession.createQuery("from Employee order by lastName, firstName", Employee.class);
		}
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		//LAZYTOEAGER
		for(Employee e: employees) {
			Hibernate.initialize(e.getEmployeeTeam());
		}
		
		// return the results
		return employees;
		
	}

}
