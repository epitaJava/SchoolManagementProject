package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import datamodel.Students;
import service.StudentService;

public class StudentDatabase {

	public void databaseConnectivity() {
		try {
			String sql = "jdbc:mysql://localhost:3306/school_management";
			String username = "root";
			String password = "1234"; // your password
			
			// Mysql driver class connector
			Class.forName("com.mysql.jdbc.Driver");

			// Connection to the Database
			Connection con = DriverManager.getConnection(sql, username, password);
			
			//Prepare the sql query to do
			PreparedStatement prep, prep1, prep2;
			
			StudentService service = new StudentService();
			List<Students> studentsList = service.read();

			/*
			 * prep = con.
			 * prepareStatement("create table students(studId varchar(50), name varchar(50), age varchar(100), "
			 * +
			 * "course varchar(100), department varchar(50), year varchar(100), fee double, paid text)"
			 * );
			 */
			System.out.println("Created Table!");
			
			for(Students student: studentsList) { 
				prep1=con.prepareStatement("insert into students(studId, name, age, course, department, year, fee, paid)"
						+ "values(?,?,?,?,?,?,?,?)");
			 prep1.setString(1, student.getStudId());
			 prep1.setString(2, student.getName());
			 prep1.setString(3, student.getAge());
			 prep1.setString(4, student.getCourse().toString());
			 prep1.setString(5, student.getDepartment());
			 prep1.setString(6, student.getYear());
			 prep1.setDouble(7, student.getFee());
			 prep1.setString(8, student.getPaid());
			 prep1.executeUpdate();
			 }
			
			System.out.println("Inserted into Table!");
			//prep.execute();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
