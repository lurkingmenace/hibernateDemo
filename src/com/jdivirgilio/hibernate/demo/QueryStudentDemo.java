package com.jdivirgilio.hibernate.demo;

import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Student;

public class QueryStudentDemo {

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
			// Different query techniques
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> students = session.createQuery("from Student").getResultList();
			displayQuery("from Student");
			displayStudents(students);

			students = session.createQuery("from Student s where s.lastName='Jane'").getResultList();
			displayQuery("from Student s where s.lastName='Jane'");
			displayStudents(students);
			
			students = session
						.createQuery("from Student s where s.lastName='DiVirgilio' OR firstName='Apple'")
						.getResultList();
			displayQuery("from Student s where s.lastName='DiVirgilio' OR firstName='Apple'");
			displayStudents(students);
			
			students = session.createQuery("from Student s where s.email LIKE '%anywhere.com'").getResultList();
			displayQuery("from Student where s.email LIKE '%anywhere.com'");
			displayStudents(students);
			
			session.getTransaction().commit();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		students.stream().forEach((s) -> System.out.println(s));
		System.out.println();
	}

	private static void displayQuery(String s) {
		System.out.println("\nQuery: " + s);
	}
}
