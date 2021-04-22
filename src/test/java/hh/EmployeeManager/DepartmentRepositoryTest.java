package hh.EmployeeManager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.EmployeeManager.domain.Department;
import hh.EmployeeManager.domain.DepartmentRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DepartmentRepositoryTest {
	@Autowired
	private DepartmentRepository repository;
	
	@Test
	public void createNewDepartment() {
		Department dep = new Department("Sales");
		repository.save(dep);
		assertThat(dep.getDepId()).isNotNull();
	}
	
	@Test
	public void findByIdShouldReturnDepartment() {
		Department dep = new Department("Sales");
		repository.save(dep);
		assertThat(repository.findById(dep.getDepId())).isNotNull();
	}
	
	@Test
	public void saveAndDeleteDepartmentById() {
		Department dep = new Department("Sales");
		repository.save(dep);
		repository.deleteById(dep.getDepId());
		assertThat(repository.findById(dep.getDepId())).isEmpty();
	}
}
