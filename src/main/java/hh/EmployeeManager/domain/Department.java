package hh.EmployeeManager.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long depId;
	private String name;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> employees;
	
	// Constructors
	public Department(String name) {
		super();
		this.name = name;
	}
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
	public Long getDepId() {
		return depId;
	}
	public void setDepId(Long depId) {
		this.depId = depId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	@Override
	public String toString() {
		return "Department [depId=" + depId + ", name=" + name + ", employees=" + employees + "]";
	}
}
