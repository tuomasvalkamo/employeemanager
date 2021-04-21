package hh.EmployeeManager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.EmployeeManager.domain.DepartmentRepository;
import hh.EmployeeManager.domain.Employee;
import hh.EmployeeManager.domain.EmployeeRepository;
import hh.EmployeeManager.domain.User;
import hh.EmployeeManager.domain.UserRepository;

@Controller
public class EmployeeController {
	// Get repositories
	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired
	private DepartmentRepository depRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// Map pages
	
	// Employee list page
	@GetMapping({ "/", "/employees"} )
	public String employeeList(Model model) {
		// Get current user
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		User curUser = userRepository.findByUsername(username);
		
		model.addAttribute("employees", empRepository.findByUser(curUser));
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
		// Get current user
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		User curUser = userRepository.findByUsername(username);
		// Tie current user to saved data
		employee.setUser(curUser);
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
	
	// Login page
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	// RESTful service to get all employees
	
	// RESTful service to employee by id
}
