package hh.EmployeeManager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.EmployeeManager.domain.User;
import hh.EmployeeManager.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository repository;
	
	// Test data
	private User user = new User("name", "phash", "roleadmin");
	// Save test data to DB
	@BeforeEach
	public void createTestUser() {
		repository.save(user);
	}
	
	@Test
	public void createNewUser() {
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void findByIdShouldReturnUser() {
		assertThat(repository.findById(user.getId())).isNotNull();
	}
	
	@Test
	public void saveAndDeleteUserById() {
		repository.deleteById(user.getId());
		assertThat(repository.findById(user.getId())).isEmpty();
	}
}
