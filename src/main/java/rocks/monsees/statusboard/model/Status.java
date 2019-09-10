package rocks.monsees.statusboard.model;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import rocks.monsees.statusboard.validation.DateFieldConstraint;

@Entity
@DateFieldConstraint
@Setter
@Getter
public class Status implements Comparable<Status> {

	@Id
	@GeneratedValue
	@Setter(AccessLevel.NONE)
	private int id;

	@NotNull(message = "Please enter a start date.")
	@FutureOrPresent(message = "Begin need to be today or in future.")
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate begin;

	@NotNull(message = "Please enter a end date")
	@FutureOrPresent(message = "End need to be today or in future.")
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate end;

	private boolean available;

	@Size(min = 0, max = 22)
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
	 * Compares Status by begin
	 */
	@Override
	public int compareTo(Status status) {
		return this.begin.compareTo(status.begin);
	}

}
