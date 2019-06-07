package rocks.monsees.statusboard.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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


		
		//check if status was already added to model by redirect
		if(!model.containsAttribute("newStatus")) {
			model.addAttribute("newStatus", new Status());
		} else {
			//add attribute to model to indicate that modal shall be shown again
			model.addAttribute("showAddStatusModal", true);
		}
		return "dashboard";
	}

	@PostMapping("addStatus")
	public String addStatus(@Valid @ModelAttribute("newStatus") Status newStatus, BindingResult result,
			RedirectAttributes ra) {

		//add command bean and binding result to redirect in case of validation error 
		if (result.hasErrors()) {
			ra.addFlashAttribute("newStatus", newStatus);
			ra.addFlashAttribute("org.springframework.validation.BindingResult.newStatus", result);
		} 
		return "redirect:/dashboard";
	}

}
