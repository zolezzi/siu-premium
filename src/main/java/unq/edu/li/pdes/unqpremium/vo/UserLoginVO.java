package unq.edu.li.pdes.unqpremium.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserLoginVO {

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String password;
}
