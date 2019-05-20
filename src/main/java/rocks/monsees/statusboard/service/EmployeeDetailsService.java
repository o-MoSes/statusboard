package rocks.monsees.statusboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import rocks.monsees.statusboard.model.Employee;
import rocks.monsees.statusboard.model.EmployeePrincipal;

public class EmployeeDetailsService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeService employeeService;

	@Override
	public UserDetails loadUserByUsername(String position) throws UsernameNotFoundException {

		Employee employee = employeeService.getEmployeeByPosition(position);
		if (null == employee) {
			logger.info("Couldnt fetch employee with position: "+position);
			throw new UsernameNotFoundException(position);
		}
		return new EmployeePrincipal(employee);
	}
}
