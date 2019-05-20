package rocks.monsees.statusboard.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rocks.monsees.statusboard.service.EmployeeService;

@Controller
public class DashboardController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/dashboard")
	public String showDashboard(Model model, Principal principal) {
		logger.debug("Trying to fetch dashboard.jsp");
		model.addAttribute("employeeName", principal.getName());
		model.addAttribute("statusList", employeeService.getEmployeeByPosition(principal.getName()).getStatusList());
		return "dashboard";
	}

}
