package net.hibernate;

import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.apache.commons.collections4.CollectionUtils;

public class PersonDAO {


	public static void addPerson(Person person){
		 
		Session session = DataAccessObject.factory.openSession();
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
		 Session session = DataAccessObject.factory.openSession();
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

	public static void deletePerson(Integer personId) {
		 Session session = DataAccessObject.factory.openSession();
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

	public static Person findPersonId(int id) {
    	Session session = DataAccessObject.factory.openSession();
        Person person = (Person)session.get(Person.class, id);
        session.close();
        return person; 
    }

}