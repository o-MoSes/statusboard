package rocks.monsees.statusboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import rocks.monsees.statusboard.exception.EmployeeExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@ExceptionHandler(Exception.class)
	public String handleExceptions(HttpServletRequest request, Exception ex) {
		logger.info("Exception raised on URL: "+request.getRequestURL());
		logger.info("Exception raised: "+ex);
		
		
		if(ex instanceof EmployeeExistsException) {
			return "lalala";
		}
		
		return "error";
	}
	
	
	
}
