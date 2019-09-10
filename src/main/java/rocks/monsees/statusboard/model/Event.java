package rocks.monsees.statusboard.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Event {

	@Id
	@Setter(AccessLevel.NONE)
	private int id;
	private LocalDate begin;
	private LocalDate end;
	private String title;
	private String description;

	// TODO add foto

}
