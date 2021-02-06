package net.hibernate;

import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.apache.commons.collections4.CollectionUtils;

public class DataAccessObject {

	private static SessionFactory factory;

   	public static void databaseConnection() throws Exception {

		try {

			System.out.println("\n\nProcessing Database Connection! Please Wait.\n\n");
		 	factory = new Configuration().configure().buildSessionFactory();

		 } catch (Throwable ex) {

			System.err.println("\n\nFailed to create sessionFactory object.\n\n" + ex);
			throw new ExceptionInInitializerError(ex);
		 }

	} 

	public static void addContact(Contacts contact){

	 	Session session = factory.openSession();
		Transaction tx = null;
 
		 try{
			 tx = session.beginTransaction();
			 session.save(contact);
			 tx.commit();

		 } catch (HibernateException e) {
			 if (tx!=null) tx.rollback();
			 e.printStackTrace();
			 
		 }finally {
			 
		 session.close();
		 System.out.println("\n\nSuccessfully Added New Contact!\n\n");
		 }

	}

	public static void addRole(Roles role){

	 	Session session = factory.openSession();
		Transaction tx = null;
 
		 try{
			 tx = session.beginTransaction();
			 session.save(role);
			 tx.commit();

		 } catch (HibernateException e) {
			 if (tx!=null) tx.rollback();
			 e.printStackTrace();
			 
		 }finally {
			 
		 session.close();
		 System.out.println("\n\nSuccessfully Added New Role!\n\n");
		 }

	}
	 		
	public static void addPerson(Person person){
		 
		Session session = factory.openSession();
		Transaction tx = null;
 
		 try{
			 tx = session.beginTransaction();
			 session.save(person);
			 tx.commit();
			 
		 } catch (HibernateException e) {
			 if (tx!=null) tx.rollback();
			 e.printStackTrace();
			 
		 }finally {
			 
		 session.close();
		 System.out.println("\n\nPerson Created!\n\n");
		 }
	}
	
	public static void updatePerson(Person person){
		 Session session = factory.openSession();
		 Transaction tx = null;
		 try{
			 tx = session.beginTransaction();

			 session.update(person);

			 tx.commit();
		 }catch (HibernateException e) {
			 if (tx!=null) tx.rollback();
			 e.printStackTrace();
		 }finally {

		 	System.out.println("\nUpdated Successfully\n");
			session.close();
		 }
	}

	public static void updateContact(Contacts contact){
		 Session session = factory.openSession();
		 Transaction tx = null;
		 try{
			 tx = session.beginTransaction();

			 session.update(contact);

			 tx.commit();
		 }catch (HibernateException e) {
			 if (tx!=null) tx.rollback();
			 e.printStackTrace();
		 }finally {

		 	System.out.println("\nUpdated Successfully\n");
			session.close();
		 }
	}
	 

	public static void updateRole(Roles role){
		 Session session = factory.openSession();
		 Transaction tx = null;
		 try{
			 tx = session.beginTransaction();

			 session.update(role);

			 tx.commit();
		 }catch (HibernateException e) {
			 if (tx!=null) tx.rollback();
			 e.printStackTrace();
		 }finally {

		 	System.out.println("\nUpdated Successfully\n");
			session.close();
		 }
	}

	public static void deletePerson(Integer personId) {
		 Session session = factory.openSession();
		 Transaction tx = null;
	
		 try{
			 tx = session.beginTransaction();
			 Person person = (Person)session.get(Person.class, personId);
			 session.delete(person);
			 tx.commit();
		 }catch (HibernateException e) {
			 if (tx!=null) tx.rollback();
			 e.printStackTrace();
		 }finally {
			 session.close();
		 }
	}

	public static void deleteRole(Integer roleId) {
		 Session session = factory.openSession();
		 Transaction tx = null;
	
		 try{
			 tx = session.beginTransaction();
			 Roles role = (Roles)session.get(Roles.class, roleId);
			 session.delete(role);
			 tx.commit();
		 }catch (HibernateException e) {
			 if (tx!=null) tx.rollback();
			 e.printStackTrace();
		 }finally {
			 session.close();
		 }
	}

	public static void deleteContact(Integer contactId) {
		 Session session = factory.openSession();
		 Transaction tx = null;
	
		 try{
			 tx = session.beginTransaction();
			 Contacts contact = (Contacts)session.get(Contacts.class, contactId);
			 session.delete(contact);
			 tx.commit();
		 }catch (HibernateException e) {
			 if (tx!=null) tx.rollback();
			 e.printStackTrace();
		 }finally {
			 session.close();
		 }
	}

    public static Person findPersonId(int id) {
    	Session session = factory.openSession();
        Person person = (Person)session.get(Person.class, id);
        session.close();
        return person; 
    }

    public static Contacts findContactId(int id) {
    	Session session = factory.openSession();
        Contacts contact = (Contacts)session.get(Contacts.class, id);
        session.close();
        return contact; 
    }

    public static Roles findRoleId(int id) {
    	Session session = factory.openSession();
        Roles role = (Roles)session.get(Roles.class, id);
        session.close();
        return role; 
    }

    public static Set<Roles> findRole() {
    	Session session = factory.openSession();
        List<Roles> role = session.createQuery("FROM Roles").list();
        Set<Roles> roles = new LinkedHashSet<>();
		CollectionUtils.addAll(roles, role);
        session.close();
        return roles; 
    }

    public static Set<Person> sortByGWA() {
        Session session = factory.openSession();
		List<Person> person = session.createQuery("FROM Person").list();
		Collections.sort(person, new Comparator<Person>() {
			@Override
			public int compare(Person person1, Person person2) {
				return Double.compare(person1.getGradeWeightedAverage(), person2.getGradeWeightedAverage());
			}
		});

		Set<Person> persons = new LinkedHashSet<>();
		CollectionUtils.addAll(persons, person);
        session.close();
        return persons;
    }

    public static Set<Person> sortByDateHired() {
        Session session = factory.openSession();
        List<Person> person = session.createQuery("FROM Person ORDER BY date_hired asc").list();
        Set<Person> persons = new LinkedHashSet<>();
		CollectionUtils.addAll(persons, person);
        session.close();
        return persons;
    }

    public static Set<Person> sortByLastName() {
        Session session = factory.openSession();
        List<Person> person = session.createQuery("FROM Person ORDER BY last_name asc").list();
        Set<Person> persons = new LinkedHashSet<>();
		CollectionUtils.addAll(persons, person);
        session.close();
        return persons;
    }
}
