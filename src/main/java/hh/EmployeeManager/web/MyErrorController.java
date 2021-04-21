package hh.EmployeeManager.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController  {

	// Handle whitelabel errors
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        // Check if error 404
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "/404";
	        }
	    }
	    // If not error 404 return default error page
	    return "error";
	}
	
    @Override
    public String getErrorPath() {
        return null;
    }
}