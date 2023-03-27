package unq.edu.li.pdes.unqpremium.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterFilterDTO {

	@JsonProperty("year")
	private Integer year;

	@JsonProperty("semesterType")
	private String semesterType;
}
