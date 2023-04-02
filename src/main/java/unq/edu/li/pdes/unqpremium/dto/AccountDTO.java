package unq.edu.li.pdes.unqpremium.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("firstname")
	private String firstname;
	
	@JsonProperty("lastname")
	private String lastname;
	
	@JsonProperty("dni")
	private String dni;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("roleDescripton")
	private String roleDescripton;
}
