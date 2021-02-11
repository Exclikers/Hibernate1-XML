package net.hibernate;

import java.util.*;


public interface RoleService {

	Scanner getInput = new Scanner(System.in);
	RoleDAO roleDAO = new RoleDAO();
	Validate validate = new Validate();
	
	Set<Roles> removeSameRole(Set<Roles> role ,int roleToBeRemove);
	void listRole(Set<Roles> role);
	void addRole() throws Exception;
	void updateRole() throws Exception;
	void deleteRole() throws Exception;

}