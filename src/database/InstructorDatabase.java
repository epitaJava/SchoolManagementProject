package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import datamodel.Instructors;
import service.InstructorService;

public class InstructorDatabase {
	public void databaseConnectivity() {
		try {
			String sql = "jdbc:mysql://localhost:3306/school_management";
			String username = "root";
			String password = "1234";
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(sql, username, password);
			System.out.println("Connected DB");

			PreparedStatement prep_query, prep_read;

			InstructorService service = new InstructorService();
			List<Instructors> instructorList = service.readCSV();
			// prep_query = con.prepareStatement("create table instructor (instructor_id INT
			// NOT NULL PRIMARY KEY, name varchar(50), subject varchar(50), phone_number
			// varchar(11), email varchar(50), target_year varchar(50)");

			for (Instructors ins : instructorList) {

				prep_query = con.prepareStatement(
						"insert into instructor (instructor_id, name, subject, phone_number, email, target_year)"
								+ "values (?,?,?,?,?,?)");
				prep_query.setString(1, ins.getInstructorId());
				prep_query.setString(2, ins.getName());
				prep_query.setString(3, ins.getSubject());
				prep_query.setString(4, ins.getPhoneNumber().toString());
				prep_query.setString(5, ins.getEmail());
				prep_query.setString(6, ins.getTarget_year());
				prep_query.executeUpdate();
			}

			prep_read = con.prepareStatement("select * from instructor where target_year = ? ");
			prep_read.setString(1, "Fall 2022");
			ResultSet rs = prep_read.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("instructor_id") + " " + rs.getString("name") + " "
						+ rs.getString("subject") + " " + rs.getString("phone_number") + " " + rs.getString("email")
						+ " " + rs.getString("target_year"));
			}
			prep_read.execute();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
