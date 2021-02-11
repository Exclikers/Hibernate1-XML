package net.hibernate;

import java.util.*;

public interface PersonService {

	Scanner getInput = new Scanner(System.in);
	PersonDAO personDAO = new PersonDAO();
	RoleDAO roleDAO = new RoleDAO();
	Validate validate = new Validate();

	Person editPerson(Person person,int toEdit,Scanner scanner);
	void listPerson(Set<Person> persons);
	void createPerson(Person person) throws Exception;
	void deletePerson() throws Exception;
	void updatePerson() throws Exception;
	void listPerson() throws Exception;
	void addPersonRole();
	void deletePersonRole();


}