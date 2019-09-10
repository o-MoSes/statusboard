package rocks.monsees.statusboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.monsees.statusboard.model.Event;
import rocks.monsees.statusboard.repository.EventRepository;

@Service
public class EventService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EventRepository eventRepository;
	
	
	public Event getEvent(int id) {
		logger.debug("Trying to fetch event with id: " + id);
		//TODO return Optional and handle it in Controller
		return this.eventRepository.findById(id).get();
	}
	
	public void addEvent(Event e) {
		logger.debug("Trying to add event: " + e);
		this.eventRepository.save(e);
	}

	
	public void updateEvent(Event e) {
		logger.debug("Trying to update event with id: " + e.getId());
		this.addEvent(e);
	}
	
	public void deleteEvent(int id) {
		logger.debug("Trying to delete event with id: " + id);
		this.eventRepository.deleteById(id);
	}
	
	
	
	
	
	
	
	
	

}
