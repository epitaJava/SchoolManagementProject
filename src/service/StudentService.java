package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import datamodel.Students;

public class StudentService {

	public List<Students> read() throws IOException {
		String line = null;
		String splitBy = ",";
		BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\earuvai\\Downloads\\FileFolder\\students.csv"));
		List<Students> studentList = new ArrayList<>();
		int count = 0;
		while ((line = br.readLine()) != null) { // This assignment check will help you avoid nullexception
			if (count != 0) { // Helps to keep count of the rows from the CSV file
				String[] students = line.split(splitBy);
				Students student = new Students();

				student.setStudId(students[0].trim());
				student.setName(students[1].trim());
				student.setDepartment(students[4].trim());

				// Integer age = Integer.valueOf(students[4].trim());
				// age = ((age) >= 19) && !age.equals(0) ? age : -1;
				student.setAge(students[2].trim()); // Parse it to String after checking it with values.

				student.setYear(students[5].trim());
				student.setFee(Double.parseDouble(students[6].trim())); // Parse it to double because the list
																		// attributes are string.

				String CSV = students[3].trim();
				String[] values = CSV.split(";");
				student.setCourse(Arrays.asList(values)); // Course is sub-list of array inside the student List.

				student.setPaid(students[7].trim());

				studentList.add(student); // Add each student data values to the studentList
			}
			count++;
		}
		//sortByStudentYear(studentList);
		br.close();

		return studentList;
	}

	public void sortByStudentYear(List<Students> studs) throws IOException { //
		studs.sort((p1, p2) -> p1.getYear().compareTo(p2.getYear())); //
		System.out.println(studs);

		Collections.sort(studs, (s1, s2) -> Integer.valueOf(s1.getAge()).compareTo(Integer.valueOf(s2.getAge())));
		System.out.println(studs);
	}

}
