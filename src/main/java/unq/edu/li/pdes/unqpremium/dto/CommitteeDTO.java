package unq.edu.li.pdes.unqpremium.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CommitteeDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("daysClass")
	private String daysClass;

	@JsonProperty("professors")
	private List<AccountDTO> professors;
	
	@JsonProperty("students")
	private List<AccountDTO> students;
}
