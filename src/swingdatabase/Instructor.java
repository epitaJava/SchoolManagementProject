package swingdatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Instructor {

	public static void main(String[] args) {

		JFrame f = new JFrame("Instructor Form");
		JLabel instructorId, name, subject, email, phonenumber, targetyear;
		final JTextField idfield = new JTextField();
		final JTextField namefield = new JTextField();
		String[] subjects = { "Java Application Devlopment", "Analytics", "French", "Python", "C++", "Database",
				"Web development" };
		JComboBox<String> subjectfield = new JComboBox<String>(subjects);
		final JTextField numberfield = new JTextField();
		final JTextField emailfield = new JTextField();

		JCheckBox targetYearField1 = new JCheckBox("Fall 2023");
		JCheckBox targetYearField2 = new JCheckBox("Fall 2022");

		instructorId = new JLabel("Instructor Id:");
		instructorId.setBounds(50, 50, 100, 30);
		idfield.setBounds(140, 50, 100, 30);

		name = new JLabel("Name:");
		name.setBounds(50, 100, 100, 30);
		namefield.setBounds(140, 100, 100, 30);

		subject = new JLabel("Subject:");
		subject.setBounds(50, 150, 100, 30);
		subjectfield.setBounds(140, 150, 200, 30);

		email = new JLabel("Email:");
		email.setBounds(50, 200, 100, 30);
		emailfield.setBounds(140, 200, 160, 30);

		phonenumber = new JLabel("Telephone:");
		phonenumber.setBounds(50, 250, 100, 30);
		numberfield.setBounds(140, 250, 130, 30);

		targetyear = new JLabel("Target year:");
		targetyear.setBounds(50, 300, 100, 30);
		targetYearField1.setBounds(140, 300, 100, 30);
		targetYearField2.setBounds(140, 330, 100, 30);
		

		JButton b = new JButton("Register");
		b.setBounds(100, 500, 100, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String instructorId = idfield.getText();
				String name = namefield.getText();
				String subject = (String) subjectfield.getSelectedItem();
				String email = emailfield.getText();
				String phone_number = numberfield.getText();
				String targetyear = ((targetYearField1.isSelected() && !targetYearField2.isSelected() ? "Fall 2023"
						: "Fall 2022"));
				addInstructor(instructorId, name, subject, email, phone_number, targetyear);
				JOptionPane.showMessageDialog(b, "Registered");
			}

		});
		
		f.add(instructorId);
		f.add(name);
		f.add(subject);
		f.add(email);
		f.add(phonenumber);
		f.add(targetyear);
		f.add(idfield);
		f.add(namefield);
		f.add(subjectfield);
		f.add(emailfield);
		f.add(numberfield);
		f.add(targetYearField1);
		f.add(targetYearField2);
		f.add(b);
		f.setSize(600, 600);
		f.setLayout(null);
		f.setVisible(true);

	}

	public static void addInstructor(String instructor_id, String name, String subject, String email,
			String phone_number, String target_year) {
		try {
			String url = "jdbc:mysql://localhost:3306/school_management";
			String user = "root";
			String password = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connected DB");

			// Prepare the sql query to do
			PreparedStatement prep = connection.prepareStatement("insert into instructor(instructor_id,"
					+ " name,subject,email, phone_number, target_year) values(?,?,?,?,?,?)");

			prep.setString(1, instructor_id);
			prep.setString(2, name);
			prep.setString(3, subject);
			prep.setString(4, email);
			prep.setString(5, phone_number);
			prep.setString(6, target_year);
			prep.executeUpdate();
			
			System.out.println("Inserted into DB");

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
