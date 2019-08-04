package rocks.monsees.statusboard.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rocks.monsees.statusboard.exception.EmployeeExistsException;
import rocks.monsees.statusboard.model.Employee;
import rocks.monsees.statusboard.model.Status;
import rocks.monsees.statusboard.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Employee getEmployeeById(int id) {
		logger.debug("Trying to fetch employee with id: " + id);
		return employeeRepo.findById(id).get();
	}

	public Employee getEmployeeByPosition(String position) {
		logger.debug("Trying to fetch employee with position: " + position);
		return employeeRepo.findByPosition(position);
	}

	public List<Employee> getAllEmployees() {
		logger.debug("Trying to fetch all employees");
		List<Employee> employeeList = new ArrayList<>();
		employeeRepo.findAll().forEach(e -> employeeList.add(e));
		return employeeList;
	}

	public void addEmployee(Employee employee) throws EmployeeExistsException {
		logger.debug("Trying to save employee: " + employee);

		String position = employee.getPosition();

		if (null == getEmployeeByPosition(position)) {
			String unencodedPassword = employee.getPassword();
			employee.setPassword(passwordEncoder.encode(unencodedPassword));
			employeeRepo.save(employee);
		} else {
			throw new EmployeeExistsException(position);
		}
	}

	public void deleteEmployee(int id) {
		logger.debug("Trying to delete employee with id: " + id);
		employeeRepo.deleteById(id);
	}

	public void deleteEmployee(String position) {
		logger.debug("Trying to delete employee with position: " + position);
		employeeRepo.deleteById(this.getEmployeeByPosition(position).getId());
	}

	public void updateEmployee(Employee employee) {
		logger.debug("Trying to update employee with position: " + employee.getPosition());
		employeeRepo.save(employee);
	}

	
	public void addStatusForEmployee(String position, Status newStatus) {
		Employee employee = this.getEmployeeByPositionWithStatusList(position);
		employee.addStatus(newStatus);
//		System.out.println("Collection after Sorting"+employee.getStatusList());
		this.updateEmployee(employee);
	}
	
	public Employee getEmployeeByPositionWithStatusList(String position) {
		return employeeRepo.findOneWithStatusByPosition(position);
	}

	public List<Employee> getAllEmployeesWithStatusList() {
		logger.debug("Trying to fetch all employees with statusList");
		List<Employee> employeeList = new ArrayList<>();
		employeeRepo.findAll().forEach(e -> employeeList.add(e));
		return employeeList;
	}


	// methods for changing single values of employee-object
}
