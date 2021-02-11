package net.hibernate;

import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.apache.commons.collections4.CollectionUtils;

public class DataAccessObject {

	public static SessionFactory factory;

   	public void databaseConnection() throws Exception {

		try {

			System.out.println("\n\nProcessing Database Connection! Please Wait.\n\n");
		 	factory = new Configuration().configure().buildSessionFactory();

		 } catch (Throwable ex) {

			System.err.println("\n\nFailed to create sessionFactory object.\n\n" + ex);
			throw new ExceptionInInitializerError(ex);
		 }

	} 

    public Set<Person> sortByGWA() {
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

    public Set<Person> sortByDateHired() {
        Session session = factory.openSession();
        List<Person> person = session.createQuery("FROM Person ORDER BY date_hired asc").list();
        Set<Person> persons = new LinkedHashSet<>();
		CollectionUtils.addAll(persons, person);
        session.close();
        return persons;
    }

    public Set<Person> sortByLastName() {
        Session session = factory.openSession();
        List<Person> person = session.createQuery("FROM Person ORDER BY last_name asc").list();
        Set<Person> persons = new LinkedHashSet<>();
		CollectionUtils.addAll(persons, person);
        session.close();
        return persons;
    }
}
