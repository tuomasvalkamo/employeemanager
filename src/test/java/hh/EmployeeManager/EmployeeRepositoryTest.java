package hh.EmployeeManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.EmployeeManager.domain.Department;
import hh.EmployeeManager.domain.Employee;
import hh.EmployeeManager.domain.EmployeeRepository;
import hh.EmployeeManager.domain.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmployeeRepositoryTest {
	@Autowired
	private EmployeeRepository repository;
	
	// User for test cases
	private User testUser = new User("username", "password", "USER");
	
	@Test
	public void createNewEmployee() {
		Employee employee = new Employee("Matti", "Meikäläinen", LocalDate.of(1999, 5, 25), "0000-1111", 
				"Kaivokatu 1", "Helsinki", "00100", new Department("Marketing"), testUser);
		repository.save(employee);
		assertThat(employee.getEmployeeId()).isNotNull();
	}
	
	@Test
	public void findByIdShouldReturnEmployee() {
		Employee employee = new Employee("Matti", "Meikäläinen", LocalDate.of(1999, 5, 25), "0000-1111", 
				"Kaivokatu 1", "Helsinki", "00100", new Department("Marketing"), testUser);
		repository.save(employee);
		assertThat(repository.findById(employee.getEmployeeId())).isNotNull();
	}
	
	@Test
	public void saveAndDeleteEmployeeById() {
		Employee employee = new Employee("Matti", "Meikäläinen", LocalDate.of(1999, 5, 25), "0000-1111", 
				"Kaivokatu 1", "Helsinki", "00100", new Department("Marketing"), testUser);
		repository.save(employee);
		repository.deleteById(employee.getEmployeeId());
		assertThat(repository.findById(employee.getEmployeeId())).isEmpty();
	}
}
