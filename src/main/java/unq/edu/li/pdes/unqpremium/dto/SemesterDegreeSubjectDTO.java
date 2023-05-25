package unq.edu.li.pdes.unqpremium.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SemesterDegreeSubjectDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("degreeId")
	private Long degreeId;
	
	@JsonProperty("subjectId")
	private Long subjectId;
	
	@JsonProperty("degreeName")
	private String degreeName;
	
	@JsonProperty("subjectName")
	private String subjectName;
}
