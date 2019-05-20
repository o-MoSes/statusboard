package rocks.monsees.statusboard;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import rocks.monsees.statusboard.model.Authority;
import rocks.monsees.statusboard.model.Employee;
import rocks.monsees.statusboard.model.Status;
import rocks.monsees.statusboard.service.EmployeeService;

@Component
public class DataLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeService employeeService;

	@Override

	public void run(String... strings) throws Exception {
		logger.debug("Loading app data...");
//		logger.debug(employeeService.getAllEmployees().toString());

	
//		new Employee(position, title, name, password, available, description, authorities)
		
//		Employee master = new Employee("master", "CTO", "Peter", "peter", 
//				new HashSet<Authority>(Arrays.asList(Authority.USER, Authority.ADMIN)));
//		
//		master.addStatus(new Status(LocalDate.of(2019, 5, 12),LocalDate.of(2019, 10, 3),true,"status"));
//		master.addStatus(new Status(LocalDate.of(2019, 5, 10),LocalDate.of(2019, 5, 11),true,"status2"));
//		
//		
//		
//		
//		Employee slave = new Employee("slave", "CFO", "Xenia", "xenia",
//				new HashSet<Authority>(Arrays.asList(Authority.USER)));
//		
//		slave.addStatus(new Status(LocalDate.of(2019, 4, 3),LocalDate.of(2019, 4, 4),false,"status"));
//		slave.addStatus(new Status(LocalDate.of(2019, 4, 1),LocalDate.of(2019, 4, 2),false,"status2"));
//		
//		employeeService.addEmployee(master);
//		employeeService.addEmployee(slave);
		
		
//		employeeService.deleteEmployee("master");
//		employeeService.deleteEmployee("slave");
	}
}