package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// Create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml") // Default name of file. Not necessary to include here.
																// Must be in class path though!
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
	
		try {
			// Create student object
			Student newStudent = new Student("John", "DiVirgilio", "anyone@anywhere.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the obj to the db
			session.save(newStudent);
			
			// commit it
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
		
	}

}
