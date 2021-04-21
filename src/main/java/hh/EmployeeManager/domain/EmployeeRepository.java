package hh.EmployeeManager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findByUser(User user);
}
