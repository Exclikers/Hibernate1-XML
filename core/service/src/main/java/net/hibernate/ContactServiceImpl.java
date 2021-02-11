package net.hibernate;

import java.util.*;


public class ContactServiceImpl implements ContactService {

	public Contacts editContact(Contacts contact,int toEdit,Scanner scanner){
		System.out.println("");
		switch(toEdit) {
			case 1:
				long landline = validate.validMobileNumber("Landline : ", scanner);
				contact.setLandline(landline);
				break;
			case 2:
				long mobile = validate.validMobileNumber("Mobile : ", scanner);
				contact.setMobile(mobile);
				break;
			case 3:
				String email = validate.emailValid(scanner);
				contact.setEmail(email);
				break;
		}
		return contact;
	}

	public void listContact(Person persons){

		 System.out.println("\n");
		 System.out.println("List of Available Contacts for : " + persons.getFirstName() + "\n ");

   		 for (Contacts contact : persons.getContacts()) {
   		 	System.out.println("Contact Id : " + contact.getContactId());
   		 	System.out.println("Landline: " + contact.getLandline());
   		 	System.out.println("Mobile: " + contact.getMobile());
   		 	System.out.println("Email: " + contact.getEmail() + "\n");
   		 }
	}

	public void createContact(Contacts contact) throws Exception {

		contactDAO.addContact(contact);

	}

	public Person addNewContact() {

			System.out.println("\n****** ADD NEW CONTACT ******\n");
			System.out.print("Enter Person Id Number: ");
			int personId = validate.numberInputValidate(getInput);

			Person personContact = personDAO.findPersonId(personId);


			return personContact;
	}

	public void updateContact() {

			try {
				System.out.println("\n****** UPDATE CONTACT ******\n");
				System.out.print("Enter Person ID Number you want to update : ");
				int personId = validate.numberInputValidate(getInput);

				Person person = personDAO.findPersonId(personId);
				listContact(person);

				System.out.println("Enter Contact Id Number you want to Update");
				System.out.print("Contact Id Number : ");
				int contactId = validate.numberInputValidate(getInput);

				System.out.println("\n******* Select Column you want to Update *******");
				System.out.println("*                                              *");
				System.out.println("* 1 : Landline 	                               *");
				System.out.println("* 2 : Mobile 	                               *");
				System.out.println("* 3 : Email 	                               *");
				System.out.println("************************************************\n");
				System.out.print("Enter Selection: ");
			
				
				Contacts contactToUpdate = contactDAO.findContactId(contactId);
				int column = validate.numberInputValidate(getInput);

				if (column > 3 || column < 1) {
					System.out.println("\nSelect only from 1 - 3!\n"); 
					
				} else {
					contactDAO.updateContact(editContact(contactToUpdate,column,getInput));
					
				}		
					
			} catch(Exception e) {
				System.out.println("\nId Number not found!!\n");
			}
	}

	public void deleteContact() {
			System.out.println("\n****** DELETE CONTACT ******\n");
			
			try {

				System.out.print("Enter Person ID Number you want to delete contact : ");
				int personId = validate.numberInputValidate(getInput);

				Person person = personDAO.findPersonId(personId);
				listContact(person);

				System.out.println("Enter Contact Id Number you want to Delete");
				System.out.print("Contact Id Number : ");
				int contactId = validate.numberInputValidate(getInput);

				contactDAO.deleteContact(contactId);
				System.out.println("\nContact " + contactId + " Successfully Deleted!");

			} catch(Exception e) {
				System.out.println("\nId Number not Exist!!\n");
			}
	}

}