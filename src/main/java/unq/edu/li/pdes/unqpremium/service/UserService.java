package unq.edu.li.pdes.unqpremium.service;

import unq.edu.li.pdes.unqpremium.dto.JwtResponseDTO;
import unq.edu.li.pdes.unqpremium.dto.UserDTO;

public interface UserService {

	JwtResponseDTO login(UserDTO user);
}
