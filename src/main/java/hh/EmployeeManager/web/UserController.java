package hh.EmployeeManager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hh.EmployeeManager.domain.SignUpForm;
import hh.EmployeeManager.domain.User;
import hh.EmployeeManager.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
    private UserRepository urepository; 
	
	// Login page
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	// Signup page
	@GetMapping("/signup")
    public String addUser(Model model){
    	model.addAttribute("signupform", new SignUpForm());
        return "signup";
    }	
	
	// Save user after signup
	@PostMapping("/saveuser")
	    public String save(@Validated @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
	    	if (!bindingResult.hasErrors()) { // validation errors
	    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
		    		String pwd = signupForm.getPassword();
			    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			    	String hashPwd = bc.encode(pwd);
		
			    	User newUser = new User();
			    	newUser.setPasswordHash(hashPwd);
			    	newUser.setUsername(signupForm.getUsername());
			    	newUser.setRole("USER");
			    	if (urepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
			    		urepository.save(newUser);
			    	}
			    	else {
		    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
		    			return "signup";		    		
			    	}
	    		}
	    		else {
	    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
	    			return "signup";
	    		}
	    	}
	    	else {
	    		return "signup";
	    	}
	    	return "redirect:/login";    	
	    }    
}
