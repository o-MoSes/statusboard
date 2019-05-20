package rocks.monsees.statusboard.exception;

public class EmployeeExistsException extends Exception {

	public EmployeeExistsException(String position) {
		super("Employee with position: '"+ position +"' already exists");
	}
	
	

}
