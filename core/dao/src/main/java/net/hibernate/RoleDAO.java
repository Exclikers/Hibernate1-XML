package net.hibernate;

import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.apache.commons.collections4.CollectionUtils;

public class RoleDAO {

	public static void addRole(Roles role){

	 	Session session = DataAccessObject.factory.openSession();
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

	public static void updateRole(Roles role){
		 Session session = DataAccessObject.factory.openSession();
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

	public static void deleteRole(Integer roleId) {
		 Session session = DataAccessObject.factory.openSession();
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

	public static Roles findRoleId(int id) {
    	Session session = DataAccessObject.factory.openSession();
        Roles role = (Roles)session.get(Roles.class, id);
        session.close();
        return role; 
    }

    public static Set<Roles> findRole() {
    	Session session = DataAccessObject.factory.openSession();
        List<Roles> role = session.createQuery("FROM Roles").list();
        Set<Roles> roles = new LinkedHashSet<>();
		CollectionUtils.addAll(roles, role);
        session.close();
        return roles; 
    }
}