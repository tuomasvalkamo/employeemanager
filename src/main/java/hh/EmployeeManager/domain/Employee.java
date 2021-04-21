package hh.EmployeeManager.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;
	private String firstname;
	private String lastname;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	private String ssn;
	private String streetAddress;
	private String cityAddress;
	private String zipcode;
	@ManyToOne
	@JoinColumn(name = "depId")
	private Department department;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id")
	private User user;
	
	// Constructors
	public Employee(String firstname, String lastname, LocalDate dob, String ssn, String streetAddress,
			String cityAddress, String zipcode, Department department, User user) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.ssn = ssn;
		this.streetAddress = streetAddress;
		this.cityAddress = cityAddress;
		this.zipcode = zipcode;
		this.department = department;
		this.user = user;
	}

	public Employee() {
		super();
	}

	// Getters and setters
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCityAddress() {
		return cityAddress;
	}
	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Employee [id=" + employeeId + ", firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob + ", ssn="
				+ ssn + ", streetAddress=" + streetAddress + ", cityAddress=" + cityAddress + ", zipcode=" + zipcode
				+ "]";
	}
}
