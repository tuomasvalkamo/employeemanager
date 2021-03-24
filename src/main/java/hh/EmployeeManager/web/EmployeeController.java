package hh.EmployeeManager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.EmployeeManager.domain.DepartmentRepository;
import hh.EmployeeManager.domain.Employee;
import hh.EmployeeManager.domain.EmployeeRepository;

@Controller
public class EmployeeController {
	// Get repositories
	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired
	private DepartmentRepository depRepository;
	
	// Map pages
	
	// Employee list page
	@GetMapping({ "/", "/employees"} )
	public String employeeList(Model model) {
		model.addAttribute("employees", empRepository.findAll());
		return "employees";
	}
	
	// Add employee page
	@GetMapping("/addemployee")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("departments", depRepository.findAll());
		return "addemployee";
	}
	
	// Save new employee
	@PostMapping("/save")
	public String save(Employee employee) {
		System.out.println(employee.toString());
		empRepository.save(employee);
		return "redirect:employees";
	}
	
	// Delete existing employee
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Long empId, Model model) {
		empRepository.deleteById(empId);

		return "redirect:../employees";
	}
	
	// Edit existing employee
	@GetMapping("/edit/{id}")
	public String editEmployee(@PathVariable("id") Long empId, Model model) {
		model.addAttribute("employee", empRepository.findById(empId));
		model.addAttribute("departments", depRepository.findAll());
		return "editemployee";
	}
	
	// RESTful service to get all employees
	
	// RESTful service to employee by id
}
