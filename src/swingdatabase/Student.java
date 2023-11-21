package swingdatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class Student {
	public static void main(String[] args) {
		JFrame f = new JFrame("Student Form");
		JLabel studentId, studentName, studentAge, courseList, studDepart, studYear, studFee, isPaid;
		final JTextField studentIdField = new JTextField();
		final JTextField studentNameField = new JTextField();
		final JTextField studentFeeField = new JTextField();
		String[] courses = { "Java;Python;C++", "Cryptography;Cyber threats;Networks and Security",
				"Python;NLP;Statistics" };
		JComboBox<String> studentcourseField = new JComboBox<>(courses);
		String[] departments = { "Bachelor's of Computer science", "Bachelor's of Artificial Intelligence",
				"Bachelor's of Cyber Security" };
		JComboBox<String> studentDepartField = new JComboBox<>(departments);
		JCheckBox studentYearField1 = new JCheckBox("Fall 2023");
		JCheckBox studentYearField2 = new JCheckBox("Fall 2022");

		studentId = new JLabel("Student Id");
		// objectname.setBounds(x axis, y axis, width, height)
		studentId.setBounds(50, 50, 100, 30);
		studentIdField.setBounds(140, 50, 100, 30);

		studentName = new JLabel("Student Name");
		studentName.setBounds(50, 100, 100, 30); //
		studentNameField.setBounds(140, 100, 100, 30);

		studentAge = new JLabel("Student Age");
		studentAge.setBounds(50, 150, 100, 30);
		SpinnerModel model = new SpinnerNumberModel(19, // valeur initiale
				18, // valeur minimum
				30, // valeur maximum
				1 // pas
		);
		JSpinner studentAgeField = new JSpinner(model);
		studentAgeField.setBounds(140, 150, 100, 30);

		courseList = new JLabel("Course List");
		courseList.setBounds(50, 200, 100, 30);
		studentcourseField.setBounds(140, 200, 200, 30);
		studentcourseField.setVisible(true);

		studDepart = new JLabel("Department");
		studDepart.setBounds(50, 250, 100, 30);
		studentDepartField.setBounds(140, 250, 200, 30);
		studentDepartField.setVisible(true);

		studYear = new JLabel("Year");
		studYear.setBounds(50, 300, 100, 30);
		studentYearField1.setBounds(140, 300, 200, 30);
		studentYearField2.setBounds(140, 330, 200, 30);

		studFee = new JLabel("Fee");
		studFee.setBounds(50, 380, 100, 30);
		studentFeeField.setBounds(140, 380, 200, 30);

		isPaid = new JLabel("Fee Paid");
		isPaid.setBounds(50, 420, 100, 30);
		JRadioButton paidYes = new JRadioButton("Yes");
		JRadioButton paidNo = new JRadioButton("No");
		paidYes.setBounds(140, 420, 200, 30);
		paidNo.setBounds(140, 450, 200, 30);
		ButtonGroup bg = new ButtonGroup();
		bg.add(paidYes);
		bg.add(paidNo);
		f.add(paidYes);
		f.add(paidNo);

		JButton b = new JButton("Register");
		b.setBounds(100, 500, 100, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String studId = studentIdField.getText();
				String name = studentNameField.getText();
				String age = studentAgeField.getValue().toString();
				String course = (String) studentcourseField.getSelectedItem();
				String department = (String) studentDepartField.getSelectedItem();
				String year = ((studentYearField1.isSelected() && !studentYearField2.isSelected() ? "Fall 2023"
						: "Fall 2022"));
				Double fee = Double.parseDouble(studentFeeField.getText());
				String paid = ((paidYes.isSelected() && !paidNo.isSelected()) ? "Yes" : "No");

				addStudent(studId, name, age, course, department, year, fee, paid);
				JOptionPane.showMessageDialog(b, "Registered");
			}

		});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(b);
		f.add(studentIdField);
		f.add(studentNameField);
		f.add(studentAgeField);
		f.add(studentcourseField);
		f.add(studentDepartField);
		f.add(studentYearField1);
		f.add(studentYearField2);
		f.add(studentFeeField);
		f.add(isPaid);
		f.add(studentId);
		f.add(studentName);
		f.add(studentAge);
		f.add(courseList);
		f.add(studDepart);
		f.add(studFee);
		f.add(paidYes);
		f.add(paidNo);
		f.add(studYear);
		f.setSize(600, 600);
		f.setLayout(null);
		f.setVisible(true);
	}

	private static void addStudent(String studId, String name, String age, String course, String department,
			String year, Double fee, String paid) {
		try {
			String url = "jdbc:mysql://localhost:3306/school_management";
			String user = "root";
			String password = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connected DB");

			// Prepare the sql query to do
			PreparedStatement prep = connection.prepareStatement("insert into students(studId, name, age, course, department, year, fee, paid)"
							+ "values(?,?,?,?,?,?,?,?)");
			prep.setString(1, studId);
			prep.setString(2, name);
			prep.setString(3, age);
			prep.setString(4, course);
			prep.setString(5, department);
			prep.setString(6, year);
			prep.setDouble(7, fee);
			prep.setString(8, paid);
			prep.executeUpdate();
			System.out.println("Inserted successfully");

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}

}
