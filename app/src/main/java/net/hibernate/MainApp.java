package net.hibernate;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;   
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class MainApp  {

	public static void main(String[] args) throws Exception {

		MainApp mainmenu = new MainApp();
		DataAccessObject dao = new DataAccessObject();

		dao.databaseConnection();

		System.out.println("\n***************************************************");
	 	System.out.println("********** Welcome to Hibernate Program! **********");
		System.out.println("***************************************************"+"\n\n");

	 	mainmenu.mainMenu();
		 
	}

	public void mainMenu() throws Exception {

		Scanner getInput = new Scanner(System.in);
		PersonService personservice = new PersonServiceImpl();
		ContactService contactservice = new ContactServiceImpl();
		RoleService roleservice = new RoleServiceImpl();
		ContactDAO contactDAO = new ContactDAO();
		RoleDAO roleDAO = new RoleDAO();
		UserInput userInput = new UserInput();
		Validate validate = new Validate();

		System.out.println("\n**************************** MAIN MENU ******************************");
		System.out.println("* 							            *");
		System.out.println("* *PERSON*		    *CONTACT*		*ROLE* 		    *");
		System.out.println("* 1. Create Person 	    7. Add Contact 	10. Add Role        *");
		System.out.println("* 2. Delete Person 	    8. Update Contact 	11. Update Role     *");
		System.out.println("* 3. Update Person 	    9. Delete Contact 	12. Delete Role     *");
		System.out.println("* 4. List of Person 				13. List of Role    *");
		System.out.println("* 5. Add Person Role 				14. Exit 	    *");
		System.out.println("* 6. Delete Person Role 					    *");
		System.out.println("*********************************************************************\n");

		System.out.print("Enter number of choice: ");
		int choice = validate.numberInputValidate(getInput);

		if (choice == 1) {

			Person person = userInput.inputPerson(getInput);
			personservice.createPerson(person);
			Contacts contact = userInput.inputContact(getInput, person);
			contactservice.createContact(contact);
			mainMenu();

		}

		if (choice == 2) {

			personservice.deletePerson();
			mainMenu();

		}

		if (choice == 3) {

			personservice.updatePerson();
			mainMenu();

		}

		if (choice == 4) {

			personservice.listPerson();
			mainMenu();

		}

		if (choice == 5) {

			personservice.addPersonRole();
			mainMenu();
		}

		if (choice == 6) {

			personservice.deletePersonRole();
			mainMenu();
		}

		if (choice == 7) {

			Person personContact = contactservice.addNewContact();

			if (personContact == null){
				System.out.println("\n\nPerson id doesn't exist!!");
			} else {
				Contacts contact = userInput.inputContact(getInput, personContact);
				contactDAO.addContact(contact);
			}
			mainMenu();
			
		}

		if (choice == 8) {

			contactservice.updateContact();
			mainMenu();
			
		}

		if (choice == 9) {

			contactservice.deleteContact();			
			mainMenu();	
			
		}

		if (choice == 10) {

			roleservice.addRole();
			mainMenu();	

		}

		if (choice == 11) {

			roleservice.updateRole();
			mainMenu();
			
		}

		if (choice == 12) {

			roleservice.deleteRole();
			mainMenu();
			
		}

		if (choice == 13) {

			Set<Roles> roleSet = roleDAO.findRole();
			roleservice.listRole(roleSet);
			mainMenu();

		}

		if (choice == 14) {

			System.out.println("Thank you for using our Software!");
	    	System.exit(0);
			
		}

		if ((choice > 14) || (choice < 1)) {
	    	System.out.println("Please Select from 1 - 14 \n");
	    	mainMenu();
    	}

	}

}