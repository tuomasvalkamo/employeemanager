package hh.EmployeeManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.EmployeeManager.domain.Department;
import hh.EmployeeManager.domain.DepartmentRepository;
import hh.EmployeeManager.domain.Employee;
import hh.EmployeeManager.domain.EmployeeRepository;
import hh.EmployeeManager.domain.User;
import hh.EmployeeManager.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeRepositoryTest {
	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private UserRepository urepository;
	@Autowired
	private DepartmentRepository drepository;
	
	// Create test data
	private User testUser = new User("username", "password", "USER");
	private Department department = new Department("Sales");
	// Save test data to DB
	@BeforeEach
	public void stageData() {
		urepository.save(testUser);
		drepository.save(department);
	}
	
	@Test
	public void createNewEmployee() {
		Employee employee = new Employee("Matti", "Meikäläinen", LocalDate.of(1999, 5, 25), "0000-1111", 
				"Kaivokatu 1", "Helsinki", "00100", department, testUser);
		repository.save(employee);
		assertThat(employee.getEmployeeId()).isNotNull();
	}
	
	@Test
	public void findByIdShouldReturnEmployee() {
		Employee employee = new Employee("Matti", "Meikäläinen", LocalDate.of(1999, 5, 25), "0000-1111", 
				"Kaivokatu 1", "Helsinki", "00100", department, testUser);
		repository.save(employee);
		assertThat(repository.findById(employee.getEmployeeId())).isNotNull();
	}
	
	@Test
	public void saveAndDeleteEmployeeById() {
		Employee employee = new Employee("Matti", "Meikäläinen", LocalDate.of(1999, 5, 25), "0000-1111", 
				"Kaivokatu 1", "Helsinki", "00100", department, testUser);
		repository.save(employee);
		repository.deleteById(employee.getEmployeeId());
		assertThat(repository.findById(employee.getEmployeeId())).isEmpty();
	}
}
