package rocks.monsees.statusboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import rocks.monsees.statusboard.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	Employee findByPosition(String position);

	@EntityGraph(attributePaths = "statusList")
	Employee findOneWithStatusByPosition(String position);
	
	
	@EntityGraph(attributePaths = "statusList")
	List<Employee> findAll();

}
