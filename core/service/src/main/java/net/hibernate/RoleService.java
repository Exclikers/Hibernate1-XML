package net.hibernate;

import java.util.*;


public class RoleService {

	public static Set<Roles> removeSameRole(Set<Roles> role ,int roleToBeRemove) {
		Iterator<Roles> itr = role.iterator();
		while (itr.hasNext()) { 
			Roles roleToBeDeleted = itr.next(); 
			if (roleToBeDeleted.getRoleId() == roleToBeRemove) { 
				itr.remove(); 
			} 
		}
		return role;
	}

	public static void listRole(Set<Roles> role){

		 System.out.println("List of Roles \n ");

   		 for (Roles roles : role) {
   		 	System.out.println("Role Id : " + roles.getRoleId() + ", '" + roles.getRole() + "'");
   		 }

   		 System.out.println("\n ");
	}

}