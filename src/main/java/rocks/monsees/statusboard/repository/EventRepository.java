package rocks.monsees.statusboard.repository;

import org.springframework.data.repository.CrudRepository;

import rocks.monsees.statusboard.model.Event;

public interface EventRepository extends CrudRepository<Event, Integer>{

}
