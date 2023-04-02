package unq.edu.li.pdes.unqpremium.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountVO {

	@JsonProperty("firstname")
	private String firstname;
	
	@JsonProperty("lastname")
	private String lastname;
	
	@JsonProperty("degreeId")
	private List<Long> degreeIds;
	
	@JsonProperty("dni")
	private String dni;
	
	@JsonProperty("role")
	private String role;
}
