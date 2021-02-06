package net.hibernate;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;   
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class MainApp  {

	static Scanner getInput = new Scanner(System.in);
	static Set<Roles> role = new HashSet<>();

	public static void main(String[] args) throws Exception {

		DataAccessObject.databaseConnection();

		System.out.println("\n***************************************************");
	 	System.out.println("********** Welcome to Hibernate Program! **********");
		System.out.println("***************************************************"+"\n\n");

	 	mainMenu();
		 
	}

	public static void mainMenu() throws Exception {

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
		int choice = Validate.numberInputValidate(getInput);

		if (choice == 1) {

			createPerson();
			mainMenu();

		}

		if (choice == 2) {

			deletePerson();
			mainMenu();

		}

		if (choice == 3) {

			updatePerson();
			mainMenu();

		}

		if (choice == 4) {

			listPerson();
			mainMenu();

		}

		if (choice == 5) {

			addPersonRole();
			mainMenu();
		}

		if (choice == 6) {

			deletePersonRole();
			mainMenu();
		}

		if (choice == 7) {

			addNewContact();
			mainMenu();
			
		}

		if (choice == 8) {

			updateContact();
			mainMenu();
			
		}

		if (choice == 9) {

			deleteContact();			
			mainMenu();	
			
		}

		if (choice == 10) {

			addRole();
			mainMenu();	

		}

		if (choice == 11) {

			updateRole();
			mainMenu();
			
		}

		if (choice == 12) {

			deleteRole();
			mainMenu();
			
		}

		if (choice == 13) {

			Set<Roles> roleSet = DataAccessObject.findRole();
			Service.listRole(roleSet);
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

	private static void createPerson() throws Exception {

		Person person = inputPerson(getInput);
		DataAccessObject.addPerson(person);
		Contacts contact = inputContact(getInput, person);
		DataAccessObject.addContact(contact);

	}

	private static void deletePerson() throws Exception {

			System.out.println("\n****** DELETE PERSON ******\n");

			System.out.print("Enter Person Id Number: ");
			int personId = Validate.numberInputValidate(getInput);
			
			try {

				DataAccessObject.deletePerson(personId);
				System.out.println("\nId Number " + personId + " Successfully Deleted!");

			} catch(IllegalArgumentException e) {
				System.out.println("\nId Number not Exist!!\n");
			}	

	}

	private static void updatePerson() throws Exception {

			System.out.println("\n****** UPDATE PERSON ******\n");
			System.out.print("Enter ID Number you want to update : ");
			int personId = Validate.numberInputValidate(getInput);

			System.out.println("\n************** Select Column you want to Update **************");
			System.out.println("* 1. First Name 	5. Street No. 	9. Birthdate 	     *");
			System.out.println("* 2. Last Name 		6. Barangay 	10. GWA 	     *");
			System.out.println("* 3. Middle Name 	7. Municipality 11. Date Hired 	     *");
			System.out.println("* 4. Suffix 		8. Zip Code 	12. Employment Status*");
			System.out.println("**************************************************************\n");

			try {
				Person personToUpdate = DataAccessObject.findPersonId(personId);
				System.out.println("");
				System.out.print("Select: ");
				int selectedItem = Validate.numberInputValidate(getInput);

				if (selectedItem > 12) {
					System.out.println("Please Select from 1 to 12 Only!");
				} else {
					DataAccessObject.updatePerson(Service.editPerson(personToUpdate,selectedItem,getInput));
				}		
				
			} catch(Exception e) {
				System.out.println("\nId Number not found!!\n");
			}
	}

	private static void listPerson() throws Exception {

			Set<Person> persons = new HashSet<>();
			System.out.println("\n******* List of Person Order by *******");
			System.out.println("* 			              *");
			System.out.println("* 1 : Grade Weighted Average 	      *");
			System.out.println("* 2 : Date Hired 	              *");
			System.out.println("* 3 : Last Name 	              *");
			System.out.println("***************************************\n");
			System.out.print("Enter Selection: ");
			
			int orderList = Validate.numberInputValidate(getInput);
			if (orderList > 3 || orderList < 1) {System.out.println("\nSelect only from 1 - 3!\n"); 
				mainMenu();}

			if(orderList == 1) {
				persons = DataAccessObject.sortByGWA();
				if(persons.isEmpty()) {System.out.println("\nNo person on the List!!\n");}
			} else if(orderList == 2) {
				persons = DataAccessObject.sortByDateHired();
				if(persons.isEmpty()) {System.out.println("\nNo person on the List!!\n");}
			} else {
				persons = DataAccessObject.sortByLastName();
				if(persons.isEmpty()) {System.out.println("\nNo person on the List!!\n");}
			}
			Service.listPerson(persons);
			
	}

	private static void addPersonRole() {

			System.out.println("\n****** ADD PERSON ROLE ******\n");
			System.out.print("Enter Person Id Number: ");
			int personId = Validate.numberInputValidate(getInput);

			Set<Roles> roleSet = DataAccessObject.findRole();
			Service.listRole(roleSet);

			System.out.print("Enter Role Id Number: ");
			int roleId = Validate.numberInputValidate(getInput);

			try {
				Person addPersonRole = DataAccessObject.findPersonId(personId);
				Roles addRoleOfPerson = DataAccessObject.findRoleId(roleId);
				
				role = addPersonRole.getRoles();
				role.add(addRoleOfPerson);
				addPersonRole.setRoles(role);

				if(Validate.validatePersonRole(role,addRoleOfPerson.getRole()) == false){
					System.out.println("\n\nRole already Exist");
				} else {
					DataAccessObject.updatePerson(addPersonRole);
				}
			} catch(Exception e) {
				System.out.println("\n\nPlease check the person or role id number!");
			}

	}

	private static void deletePersonRole() {

			System.out.println("\n****** DELETE PERSON ROLE ******\n");
			System.out.print("Enter Person Id Number: ");
			int personId = Validate.numberInputValidate(getInput);

			try {
				Person deletePersonRole = DataAccessObject.findPersonId(personId);

				role = deletePersonRole.getRoles();

				if(!role.isEmpty()) {
					System.out.println("\n\nRoles available to delete!!!\n");
					for (Roles roles : role) {
						System.out.println("*" + roles.toString());
					}
					System.out.print("Enter Role Id Number to Delete: ");
					int roleIdToDeletePersonRole = Validate.numberInputValidate(getInput);
				
					deletePersonRole.setRoles(Service.removeSameRole(role,roleIdToDeletePersonRole));
					DataAccessObject.updatePerson(deletePersonRole);
					System.out.println("Role Deleted Successfully!");
				} else {
					System.out.println("\n\nNo available roles to delete!!");
				}
				
			} catch(Exception e) {
				System.out.println("\n\nPerson id doesn't exist!!");
			}
	}

	private static void addNewContact() {

			System.out.println("\n****** ADD NEW CONTACT ******\n");
			System.out.print("Enter Person Id Number: ");
			int personId = Validate.numberInputValidate(getInput);

			Person peronContact = DataAccessObject.findPersonId(personId);

			if (peronContact == null){
				System.out.println("\n\nPerson id doesn't exist!!");
			} else {
				Contacts contact = inputContact(getInput, peronContact);
				DataAccessObject.addContact(contact);
			}

	}

	private static void updateContact() {

			try {
				System.out.println("\n****** UPDATE CONTACT ******\n");
				System.out.print("Enter Person ID Number you want to update : ");
				int personId = Validate.numberInputValidate(getInput);

				Person person = DataAccessObject.findPersonId(personId);
				Service.listContact(person);

				System.out.println("Enter Contact Id Number you want to Update");
				System.out.print("Contact Id Number : ");
				int contactId = Validate.numberInputValidate(getInput);

				System.out.println("\n******* Select Column you want to Update *******");
				System.out.println("*                                              *");
				System.out.println("* 1 : Landline 	                               *");
				System.out.println("* 2 : Mobile 	                               *");
				System.out.println("* 3 : Email 	                               *");
				System.out.println("************************************************\n");
				System.out.print("Enter Selection: ");
			
				
				Contacts contactToUpdate = DataAccessObject.findContactId(contactId);
				int column = Validate.numberInputValidate(getInput);

				if (column > 3 || column < 1) {
					System.out.println("\nSelect only from 1 - 3!\n"); 
					mainMenu();
				} else {
					DataAccessObject.updateContact(Service.editContact(contactToUpdate,column,getInput));
					
				}		
					
			} catch(Exception e) {
				System.out.println("\nId Number not found!!\n");
			}
	}

	private static void deleteContact() {
			System.out.println("\n****** DELETE CONTACT ******\n");
			
			try {

				System.out.print("Enter Person ID Number you want to delete contact : ");
				int personId = Validate.numberInputValidate(getInput);

				Person person = DataAccessObject.findPersonId(personId);
				Service.listContact(person);

				System.out.println("Enter Contact Id Number you want to Delete");
				System.out.print("Contact Id Number : ");
				int contactId = Validate.numberInputValidate(getInput);

				DataAccessObject.deleteContact(contactId);
				System.out.println("\nContact " + contactId + " Successfully Deleted!");

			} catch(Exception e) {
				System.out.println("\nId Number not Exist!!\n");
			}
	}

	private static void addRole() throws Exception {

			System.out.println("\n****** ADD NEW ROLE ******\n");
			System.out.print("Enter new Role : ");
			String role = getInput.next();

			Set<Roles> roleSet = DataAccessObject.findRole();

			for (Roles roles : roleSet) {

				if (roles.getRole().toUpperCase().equals(role.toUpperCase())){
					System.out.println("\n\nRole already Exist");
					mainMenu();
				}
			}
			Roles newRole = new Roles(role); 
			DataAccessObject.addRole(newRole);
	}

	private static void updateRole() throws Exception {

		try {
			System.out.println("\n****** UPDATE ROLE ******\n");
			Set<Roles> roleSet = DataAccessObject.findRole();
			Service.listRole(roleSet);

			System.out.println("\nSelect Role you want to update");
			System.out.print("Enter Role Id : ");
			int roleId = Validate.numberInputValidate(getInput);
			Roles roleToUpdate = DataAccessObject.findRoleId(roleId);

			String newRole = Validate.notNullString("Enter new Role value : ",getInput);
			roleToUpdate.setRole(newRole);	

			for (Roles roles : roleSet) {
				if (roles.getRole().toUpperCase().equals(newRole.toUpperCase())){
					System.out.println("\n\nRole already Exist");
					mainMenu();
				}
			}

			DataAccessObject.updateRole(roleToUpdate);
					
		} catch(Exception e) {
			System.out.println("\nRole id not found!!\n");
		}

	}

	private static void deleteRole() throws Exception {

		System.out.println("\n****** DELETE ROLE ******\n");

		Set<Roles> roleSet = DataAccessObject.findRole();
		Service.listRole(roleSet);

		System.out.print("Enter Role Id to Delete : ");
		int roleId = Validate.numberInputValidate(getInput);

		System.out.println("WARNING!");
		System.out.println("Deleting Role will also Delete the person with the Current Role");
		System.out.print("Are you sure?(Y/N): ");
		boolean confirm = Validate.inputBoolean(getInput);
		
		if(confirm == true) {
			try {

				DataAccessObject.deleteRole(roleId);
				System.out.println("\nRole Successfully Deleted!");

			} catch(IllegalArgumentException e) {
				System.out.println("\nRole Id not Exist!!\n");
			}	
		}


	}

	public static Person inputPerson(Scanner getInput) throws Exception {

		System.out.println("\n**** ADD PERSON ****\n");
		System.out.print("Enter your Title : ");
		String title = getInput.next();

		System.out.print("Enter your First Name : ");
		String firstName = getInput.next();

		System.out.print("Enter your Last Name : ");
		String lastName = getInput.next();

		System.out.print("Enter your Middle Name : ");
		String middleName = getInput.next();

		System.out.print("Enter your Suffix : ");
		String suffix = getInput.next();

		System.out.println("\n**** ADDRESS ****\n");

		System.out.print("Street No.: ");
		int streetNumber = Validate.numberInputValidate(getInput);

		System.out.print("Barangay : ");
		String barangay = getInput.next();

		System.out.print("Municipality/City : ");
		String municipality = getInput.next();

		System.out.print("Zip Code : ");
		int zipCode = Validate.numberInputValidate(getInput);

		System.out.println("\n**** Additional Info ****\n");

		System.out.print("Birthday (yyyy-MM-dd) : ");
		Date birthdate = Validate.inputDate(getInput);
/*		System.out.print("Birthdate (dd/MM/yyyy) : ");
		String bDay = getInput.next();
		Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(bDay); */
/*
		System.out.print("Grade Weighted Average : ");
		double gradeWeightedAverage = getInput.nextDouble();	*/
		double gradeWeightedAverage = Validate.inputDouble("Grade Weighted Average: ",getInput);

		System.out.print("Date Hired (yyyy-MM-dd) : ");
		Date dateHired = Validate.inputDate(getInput);
/*		System.out.print("Date Hired (dd/MM/yyyy) : ");
		String dateH = getInput.next();
		Date dateHired = new SimpleDateFormat("dd/MM/yyyy").parse(dateH); */	

		System.out.println("*** Employment Status : ***");
		System.out.println("1. Employed ");
		System.out.println("2. Unemployed ");
		System.out.print("Select : ");
		int employment = Validate.numberInputValidate(getInput);
		String employmentStatus = null;
		if(employment == 1) {employmentStatus = "Employed";}
		if(employment >= 2) {employmentStatus = "Unmployed";}

		Person person = new Person(title, firstName, lastName, middleName, suffix, streetNumber, barangay, municipality, zipCode, birthdate, gradeWeightedAverage, dateHired, employmentStatus); 
		return person;
	 }

	 public static Contacts inputContact(Scanner getInput, Person person) {

		System.out.println("\n**** CONTACT INFO ****\n");

		long landline = Validate.validMobileNumber("Landline Number : ", getInput);

		long mobile = Validate.validMobileNumber("Mobile Number : ", getInput);

		String email = Validate.emailValid(getInput);

		Contacts contact = new Contacts(landline, mobile, email, person); 

		return contact;
	}


}