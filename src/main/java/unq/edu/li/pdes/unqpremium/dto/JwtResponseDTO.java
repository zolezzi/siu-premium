package unq.edu.li.pdes.unqpremium.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDTO {

    private String email;
    private String token;
    private String firstname;
    private String lastname;
    private String role;
}