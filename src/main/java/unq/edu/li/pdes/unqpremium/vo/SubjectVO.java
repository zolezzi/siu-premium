package unq.edu.li.pdes.unqpremium.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SubjectVO {

	@JsonProperty("degreeId")
	private Long degreeId;
	
	@JsonProperty("name")
	private String name;
	
}
