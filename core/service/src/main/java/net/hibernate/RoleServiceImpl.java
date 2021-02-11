package net.hibernate;

import java.util.*;


public class RoleServiceImpl implements RoleService {

	public Set<Roles> removeSameRole(Set<Roles> role ,int roleToBeRemove) {
		Iterator<Roles> itr = role.iterator();
		while (itr.hasNext()) { 
			Roles roleToBeDeleted = itr.next(); 
			if (roleToBeDeleted.getRoleId() == roleToBeRemove) { 
				itr.remove(); 
			} 
		}
		return role;
	}

	public void listRole(Set<Roles> role){

		 System.out.println("List of Roles \n ");

   		 for (Roles roles : role) {
   		 	System.out.println("Role Id : " + roles.getRoleId() + ", '" + roles.getRole() + "'");
   		 }

   		 System.out.println("\n ");
	}

	public void addRole() throws Exception {

			System.out.println("\n****** ADD NEW ROLE ******\n");
			System.out.print("Enter new Role : ");
			String role = getInput.next();

			Set<Roles> roleSet = roleDAO.findRole();

			boolean exist = false;
			for (Roles roles : roleSet) {

				if (roles.getRole().toUpperCase().equals(role.toUpperCase())){
					System.out.println("\n\nRole already Exist");
					exist = true;
					break;
				}
			}

			if(exist == false){
				Roles newRole = new Roles(role); 
				roleDAO.addRole(newRole);
			}
			
	}

	public void updateRole() throws Exception {

		try {
			System.out.println("\n****** UPDATE ROLE ******\n");
			Set<Roles> roleSet = roleDAO.findRole();
			listRole(roleSet);

			System.out.println("\nSelect Role you want to update");
			System.out.print("Enter Role Id : ");
			int roleId = validate.numberInputValidate(getInput);
			Roles roleToUpdate = roleDAO.findRoleId(roleId);

			String newRole = validate.notNullString("Enter new Role value : ",getInput);
			roleToUpdate.setRole(newRole);	

			boolean exist = false;

			for (Roles roles : roleSet) {
				if (roles.getRole().toUpperCase().equals(newRole.toUpperCase())){
					System.out.println("\n\nRole already Exist");
					exist = true;
					break;
				}
			}

			if(exist == false){
				roleDAO.updateRole(roleToUpdate);
			}
			
					
		} catch(Exception e) {
			System.out.println("\nRole id not found!!\n");
		}

	}

	public void deleteRole() throws Exception {

		System.out.println("\n****** DELETE ROLE ******\n");

		Set<Roles> roleSet = roleDAO.findRole();
		listRole(roleSet);

		System.out.print("Enter Role Id to Delete : ");
		int roleId = validate.numberInputValidate(getInput);

		System.out.println("WARNING!");
		System.out.println("Deleting Role will also Delete the person with the Current Role");
		System.out.print("Are you sure?(Y/N): ");
		boolean confirm = validate.inputBoolean(getInput);
		
		if(confirm == true) {
			try {

				roleDAO.deleteRole(roleId);
				System.out.println("\nRole Successfully Deleted!");

			} catch(IllegalArgumentException e) {
				System.out.println("\nRole Id not Exist!!\n");
			}	
		}


	}

}