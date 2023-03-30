package unq.edu.li.pdes.unqpremium.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.JwtResponseDTO;
import unq.edu.li.pdes.unqpremium.dto.UserDTO;
import unq.edu.li.pdes.unqpremium.model.User;
import unq.edu.li.pdes.unqpremium.repository.UserRepository;
import unq.edu.li.pdes.unqpremium.utils.TokenUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

	private final UserRepository repository;
	private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

	public JwtResponseDTO login(UserDTO user) {
		authenticate(user.getEmail(), user.getPassword());
		var userDetails = (User) repository.findOneByEmail(user.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException(String.format("No found user:%s", user.getEmail())));
        var token = tokenUtils.createToken(userDetails.getUsername());
		return new JwtResponseDTO(userDetails.getUsername(), token);
	}
	
    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
