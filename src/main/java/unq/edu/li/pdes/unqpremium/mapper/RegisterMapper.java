package unq.edu.li.pdes.unqpremium.mapper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import unq.edu.li.pdes.unqpremium.dto.SemesterDTO;
import unq.edu.li.pdes.unqpremium.model.Semester;
import unq.edu.li.pdes.unqpremium.model.SemesterType;
import unq.edu.li.pdes.unqpremium.vo.SemesterVO;

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
		
	}
}
