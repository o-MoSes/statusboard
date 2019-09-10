package rocks.monsees.statusboard.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rocks.monsees.statusboard.model.Employee;
import rocks.monsees.statusboard.model.Status;
import rocks.monsees.statusboard.service.EmployeeService;
import rocks.monsees.statusboard.service.StatusService;

@Controller
public class DashboardController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EmployeeService employeeService;

	@Autowired
	StatusService statusService;

	@GetMapping("/dashboard")
	public String showDashboard(Model model, Principal principal) {
		model.addAttribute("employeeName", principal.getName());
		model.addAttribute("statusList", employeeService.getEmployeeByPositionWithStatusList(principal.getName()).getStatusList());
		

		/*
		 * check if status was already added to model by redirect because of validation
		 * error- if model contains none then add one as form backing bean
		 */
		if (!model.containsAttribute("newStatus")) {
			model.addAttribute("newStatus", new Status());
		} else {
			// add attribute to model to indicate that modal shall be shown again
			model.addAttribute("showAddStatusModal", true);
		}
		return "dashboard";
	}

	@PostMapping("addStatus")
	public String addStatus(@Valid @ModelAttribute("newStatus") Status newStatus, BindingResult result,
			RedirectAttributes ra, Principal principal) {

		String employeePosition= principal.getName();
		
		/* add command bean and statusExists attribute if new status conflicts with existing status. 
		cant be done via Constraint because the status is not bound to a Employee on validation time */
		if(employeeService.statusForThisPeriodExists(employeePosition,newStatus)){
			ra.addFlashAttribute("newStatus", newStatus);
			ra.addFlashAttribute("statusExists", new String("Oops, you already set a status for this period"));
		}
		// add command bean and binding result to redirect in case of validation error
		else if (result.hasErrors()) {
			ra.addFlashAttribute("newStatus", newStatus);
			ra.addFlashAttribute("org.springframework.validation.BindingResult.newStatus", result);
		} 
		else{

			// not saving new status before adding it to employee results in
			// org.hibernate.LazyInitializationException
//			statusService.saveStatus(newStatus);
//			employeeService.addStatusForEmployee(principal.getName(), newStatus);
			
//			Employee employee = employeeService.getEmployeeByPosition(principal.getName()); //causes org.hibernate.LazyInitializationException when calling employee.addStatus(new Status), because assocation wasnt initialized
			Employee employee = employeeService.getEmployeeByPositionWithStatusList(employeePosition); //this will initialize the child and prevent lazy Exception
	        employee.addStatus(newStatus); //hier entsteht der LazyFehler!!!!!!
//			List<Status> statusLi1 = new ArrayList<Status>();
//			statusLi1.add(new Status(LocalDate.of(2019, 8, 10),LocalDate.of(2019, 8, 11),true,"Cascade Test"));
//			employee.setStatusList(statusLi1); // this works and will overwrite the current statuslist when calling updateEmployee  if CascadeTyppe.SAVE is set 
	        employeeService.updateEmployee(employee); // 
			
		}

		return "redirect:/dashboard";
	}
	
	@GetMapping("delete/status")
	public String deleteStatus(@RequestParam int id, Principal principal) {
		statusService.deleteStatus(id);
		return "redirect:/dashboard?statusDeleted"; //trigger toast 
	}
	
	
	
	

}
