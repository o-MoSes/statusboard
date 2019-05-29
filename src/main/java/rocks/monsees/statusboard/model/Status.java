package rocks.monsees.statusboard.model;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Status implements Comparable<Status> {

	@Id
	@GeneratedValue
	private int id;
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private LocalDate begin;
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private LocalDate end;
	private boolean available;
	private String description;

	@ManyToOne
	@JoinColumn(name = "employee_id")
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

		// format status period
		String startDay;
		String endDay;

		String startMonth;
		String endMonth;

		// add leading 0 to start day
		if (String.valueOf(this.begin.getDayOfMonth()).length() < 2) {
			startDay = String.format("%02d", this.begin.getDayOfMonth());
		} else {
			startDay = String.valueOf(this.begin.getDayOfMonth());
		}
		startMonth = this.begin.getMonth().getDisplayName(TextStyle.SHORT, Locale.GERMANY);

		// add leading 0 to end day
		if (String.valueOf(this.end.getDayOfMonth()).length() < 2) {
			endDay = String.format("%02d", this.end.getDayOfMonth());
		} else {
			endDay = String.valueOf(this.end.getDayOfMonth());
		}
		endMonth = this.end.getMonth().getDisplayName(TextStyle.SHORT, Locale.GERMANY);

		String formattedBegin = startDay + ". " + startMonth;
		String formattedEnd = endDay + ". " + endMonth;

//		System.out.println(String.format("%-20s", formattedBegin) + "|");

		return formattedBegin + " - " + formattedEnd;
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
