package unq.edu.li.pdes.unqpremium.mapper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import unq.edu.li.pdes.unqpremium.dto.AccountDTO;
import unq.edu.li.pdes.unqpremium.dto.DegreeDTO;
import unq.edu.li.pdes.unqpremium.dto.SemesterDTO;
import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.dto.UserDTO;
import unq.edu.li.pdes.unqpremium.model.Account;
import unq.edu.li.pdes.unqpremium.model.AccountRole;
import unq.edu.li.pdes.unqpremium.model.Degree;
import unq.edu.li.pdes.unqpremium.model.Semester;
import unq.edu.li.pdes.unqpremium.model.SemesterType;
import unq.edu.li.pdes.unqpremium.model.Subject;
import unq.edu.li.pdes.unqpremium.model.User;
import unq.edu.li.pdes.unqpremium.vo.AccountVO;
import unq.edu.li.pdes.unqpremium.vo.SemesterVO;
import unq.edu.li.pdes.unqpremium.vo.SubjectVO;
import unq.edu.li.pdes.unqpremium.vo.UserLoginVO;
import unq.edu.li.pdes.unqpremium.vo.UserVO;

@Component
public class RegisterMapper {

	@Autowired
	private MapperFactory mapperFactory;
	
	@Autowired
	private Mapper mapper;
	
	@PostConstruct
	public void initialize() {

		mapperFactory.classMap(SemesterDTO.class, Semester.class).customize(new CustomMapper<SemesterDTO, Semester>() {
			@Override
			public void mapBtoA(Semester b, SemesterDTO a, MappingContext context) {
				a.setFromDate(b.getFromDate());
				a.setToDate(b.getToDate());
				a.setId(b.getId());
				a.setSemesterType(b.getSemesterType().toString());
				a.setDescription(b.getSemesterType().getDescription());
			}
			@Override
			public void mapAtoB(SemesterDTO a, Semester b, MappingContext context) {
				b.setFromDate(a.getFromDate());
				b.setToDate(a.getToDate());
				b.setId(a.getId());
				b.setSemesterType(SemesterType.valueOf(a.getSemesterType()));
			}
		}).byDefault().register();
		
		mapperFactory.classMap(SemesterVO.class, Semester.class).customize(new CustomMapper<SemesterVO, Semester>() {
			@Override
			public void mapBtoA(Semester b, SemesterVO a, MappingContext context) {
				a.setFromDate(b.getFromDate());
				a.setToDate(b.getToDate());
				a.setSemesterType(b.getSemesterType().toString());
			}
			@Override
			public void mapAtoB(SemesterVO a, Semester b, MappingContext context) {
				b.setFromDate(a.getFromDate());
				b.setToDate(a.getToDate());
				b.setSemesterType(SemesterType.valueOf(a.getSemesterType()));
			}
		}).byDefault().register();
		
		mapperFactory.classMap(AccountVO.class, Account.class).customize(new CustomMapper<AccountVO, Account>() {
			@Override
			public void mapBtoA(Account b, AccountVO a, MappingContext context) {
				a.setFirstname(b.getFirstname());
				a.setLastname(b.getLastname());
				a.setDni(b.getDni());
				a.setRole(b.getAccountRole().name());
			}
			@Override
			public void mapAtoB(AccountVO a, Account b, MappingContext context) {
				b.setFirstname(a.getFirstname());
				b.setLastname(a.getLastname());
				b.setDni(a.getDni());
				b.setAccountRole(AccountRole.valueOf(a.getRole()));
			}
		}).byDefault().register();
		
		mapperFactory.classMap(AccountDTO.class, Account.class).customize(new CustomMapper<AccountDTO, Account>() {
			@Override
			public void mapBtoA(Account b, AccountDTO a, MappingContext context) {
				a.setId(b.getId());
				a.setFirstname(b.getFirstname());
				a.setLastname(b.getLastname());
				a.setDni(b.getDni());
				a.setRole(b.getAccountRole().name());
				a.setRoleDescripton(b.getAccountRole().getDescription());
			}
			@Override
			public void mapAtoB(AccountDTO a, Account b, MappingContext context) {
				b.setId(a.getId());
				b.setFirstname(a.getFirstname());
				b.setLastname(a.getLastname());
				b.setDni(a.getDni());
				b.setAccountRole(AccountRole.valueOf(a.getRole()));
			}
		}).byDefault().register();
		
		mapperFactory.classMap(UserVO.class, User.class).customize(new CustomMapper<UserVO, User>() {
			@Override
			public void mapBtoA(User b, UserVO a, MappingContext context) {
				a.setEmail(b.getEmail());
				a.setPassword(b.getPassword());
				a.setAccount(mapper.map(a.getAccount(), AccountVO.class));
			}
			@Override
			public void mapAtoB(UserVO a, User b, MappingContext context) {
				b.setEmail(a.getEmail());
				b.setPassword(a.getPassword());
				b.setAccount(mapper.map(a.getAccount(), Account.class));
			}
		}).byDefault().register();
		
		mapperFactory.classMap(UserDTO.class, User.class).customize(new CustomMapper<UserDTO, User>() {
			@Override
			public void mapBtoA(User b, UserDTO a, MappingContext context) {
				a.setId(b.getId());
				a.setEmail(b.getEmail());
				a.setPassword(b.getPassword());
				a.setAccount(mapper.map(a.getAccount(), AccountDTO.class));
			}
			@Override
			public void mapAtoB(UserDTO a, User b, MappingContext context) {
				b.setId(a.getId());
				b.setEmail(a.getEmail());
				b.setPassword(a.getPassword());
				b.setAccount(mapper.map(a.getAccount(), Account.class));
			}
		}).byDefault().register();
		
		mapperFactory.classMap(UserLoginVO.class, User.class).customize(new CustomMapper<UserLoginVO, User>() {
			@Override
			public void mapBtoA(User b, UserLoginVO a, MappingContext context) {
				a.setEmail(b.getEmail());
				a.setPassword(b.getPassword());
			}
			@Override
			public void mapAtoB(UserLoginVO a, User b, MappingContext context) {
				b.setEmail(a.getEmail());
				b.setPassword(a.getPassword());
			}
		}).byDefault().register();
		
		mapperFactory.classMap(DegreeDTO.class, Degree.class).customize(new CustomMapper<DegreeDTO, Degree>() {
			@Override
			public void mapBtoA(Degree b, DegreeDTO a, MappingContext context) {
				a.setId(b.getId());
				a.setName(b.getName());
			}
			@Override
			public void mapAtoB(DegreeDTO a, Degree b, MappingContext context) {
				b.setId(a.getId());
				b.setName(a.getName());
			}
		}).byDefault().register();
		
		mapperFactory.classMap(SubjectDTO.class, Subject.class).customize(new CustomMapper<SubjectDTO, Subject>() {
			@Override
			public void mapBtoA(Subject b, SubjectDTO a, MappingContext context) {
				a.setId(b.getId());
				a.setName(b.getName());
			}
			@Override
			public void mapAtoB(SubjectDTO a, Subject b, MappingContext context) {
				b.setId(a.getId());
				b.setName(a.getName());
			}
		}).byDefault().register();
		
		mapperFactory.classMap(SubjectVO.class, Subject.class).customize(new CustomMapper<SubjectVO, Subject>() {
			@Override
			public void mapBtoA(Subject b, SubjectVO a, MappingContext context) {
				a.setName(b.getName());
			}
			@Override
			public void mapAtoB(SubjectVO a, Subject b, MappingContext context) {
				b.setName(a.getName());
			}
		}).byDefault().register();
		
	}
}
