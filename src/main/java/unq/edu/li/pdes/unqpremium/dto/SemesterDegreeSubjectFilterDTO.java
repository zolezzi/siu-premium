package unq.edu.li.pdes.unqpremium.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDegreeSubjectFilterDTO {
	
	@JsonProperty("likeName")
	private String likeName;

	@JsonProperty("degreeIds")
	private List<Long> degreeIds = new ArrayList<>();
	
	@JsonProperty("subjectIds")
	private List<Long> subjectIds = new ArrayList<>();

}
