package datamodel;

import java.math.BigInteger;

public class Instructors{
	
	private String instructorId;
	private String name;
	private String subject;
	private BigInteger phoneNumber;
	private String email;
	private String target_year;
	
	public String getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public BigInteger getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(BigInteger phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTarget_year() {
		return target_year;
	}
	public void setTarget_year(String target_year) {
		this.target_year = target_year;
	}
	@Override
	public String toString() {
		return "Instructors [instructorId=" + instructorId + ", name=" + name + ", subject=" + subject
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", target_year=" + target_year + "]\n";
	}	
	
}
