package com.kgainc.empmanager.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kgainc.empmanager.entity.Role;

@Repository //allows this to show up in component scanning
public class RoleDAOImpl implements RoleDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Role> getRoles() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by team name
		Query<Role> theQuery =
					currentSession.createQuery("from Role order by id",
												Role.class);
		
		// execute query and get result list
		List<Role> roles = theQuery.getResultList();
		
		// return the results
		
		return roles;
	}

	@Override
	public void saveRole(Role theRole) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the team ...
		currentSession.saveOrUpdate(theRole);
		System.out.println(theRole);
	}

	@Override
	public Role getRole(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from the database using the primary key (id)
		Role theRole = currentSession.get(Role.class, theId);
		
		return theRole;
	}

	@Override
	public void deleteRole(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery =
				currentSession.createQuery("delete from Role where id=:roleId");
		theQuery.setParameter("roleId", theId);
		
		theQuery.executeUpdate();
		
	}

}
