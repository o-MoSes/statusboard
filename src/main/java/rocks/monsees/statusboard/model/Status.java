package rocks.monsees.statusboard.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Status implements Comparable<Status>{

	@Id
	@GeneratedValue
	private int id;
	private LocalDate begin;
	private LocalDate end;
	private boolean available;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

	public Status() {
	}

	public Status(boolean available, String description) {
		super();
		this.available = available;
		this.description = description;
	}
	
	public Status(LocalDate begin, LocalDate end, boolean available, String description) {
		super();
		this.begin = begin;
		this.end = end;
		this.available = available;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public boolean isAvailable() {
		return available;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public LocalDate getBegin() {
		return begin;
	}

	public void setBegin(LocalDate begin) {
		this.begin = begin;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}
	
	
	public String getStatusPeriod() {
		 return this.begin.getDayOfMonth()+". "+this.begin.getMonth().getDisplayName(TextStyle.SHORT,Locale.GERMANY)+"  -  " +this.end.getDayOfMonth()+"."+this.end.getMonthValue();
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", begin=" + begin + ", end=" + end + ", available=" + available + ", description="
				+ description + "]";
	}

	/**
	 * Compares Status by id
	 */
	@Override
	public int compareTo(Status status) {
		return this.begin.compareTo(status.begin);
	}


}
