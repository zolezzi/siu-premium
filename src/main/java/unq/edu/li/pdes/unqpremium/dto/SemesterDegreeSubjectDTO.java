package unq.edu.li.pdes.unqpremium.dto;

import java.util.Date;

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
	
	@JsonProperty("semesterType")
	private String semesterType;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("toDate")
	private Date toDate;
	
	@JsonProperty("description")
	private String description;
}
