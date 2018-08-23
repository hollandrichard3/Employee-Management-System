package com.kgainc.empmanager.dao;

import java.util.List;

import com.kgainc.empmanager.entity.Role;

public interface RoleDAO {

	public List<Role> getRoles();

	public void saveRole(Role theRole);

	public Role getRole(int theId);

	public void deleteRole(int theId);

}
