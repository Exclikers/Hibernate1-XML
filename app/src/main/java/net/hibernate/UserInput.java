package net.hibernate;

import java.util.Scanner;
import java.util.Date;

public class UserInput  {

	public Person inputPerson(Scanner getInput) throws Exception {

		Validate validate = new Validate();

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
		int streetNumber = validate.numberInputValidate(getInput);

		System.out.print("Barangay : ");
		String barangay = getInput.next();

		System.out.print("Municipality/City : ");
		String municipality = getInput.next();

		System.out.print("Zip Code : ");
		int zipCode = validate.numberInputValidate(getInput);

		System.out.println("\n**** Additional Info ****\n");

		System.out.print("Birthday (yyyy-MM-dd) : ");
		Date birthdate = validate.inputDate(getInput);

		double gradeWeightedAverage = validate.inputDouble("Grade Weighted Average: ",getInput);

		System.out.print("Date Hired (yyyy-MM-dd) : ");
		Date dateHired = validate.inputDate(getInput);

		System.out.println("*** Employment Status : ***");
		System.out.println("1. Employed ");
		System.out.println("2. Unemployed ");
		System.out.print("Select : ");
		int employment = validate.numberInputValidate(getInput);
		String employmentStatus = null;
		if(employment == 1) {employmentStatus = "Employed";}
		if(employment >= 2) {employmentStatus = "Unmployed";}

		Person person = new Person(title, firstName, lastName, middleName, suffix, streetNumber, barangay, municipality, zipCode, birthdate, gradeWeightedAverage, dateHired, employmentStatus); 
		return person;
	 }

	public Contacts inputContact(Scanner getInput, Person person) {

		Validate validate = new Validate();

		System.out.println("\n**** CONTACT INFO ****\n");

		long landline = validate.validMobileNumber("Landline Number : ", getInput);

		long mobile = validate.validMobileNumber("Mobile Number : ", getInput);

		String email = validate.emailValid(getInput);

		Contacts contact = new Contacts(landline, mobile, email, person); 

		return contact;
	}

}