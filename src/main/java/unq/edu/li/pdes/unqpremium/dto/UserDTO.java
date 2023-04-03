package unq.edu.li.pdes.unqpremium.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "email", "password", "account"})
public class UserDTO {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("account")
	private AccountDTO account;
}