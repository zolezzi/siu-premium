package unq.edu.li.pdes.unqpremium.service.impl;

import java.util.Vector;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.JwtResponseDTO;
import unq.edu.li.pdes.unqpremium.dto.UserDTO;
import unq.edu.li.pdes.unqpremium.exception.UserCreateException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.User;
import unq.edu.li.pdes.unqpremium.repository.UserRepository;
import unq.edu.li.pdes.unqpremium.service.UserService;
import unq.edu.li.pdes.unqpremium.utils.EncodeAndDecodeCrypt;
import unq.edu.li.pdes.unqpremium.utils.ErrorUtils;
import unq.edu.li.pdes.unqpremium.utils.TokenUtils;
import unq.edu.li.pdes.unqpremium.validator.UserValidator;
import unq.edu.li.pdes.unqpremium.vo.UserLoginVO;
import unq.edu.li.pdes.unqpremium.vo.UserVO;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository repository;
	private final AuthenticationManager authenticationManager;
	private final AccountServiceImpl accountService;
    private final TokenUtils tokenUtils;
    private final Mapper mapper;
//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private EncodeAndDecodeCrypt passwordEncoder = new EncodeAndDecodeCrypt();
    private Vector<String> errors = new Vector<>();

    @Override
    public JwtResponseDTO login(UserLoginVO user) {
    	String passEncrypt = user.getPassword();
    	user.setPassword(passwordEncoder.encode(passEncrypt));
    	var userDetails = (User) repository.findOneByEmail(user.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException(String.format("No found user:%s", user.getEmail())));
    	var token = tokenUtils.createToken(userDetails.getUsername());
    	authenticate(user.getEmail(), user.getPassword());
//        String token = jwtTokenUtil.generateToken(userDetails);
//		authenticate(user.getEmail(), passwordEncoder.encode(user.getPassword()));
//		var userDetails = (User) repository.findOneByEmail(user.getEmail())
//				.orElseThrow(() -> new UsernameNotFoundException(String.format("No found user:%s", user.getEmail())));
//        var token = tokenUtils.createToken(userDetails.getUsername());
		return new JwtResponseDTO(userDetails.getUsername(), token);
	}
	
	@Transactional
	public UserDTO create(UserVO userVO) {
		UserValidator.validateSamePassword(userVO.getPassword(), userVO.getRepeatPassword(), errors);
		User newUser = mapper.map(userVO, User.class);
        var serDB = repository.findOneByEmail(newUser.getEmail());
        errors = serDB.isPresent() ? ErrorUtils.addError(errors, String.format("There is already a registered user with this email:%s ", newUser.getEmail())) : errors;
        if(!UserValidator.validate(newUser, errors) || !errors.isEmpty()) {
			throw new UserCreateException(String.format("Error register user resolved errors: %s ", errors.toString()));
		}
        var accountNew = accountService.createAccountByUser(userVO.getAccount());
        newUser.setAccount(accountNew);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return mapper.map(repository.save(newUser), UserDTO.class);
	}
	
    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
  
}
