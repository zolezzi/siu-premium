package unq.edu.li.pdes.unqpremium.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SubjectDTO {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("degreeId")
	private Long degreeId;
	
	@JsonProperty("degreeName")
	private String degreeName;
}
