package net.hibernate;

import java.util.*;


public class PersonService {

	public static Person editPerson(Person person,int toEdit,Scanner scanner){
		System.out.println("");
		switch(toEdit) {
			case 1:
				String firstName = Validate.notNullString("First Name : ",scanner);
				person.setFirstName(firstName);
				break;
			case 2:
				String lastName = Validate.notNullString("Last Name : ",scanner);
				person.setLastName(lastName);
				break;
			case 3:
				String middleName = Validate.notNullString("Middle Name : ",scanner);
				person.setMiddleName(middleName);
				break;
			case 4:
				String suffixname = Validate.notNullString("Suffix Name : ",scanner);
				person.setSuffix(suffixname);
				break;
			case 5:
				int streetNumber = Validate.validateInput("Street Number : ",scanner);
				person.setStreetNumber(streetNumber);
				break;
			case 6:
				String barangay = Validate.notNullString("Barangay : ",scanner);
				person.setBarangay(barangay);
				break;
			case 7:
				String municipality = Validate.notNullString("Municipality/City : ",scanner);
				person.setMunicipality(municipality);
				break;
			case 8:
				int zipcode = Validate.validateInput("Zipcode : ",scanner);
				person.setZipCode(zipcode);
				break;
			case 9:
				System.out.print("Birthday (yyyy-MM-dd) : ");
				Date birthDay = Validate.inputDate(scanner);
				person.setBirthdate(birthDay);
				break;
			case 10:
				double gradeWeightedAverage = Validate.inputDouble("Grade Weighted Average: ",scanner);
				person.setGradeWeightedAverage(gradeWeightedAverage);
				break;
			case 11:
				System.out.print("Date Hired (yyyy-MM-dd) : ");
				Date dateHired = Validate.inputDate(scanner);
				person.setDateHired(dateHired);
				break;
			case 12:
				System.out.println("*** Employment Status : ***");
				System.out.println("1. Employed ");
				System.out.println("2. Unemployed ");
				System.out.print("Select : ");
				int employment = Validate.numberInputValidate(scanner);
				String employmentStatus = null;
				if(employment == 1) {employmentStatus = "Employed";}
				if(employment >= 2) {employmentStatus = "Unmployed";}
				person.setEmploymentStatus(employmentStatus);
				break;
		}
		return person;
	}


	public static void listPerson(Set<Person> persons){

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

}