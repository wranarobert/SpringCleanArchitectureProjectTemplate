package ca.project.domain.dto.error;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
	private String error;
	private String message;
	private Date timestamp;
	
	public ErrorDTO(String error, String message) {
		super();
		this.error = error;
		this.message = message;
		this.timestamp = new Date();
	}
}
