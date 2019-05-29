package rocks.monsees.statusboard.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rocks.monsees.statusboard.model.Status;
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
		model.addAttribute("newStatus", new Status());
		return "dashboard";
	}

	@PostMapping("addStatus")
	public ModelAndView addStatus(@ModelAttribute("newStatus") Status newStatus, RedirectAttributes redirectAttrs) {
		System.out.println(newStatus);
		return new ModelAndView("redirect:/dashboard");
	}

}
