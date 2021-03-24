package hh.EmployeeManager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
	List<Department> findByName(String name);
}
