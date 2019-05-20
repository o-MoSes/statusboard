package rocks.monsees.statusboard.repository;

import org.springframework.data.repository.CrudRepository;

import rocks.monsees.statusboard.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	Employee findByPosition(String position);
	
}
