package net.hibernate;

import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.apache.commons.collections4.CollectionUtils;

public class ContactDAO {


	public void addContact(Contacts contact){

	 	Session session = DataAccessObject.factory.openSession();
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

	public void updateContact(Contacts contact){
		 Session session = DataAccessObject.factory.openSession();
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

	public void deleteContact(Integer contactId) {
		 Session session = DataAccessObject.factory.openSession();
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

	public Contacts findContactId(int id) {
    	Session session = DataAccessObject.factory.openSession();
        Contacts contact = (Contacts)session.get(Contacts.class, id);
        session.close();
        return contact; 
    }

}