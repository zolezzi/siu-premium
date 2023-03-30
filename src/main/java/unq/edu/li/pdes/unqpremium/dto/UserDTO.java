package unq.edu.li.pdes.unqpremium.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "email", "password"})
public class UserDTO {
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String password;
}