package net.hibernate;

import java.util.*;


public class ContactService {

	public static Contacts editContact(Contacts contact,int toEdit,Scanner scanner){
		System.out.println("");
		switch(toEdit) {
			case 1:
				long landline = Validate.validMobileNumber("Landline : ", scanner);
				contact.setLandline(landline);
				break;
			case 2:
				long mobile = Validate.validMobileNumber("Mobile : ", scanner);
				contact.setMobile(mobile);
				break;
			case 3:
				String email = Validate.emailValid(scanner);
				contact.setEmail(email);
				break;
		}
		return contact;
	}

	public static void listContact(Person persons){

		 System.out.println("\n");
		 System.out.println("List of Available Contacts for : " + persons.getFirstName() + "\n ");

   		 for (Contacts contact : persons.getContacts()) {
   		 	System.out.println("Contact Id : " + contact.getContactId());
   		 	System.out.println("Landline: " + contact.getLandline());
   		 	System.out.println("Mobile: " + contact.getMobile());
   		 	System.out.println("Email: " + contact.getEmail() + "\n");
   		 }
	}

}