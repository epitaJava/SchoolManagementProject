package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDatabase {

	public static void main(String[] args) {
		try {		
			// "jdbc:mysql://localhost:3306/schema name"
			String sql = "jdbc:mysql://localhost:3306/testdatabase";
			String username = "root";
			String password = "1234"; // your password
			
			// Mysql driver class connector
			Class.forName("com.mysql.jdbc.Driver");

			// Connection to the Database
			Connection con = DriverManager.getConnection(sql, username, password);
			
			//Prepare the sql query to do
			PreparedStatement prep, prep1, prep2;
			
			// insert into tablename( column, column...) values (?,?...); - INSERT VALUES
			prep = con.prepareStatement("insert into sample (id, name) values(?,?)");
			prep.setString(1, "100");
			prep.setString(2, "abcd");
			
			// alter table tablename add columnname datatype - TO ADD COLUMN
			//prep1 = con.prepareStatement("alter table sample add age INT");
			
			// select * from tablename - DISPLAY DATA
			prep2 = con.prepareStatement("select * from sample");
			
			ResultSet rs = prep2.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				System.out.println(id + " " + name + " " + age);
			}
			prep.executeUpdate();
			//prep1.executeUpdate();
			prep2.execute();
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
