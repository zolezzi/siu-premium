package unq.edu.li.pdes.unqpremium.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SemesterFilterDTO {

	@JsonProperty("year")
	private Integer year;

	@JsonProperty("semesterType")
	private String semesterType;
}
