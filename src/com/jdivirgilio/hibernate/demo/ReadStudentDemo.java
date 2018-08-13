package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml") // Default name of file. Not
																					// necessary to include here.
																					// Must be in class path though!
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		session.createSQLQuery("truncate table student").executeUpdate();
		session.getTransaction().commit();

		Student newStudent = new Student("John", "DiVirgilio", "anyone@anywhere.com");

		// Session can only be used on one item.
		session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(newStudent);
		session.getTransaction().commit();
		System.out.println(newStudent);

		session = factory.getCurrentSession();
		newStudent = new Student("Mary", "Jane", "applebottom@anywhere.com");
		session.beginTransaction();
		session.save(newStudent);
		session.getTransaction().commit();
		System.out.println(newStudent);

		session = factory.getCurrentSession();
		newStudent = new Student("Apple", "Bottom", "applebottom@anywhere.com");
		session.beginTransaction();
		session.save(newStudent);
		session.getTransaction().commit();
		System.out.println(newStudent);

		// Now retrieve another student
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Student retrievedStudent = session.get(Student.class, newStudent.getId());
			session.getTransaction().commit();
			System.out.println("Retreived student PK: " + retrievedStudent);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

}
