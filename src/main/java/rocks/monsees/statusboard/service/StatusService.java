package rocks.monsees.statusboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.monsees.statusboard.model.Status;
import rocks.monsees.statusboard.repository.StatusRepository;

@Service
public class StatusService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// TODO Check if service is really required
	
	@Autowired
	StatusRepository statusRepository;

	public Status getStatusById(int statusId) {
		logger.debug("Trying to retrieve status with id: " + statusId);
		return statusRepository.findById(statusId).get();
	}

	public void saveStatus(Status status) {
		logger.debug("Trying to save status with id: " + status.getId());
		statusRepository.save(status);
	}

	public void updateStatus(Status status) {
		logger.debug("Trying to update status with id: " + status.getId());
		statusRepository.save(status);
	}

	public void deleteStatus(int statusId) {
		logger.debug("Trying to delete status with id: " + statusId);
		statusRepository.deleteById(statusId);
	}

}
