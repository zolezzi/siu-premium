package unq.edu.li.pdes.unqpremium.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DegreeFilterDTO {
	
	@JsonProperty("likeName")
	private String likeName;
	
	@JsonProperty("degreeIds")
	private List<Long> degreeIds;

}
