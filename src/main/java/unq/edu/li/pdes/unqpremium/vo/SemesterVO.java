package unq.edu.li.pdes.unqpremium.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;

@Data
public class SemesterVO {

	@JsonProperty("semesterType")
	private String semesterType;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("toDate")
	private Date toDate;
	
	@JsonProperty("degreeIds")
	private List<Long> degreeIds;
	
	@JsonProperty("subjects")
	private List<SubjectDTO> subjects;
}
