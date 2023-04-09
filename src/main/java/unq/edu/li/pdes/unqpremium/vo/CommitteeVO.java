package unq.edu.li.pdes.unqpremium.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CommitteeVO {
	
	@JsonProperty("semesterDegreeSubjectId")
	private Long semesterDegreeSubjectId;
	
	@JsonProperty("daysClass")
	private String daysClass;

	@JsonProperty("professors")
	private List<Long> professorsIds;
	
	@JsonProperty("students")
	private List<Long> studentsIds;
}
