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
	public String showDashboard(@ModelAttribute("newStatus") Status newStatus, Model model, Principal principal) {
		logger.debug("Trying to fetch dashboard.jsp");
		model.addAttribute("employeeName", principal.getName());
		model.addAttribute("statusList", employeeService.getEmployeeByPosition(principal.getName()).getStatusList());

		if (null == newStatus) {
			model.addAttribute("newStatus", new Status());
		} else {
			//js to show modal on attribute value not implemented
			model.addAttribute("showAddStatusModal", true);
			model.addAttribute("newStatus", newStatus);
		}

		return "dashboard";
	}

	@PostMapping("addStatus")
	public ModelAndView addStatus(@Valid @ModelAttribute("newStatus") Status newStatus, BindingResult bindingResult,
			RedirectAttributes ra) {

		if (bindingResult.hasErrors()) {
			System.out.println("Fehler:" + newStatus);
			ra.addFlashAttribute("newStatus", newStatus);
		} else {
			System.out.println("kein Fehler:" + newStatus);
		}
		return new ModelAndView("redirect:/dashboard");
	}

}
