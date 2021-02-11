package net.hibernate;

import java.util.*;

public class PersonServiceImpl implements PersonService {

	public Person editPerson(Person person,int toEdit,Scanner scanner){
		System.out.println("");
		switch(toEdit) {
			case 1:
				String firstName = validate.notNullString("First Name : ",scanner);
				person.setFirstName(firstName);
				break;
			case 2:
				String lastName = validate.notNullString("Last Name : ",scanner);
				person.setLastName(lastName);
				break;
			case 3:
				String middleName = validate.notNullString("Middle Name : ",scanner);
				person.setMiddleName(middleName);
				break;
			case 4:
				String suffixname = validate.notNullString("Suffix Name : ",scanner);
				person.setSuffix(suffixname);
				break;
			case 5:
				int streetNumber = validate.validateInput("Street Number : ",scanner);
				person.setStreetNumber(streetNumber);
				break;
			case 6:
				String barangay = validate.notNullString("Barangay : ",scanner);
				person.setBarangay(barangay);
				break;
			case 7:
				String municipality = validate.notNullString("Municipality/City : ",scanner);
				person.setMunicipality(municipality);
				break;
			case 8:
				int zipcode = validate.validateInput("Zipcode : ",scanner);
				person.setZipCode(zipcode);
				break;
			case 9:
				System.out.print("Birthday (yyyy-MM-dd) : ");
				Date birthDay = validate.inputDate(scanner);
				person.setBirthdate(birthDay);
				break;
			case 10:
				double gradeWeightedAverage = validate.inputDouble("Grade Weighted Average: ",scanner);
				person.setGradeWeightedAverage(gradeWeightedAverage);
				break;
			case 11:
				System.out.print("Date Hired (yyyy-MM-dd) : ");
				Date dateHired = validate.inputDate(scanner);
				person.setDateHired(dateHired);
				break;
			case 12:
				System.out.println("*** Employment Status : ***");
				System.out.println("1. Employed ");
				System.out.println("2. Unemployed ");
				System.out.print("Select : ");
				int employment = validate.numberInputValidate(scanner);
				String employmentStatus = null;
				if(employment == 1) {employmentStatus = "Employed";}
				if(employment >= 2) {employmentStatus = "Unmployed";}
				person.setEmploymentStatus(employmentStatus);
				break;
		}
		return person;
	}


	public void listPerson(Set<Person> persons){

		 System.out.println("\n");

		 for (Iterator iterator = persons.iterator(); 
		 	iterator.hasNext();){
			 
		 Person person = (Person) iterator.next();

		 System.out.println("Id Number: " + person.getPersonId());
		 System.out.print("Name: " + person.getTitle());
		 System.out.print(" " + person.getFirstName());
		 System.out.print(" " + person.getMiddleName());
		 System.out.print(" " + person.getLastName());
		 if (person.getSuffix() != null) {System.out.print(" " + person.getSuffix());}
		 System.out.print("\nAddress: " + person.getStreetNumber());
		 System.out.print(" " + person.getBarangay());
		 System.out.print(" " + person.getMunicipality());
		 System.out.println(" " + person.getZipCode());
		 System.out.println(" Birthday: " + person.getBirthdate());
		 System.out.println(" GWA: " + person.getGradeWeightedAverage());
		 System.out.println(" Date Hired: " + person.getDateHired());
		 System.out.println(" Employment Status: " + person.getEmploymentStatus());
		 
		 System.out.println("CONTACTS:");

   		 System.out.print("Landline: ");
   		 for (Contacts contact : person.getContacts()) {
   		 	System.out.print(contact.getLandline() + ", ");
   		 }

   		 System.out.print("\nMobile: ");
   		 for (Contacts contact : person.getContacts()) {
   		 	System.out.print(contact.getMobile() + ", ");
   		 }

   		 System.out.print("\nEmail: ");
   		 for (Contacts contact : person.getContacts()) {
   		 	System.out.print(contact.getEmail() + ", ");
   		 }

		 if(!person.getRoles().isEmpty()){System.out.print("\nRole: ");}
		 
		 for (Roles role : person.getRoles()) {
		 	System.out.print(role.getRole().toUpperCase() + ", ");
		 }

		  System.out.println("\n");
		}
	}

	public void createPerson(Person person) throws Exception {

		personDAO.addPerson(person);

	}

	public void deletePerson() throws Exception {

			System.out.println("\n****** DELETE PERSON ******\n");

			System.out.print("Enter Person Id Number: ");
			int personId = validate.numberInputValidate(getInput);
			
			System.out.println("WARNING!");
			System.out.println("Please Remove the person role first!");
			System.out.println("Deleting Person with assigned Role will delete the role and person with its role");
			System.out.print("Are you sure?(Y/N): ");
			boolean confirm = validate.inputBoolean(getInput);
			
			if(confirm == true) {
				try {

					personDAO.deletePerson(personId);
					System.out.println("\nId Number " + personId + " Successfully Deleted!");

				} catch(IllegalArgumentException e) {
					System.out.println("\nId Number not Exist!!\n");
				}	
			}

	}

	public void updatePerson() throws Exception {

			System.out.println("\n****** UPDATE PERSON ******\n");
			System.out.print("Enter ID Number you want to update : ");
			int personId = validate.numberInputValidate(getInput);

			System.out.println("\n************** Select Column you want to Update **************");
			System.out.println("* 1. First Name 	5. Street No. 	9. Birthdate 	     *");
			System.out.println("* 2. Last Name 		6. Barangay 	10. GWA 	     *");
			System.out.println("* 3. Middle Name 	7. Municipality 11. Date Hired 	     *");
			System.out.println("* 4. Suffix 		8. Zip Code 	12. Employment Status*");
			System.out.println("**************************************************************\n");

			try {
				Person personToUpdate = personDAO.findPersonId(personId);
				System.out.println("");
				System.out.print("Select: ");
				int selectedItem = validate.numberInputValidate(getInput);

				if (selectedItem > 12) {
					System.out.println("Please Select from 1 to 12 Only!");
				} else {
					personDAO.updatePerson(editPerson(personToUpdate,selectedItem,getInput));
				}		
				
			} catch(Exception e) {
				System.out.println("\nId Number not found!!\n");
			}
	}

	public void listPerson() throws Exception {

			DataAccessObject dao = new DataAccessObject();
			Set<Person> persons = new HashSet<>();
			System.out.println("\n******* List of Person Order by *******");
			System.out.println("* 			              *");
			System.out.println("* 1 : Grade Weighted Average 	      *");
			System.out.println("* 2 : Date Hired 	              *");
			System.out.println("* 3 : Last Name 	              *");
			System.out.println("***************************************\n");
			System.out.print("Enter Selection: ");
			
			int orderList = validate.numberInputValidate(getInput);
			if (orderList > 3 || orderList < 1) {System.out.println("\nSelect only from 1 - 3!\n");}

			if(orderList == 1) {
				persons = dao.sortByGWA();
				if(persons.isEmpty()) {System.out.println("\nNo person on the List!!\n");}
			} else if(orderList == 2) {
				persons = dao.sortByDateHired();
				if(persons.isEmpty()) {System.out.println("\nNo person on the List!!\n");}
			} else {
				persons = dao.sortByLastName();
				if(persons.isEmpty()) {System.out.println("\nNo person on the List!!\n");}
			}
			listPerson(persons);
			
	}

	public void addPersonRole() {

			RoleService roleService = new RoleServiceImpl();

			System.out.println("\n****** ADD PERSON ROLE ******\n");
			System.out.print("Enter Person Id Number: ");
			int personId = validate.numberInputValidate(getInput);

			Set<Roles> roleSet = roleDAO.findRole();
			roleService.listRole(roleSet);

			System.out.print("Enter Role Id Number: ");
			int roleId = validate.numberInputValidate(getInput);

			try {
				Person addPersonRole = personDAO.findPersonId(personId);
				Roles addRoleOfPerson = roleDAO.findRoleId(roleId);
				Set<Roles> role = new HashSet<>();
				role = addPersonRole.getRoles();
				role.add(addRoleOfPerson);

				if(validate.validatePersonRole(role,addRoleOfPerson.getRole()) == false){
					System.out.println("\n\nRole already Exist");
				} else {

					addPersonRole.setRoles(role);
					personDAO.updatePerson(addPersonRole);
				}
			} catch(Exception e) {
				System.out.println("\n\nPlease check the person or role id number!");
			}

				/*System.out.println(addPersonRole.getRoles());
				System.out.println(addRoleOfPerson.getRole());*/
	}

	public void deletePersonRole() {
			RoleService roleService = new RoleServiceImpl();
			Set<Roles> role = new HashSet<>();
			System.out.println("\n****** DELETE PERSON ROLE ******\n");
			System.out.print("Enter Person Id Number: ");
			int personId = validate.numberInputValidate(getInput);

			try {
				Person deletePersonRole = personDAO.findPersonId(personId);

				role = deletePersonRole.getRoles();

				if(!role.isEmpty()) {
					System.out.println("\n\nRoles available to delete!!!\n");
					for (Roles roles : role) {
						System.out.println("*" + roles.toString());
					}
					System.out.print("Enter Role Id Number to Delete: ");
					int roleIdToDeletePersonRole = validate.numberInputValidate(getInput);
				
					deletePersonRole.setRoles(roleService.removeSameRole(role,roleIdToDeletePersonRole));
					personDAO.updatePerson(deletePersonRole);
					System.out.println("Role Deleted Successfully!");
				} else {
					System.out.println("\n\nNo available roles to delete!!");
				}
				
			} catch(Exception e) {
				System.out.println("\n\nPerson id doesn't exist!!");
			}
	}

}