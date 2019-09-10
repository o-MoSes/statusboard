package rocks.monsees.statusboard;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import rocks.monsees.statusboard.model.Authority;
import rocks.monsees.statusboard.model.Employee;
import rocks.monsees.statusboard.model.Event;
import rocks.monsees.statusboard.model.Status;
import rocks.monsees.statusboard.service.EmployeeService;
import rocks.monsees.statusboard.service.StatusService;

@Component
public class DataLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeService employeeService;
	
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	ApplicationContext ctx;

	@Override
	public void run(String... strings) throws Exception {
		logger.debug("Loading app data...");
		
//		logger.debug("Fetching all bean names");
//		String[] beanNames = ctx.getBeanDefinitionNames();
//
//		for(String s : beanNames)
//			System.out.println(s);
//		logger.debug(employeeService.getAllEmployees().toString());

	
//		new Employee(position, title, name, password, available, description, authorities)
		
//		Employee master = new Employee("master", "CTO", "Peter", "peter", 
//				new HashSet<Authority>(Arrays.asList(Authority.USER, Authority.ADMIN)));
//		
//	
//		
//		master.addStatus(new Status(LocalDate.of(2019, 8, 12),LocalDate.of(2019, 10, 3),true,"status"));
//		master.addStatus(new Status(LocalDate.of(2019, 8, 10),LocalDate.of(2019, 8, 11),true,"status2"));
//		
//		
//		
//		
//		Employee slave = new Employee("slave", "CFO", "Xenia", "xenia",
//				new HashSet<Authority>(Arrays.asList(Authority.USER)));
//		
//		slave.addStatus(new Status(LocalDate.of(2019, 11, 3),LocalDate.of(2019, 11, 4),false,"status"));
//		slave.addStatus(new Status(LocalDate.of(2019, 11, 1),LocalDate.of(2019, 11, 2),false,"status2"));
//		
//		employeeService.addEmployee(master);
//		employeeService.addEmployee(slave);
		
//		employeeService.deleteEmployee("master");
//		employeeService.deleteEmployee("slave");
		
		
//		employeeService.getEmployeeByPosition("master").getStatusList().clear();
		
//		Employee master = employeeService.getEmployeeByPositionWithStatusList("master");
//		master.getStatusList().clear();
//		employeeService.updateEmployee(master);
//		
		
//		Employee master = employeeService.getEmployeeByPosition("master");
//		master.addStatus(new Status(LocalDate.of(2019, 8, 13),LocalDate.of(2019, 10, 5),true,"debug"));
//		employeeService.updateEmployee(master);
	}
}