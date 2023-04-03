package unq.edu.li.pdes.unqpremium.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import unq.edu.li.pdes.unqpremium.dto.AccountDTO;
import unq.edu.li.pdes.unqpremium.dto.UserDTO;
import unq.edu.li.pdes.unqpremium.exception.UserCreateException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Account;
import unq.edu.li.pdes.unqpremium.model.User;
import unq.edu.li.pdes.unqpremium.repository.AccountRepository;
import unq.edu.li.pdes.unqpremium.repository.UserRepository;
import unq.edu.li.pdes.unqpremium.service.impl.AccountServiceImpl;
import unq.edu.li.pdes.unqpremium.service.impl.UserServiceImpl;
import unq.edu.li.pdes.unqpremium.utils.TokenUtils;
import unq.edu.li.pdes.unqpremium.vo.AccountVO;
import unq.edu.li.pdes.unqpremium.vo.UserLoginVO;
import unq.edu.li.pdes.unqpremium.vo.UserVO;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static final String EMAIL = "admin@gmail.com";
	private static final String PASSWORD = "HolaAdmin123!";
	private static final String USER_NOT_FOUND = "USER2";
	private static final String TOKEN_VALUE = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NTMwMTAyNCwibmFtZSI6ImFkbWluIn0.1nWH5XPt71NJ3cC4nzrvXPLr31DnXg5iUUFg0dyemHbhYirBI4IMbR0KH_iNnt0x_dAwNWZco66w8XCOXIut9g";
	private static final String DNI = "35000111";
	private static final String FIRST_NAME = "TEST";
	private static final String LAST_NAME = "TEST";
	private static final String ACCOUNT_ROLE = "ADMIN";
	private static final String ACCOUNT_ROLE_DESCRIPTION = "ADMIN";
	private static final String EMAIL_CREATE = "admin2@gmail.com";
	private static final Long ACCOUNT_ID = 1l;
	
	@Mock
	private User user;
	
	@Mock
	private User userCreate;
	
	@Mock
	private UserDTO userDto;
	
	@Mock
	private Account account;
	
	@Mock
	private AccountDTO accountDto;
	
	@Mock
	private UserVO userVO;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private AccountRepository accountRepository;
    
	@Mock
	private AuthenticationManager authenticationManager;
	
	@Mock
	private AccountServiceImpl accountService;
    
	@Mock
	private TokenUtils tokenUtil;
	
	@Mock
	private Mapper mapper;
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@InjectMocks
	private UserServiceImpl service;
	
	@Before
	public void setUp(){
		service = new UserServiceImpl(repository, authenticationManager, accountService, tokenUtil, mapper);
		
		when(repository.findOneByEmail(eq(EMAIL))).thenReturn(Optional.of(user));
		when(user.getUsername()).thenReturn(EMAIL);
		when(tokenUtil.createToken(eq(EMAIL))).thenReturn(TOKEN_VALUE);
		when(mapper.map(any(), eq(User.class))).thenReturn(user);
		when(user.getEmail()).thenReturn(EMAIL_CREATE);
		when(user.getPassword()).thenReturn(PASSWORD);
		when(repository.findOneByEmail(EMAIL_CREATE)).thenReturn(Optional.empty());
		when(accountService.createAccountByUser(any())).thenReturn(account);
		when(mapper.map(any(), eq(UserDTO.class))).thenReturn(userDto);
		when(userDto.getEmail()).thenReturn(EMAIL_CREATE);
		when(userDto.getPassword()).thenReturn(PASSWORD);
		when(userDto.getAccount()).thenReturn(accountDto);
		when(accountDto.getId()).thenReturn(ACCOUNT_ID);
		when(accountDto.getDni()).thenReturn(DNI);
		when(accountDto.getFirstname()).thenReturn(FIRST_NAME);
		when(accountDto.getLastname()).thenReturn(LAST_NAME);
		when(accountDto.getRole()).thenReturn(ACCOUNT_ROLE);
		when(accountDto.getRoleDescripton()).thenReturn(ACCOUNT_ROLE_DESCRIPTION);
		when(mapper.map(eq(userVO), eq(User.class))).thenReturn(userCreate);
		when(userCreate.getEmail()).thenReturn(EMAIL);
		when(userCreate.getPassword()).thenReturn(PASSWORD);
		when(userVO.getPassword()).thenReturn(PASSWORD);
		when(userVO.getRepeatPassword()).thenReturn(PASSWORD);
	}
	
	@Test
	public void testLoginUserValidThenReturnJWTResponseWithUsernameAndToken(){
		var user = new UserLoginVO();
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		var jwtResponseDTO = service.login(user);
	    assertThat(jwtResponseDTO.getEmail(), is(EMAIL));
	    assertThat(jwtResponseDTO.getToken(), is(TOKEN_VALUE));
		assertNotNull(jwtResponseDTO.toString());
		assertNotNull(jwtResponseDTO.hashCode());
	    verify(repository).findOneByEmail(eq(EMAIL));
	    verify(authenticationManager).authenticate(any());
	}
	
	@Test
	public void testLoginUserNotFoundThenReturnThenReturnException(){
		ex.expect(UsernameNotFoundException.class);
		ex.expectMessage("No found user:USER2");
		var user = new UserLoginVO();
		user.setEmail(USER_NOT_FOUND);
		user.setPassword(PASSWORD);
		service.login(user);
		verify(repository, never()).findOneByEmail(eq(USER_NOT_FOUND));
	}
	
	@Test
	public void testCreateUserWithAccountValidThenReturnANewUserWithAnAccount(){
		var account = createAccountVO();
		var user = new UserVO();
		user.setEmail(EMAIL_CREATE);
		user.setPassword(PASSWORD);
		user.setRepeatPassword(PASSWORD);
		user.setAccount(account);
		var userDTO = service.create(user);
	    assertThat(userDTO.getEmail(), is(EMAIL_CREATE));
	    assertThat(userDTO.getPassword(), is(PASSWORD));
	    assertThat(userDTO.getAccount().getId(), is(ACCOUNT_ID));
	    assertThat(userDTO.getAccount().getDni(), is(DNI));
	    assertThat(userDTO.getAccount().getFirstname(), is(FIRST_NAME));
	    assertThat(userDTO.getAccount().getLastname(), is(LAST_NAME));
	    assertThat(userDTO.getAccount().getRole(), is(ACCOUNT_ROLE));
	    assertThat(userDTO.getAccount().getRoleDescripton(), is(ACCOUNT_ROLE_DESCRIPTION));
		assertNotNull(userDTO.toString());
		assertNotNull(userDTO.hashCode());
	}
	
	@Test
	public void testCreateUserWithUserExistInDatabaseThenReturnThenReturnException(){
		ex.expect(UserCreateException.class);
		ex.expectMessage("Error register user resolved errors: [There is already a registered user with this email:admin@gmail.com ] ");
		service.create(userVO);
		verify(repository).findOneByEmail(eq(EMAIL_CREATE));
	}
	
	private AccountVO createAccountVO() {
		var account = new AccountVO();
		account.setDni(DNI);
		account.setFirstname(FIRST_NAME);
		account.setLastname(LAST_NAME);
		account.setRole(ACCOUNT_ROLE);
		return account;
	}
}
