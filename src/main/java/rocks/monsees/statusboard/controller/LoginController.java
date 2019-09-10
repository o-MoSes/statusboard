package rocks.monsees.statusboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rocks.monsees.statusboard.service.EmployeeService;

@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("message", "statusboard");
//		model.addAttribute("employees", employeeService.getAllEmployees());
		model.addAttribute("employees", employeeService.getAllEmployeesWithStatusList());
		return "index";
	}

	// this method is called by spring security and the url path name is analysed by
	// js to show login modal
	@GetMapping("/login")
	public String getIndexWithLoginModal(Model model) {
		model.addAttribute("message", "statusboard");
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "index";
	}
}
