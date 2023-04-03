package unq.edu.li.pdes.unqpremium.service;

import unq.edu.li.pdes.unqpremium.dto.JwtResponseDTO;
import unq.edu.li.pdes.unqpremium.vo.UserLoginVO;

public interface UserService {

	JwtResponseDTO login(UserLoginVO user);
}
