package launcher;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import datamodel.Admin;
import datamodel.Course;
import service.PayService;
import datamodel.Enrollment;
import datamodel.Students;

public class Launcher {
	public static void main(String[] args) throws ParseException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Admin admin = new Admin();
		admin.setAdminId("ad123");
		admin.setPassword("EPITA2023");

		Course course = new Course();
		course.setCourseId("J2023");
		course.setCourseDuration(30);
		course.setCourseName("Java Application Development");

		Enrollment enroll = new Enrollment();
		enroll.setEnrollId(202301);
		enroll.setDate(sdf.parse("01/01/2023"));
		enroll.setDetails("Fall 2023 enrollment!");

		Students student = new Students();
		student.setStudId("F123");
		student.setName("Tom");
		student.setAge("20");
		student.setDepartment("Bachelor's of Computer Science");
		student.setYear("2023");

		List<String> courses = new ArrayList<String>();
		courses.add("Java");
		courses.add("python");
		courses.add("C++");
		student.setCourse(courses); // courses.forEach(c -> System.out.println(c));

		StudentService studentService = new StudentService();
		System.out.println(studentService.read());

		InstructorService instructorService = new InstructorService();
		System.out.println(instructorService.readCSV());

	}
}
