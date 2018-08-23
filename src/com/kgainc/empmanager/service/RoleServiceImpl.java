package com.kgainc.empmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgainc.empmanager.dao.RoleDAO;
import com.kgainc.empmanager.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {

	// need to inject team DAO
	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	@Transactional
	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}
	
	@Override
	@Transactional
	public void saveRole(Role theRole) {

		roleDAO.saveRole(theRole);
	}

	@Override
	@Transactional
	public Role getRole(int theId) {

		return roleDAO.getRole(theId);
	}

	@Override
	@Transactional
	public void deleteRole(int theId) {
		
		roleDAO.deleteRole(theId);
	}

}
