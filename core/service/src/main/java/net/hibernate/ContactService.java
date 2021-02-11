package net.hibernate;

import java.util.*;

public interface ContactService {

	Scanner getInput = new Scanner(System.in);
	PersonDAO personDAO = new PersonDAO();
	ContactDAO contactDAO = new ContactDAO();
	Validate validate = new Validate();

	Contacts editContact(Contacts contact,int toEdit,Scanner scanner);
	void listContact(Person persons);
	void createContact(Contacts contact) throws Exception;
	Person addNewContact();
	void updateContact();
	void deleteContact();

}