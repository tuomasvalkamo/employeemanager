package hh.EmployeeManager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.EmployeeManager.domain.User;
import hh.EmployeeManager.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository repository;
	
	@Test
	public void createNewUser() {
		User user = new User("name", "phash", "roleadmin");
		repository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void findByIdShouldReturnUser() {
		User user = new User("name", "phash", "roleadmin");
		repository.save(user);
		assertThat(repository.findById(user.getId())).isNotNull();
	}
	
	@Test
	public void saveAndDeleteUserById() {
		User user = new User("name", "phash", "roleadmin");
		repository.save(user);
		repository.deleteById(user.getId());
		assertThat(repository.findById(user.getId())).isEmpty();
	}
}
