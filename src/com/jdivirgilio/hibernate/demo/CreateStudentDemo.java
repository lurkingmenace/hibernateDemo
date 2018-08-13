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
			// Session can only be used on one item.
			Student newStudent = new Student("John", "DiVirgilio", "anyone@anywhere.com");
			session.beginTransaction();
			session.save(newStudent);
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			newStudent = new Student("Mary", "Jane", "applebottom@anywhere.com");
			session.beginTransaction();
			session.save(newStudent);
			session.getTransaction().commit();

			session = factory.getCurrentSession();
			newStudent = new Student("Apple", "Bottom", "applebottom@anywhere.com");
			session.beginTransaction();
			session.save(newStudent);
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
		
	}

}
