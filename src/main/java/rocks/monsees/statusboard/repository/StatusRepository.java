package rocks.monsees.statusboard.repository;

import org.springframework.data.repository.CrudRepository;

import rocks.monsees.statusboard.model.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {
	
	
}