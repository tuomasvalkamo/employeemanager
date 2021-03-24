package hh.EmployeeManager;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.EmployeeManager.domain.Department;
import hh.EmployeeManager.domain.DepartmentRepository;
import hh.EmployeeManager.domain.Employee;
import hh.EmployeeManager.domain.EmployeeRepository;

@SpringBootApplication
public class EmployeeManagerApplication {
	private static final Logger log = LoggerFactory.getLogger(EmployeeManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository repository, DepartmentRepository depRepository) {
		return (args) -> {
			depRepository.save(new Department("Sales"));
			depRepository.save(new Department("Marketing"));
			depRepository.save(new Department("Customer service"));

			
			repository.save(new Employee("Matti", "Meikäläinen", LocalDate.of(1999, 5, 25), 
					"0000-1000", "Kaivokatu 1", "Helsinki", "00100",
					depRepository.findByName("Sales").get(0)));
			repository.save(new Employee("Pekka", "Pouta", LocalDate.of(1995, 3, 15), 
					"0000-2000", "Yrityskatu 11", "Helsinki", "00200",
					depRepository.findByName("Marketing").get(0)));
			repository.save(new Employee("Liisa", "Liisanen", LocalDate.of(1992, 7, 11), 
					"0000-3000", "Liisankatu 5", "Helsinki", "00300",
					depRepository.findByName("Customer service").get(0)));
			
			log.info("fetch all employees");
			for (Employee employee : repository.findAll()) {
				log.info(employee.toString());
			}
		};
	}
}