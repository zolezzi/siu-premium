package unq.edu.li.pdes.unqpremium.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import unq.edu.li.pdes.unqpremium.dto.DegreeDTO;
import unq.edu.li.pdes.unqpremium.dto.DegreeFilterDTO;
import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Degree;
import unq.edu.li.pdes.unqpremium.model.Subject;
import unq.edu.li.pdes.unqpremium.repository.DegreeReportRepository;
import unq.edu.li.pdes.unqpremium.repository.DegreeRepository;
import unq.edu.li.pdes.unqpremium.repository.SubjectRepository;
import unq.edu.li.pdes.unqpremium.service.impl.DegreeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DegreeServiceTest {

	@Mock
	private Degree degree;
	
	@Mock
	private Subject subject;
	
	@Mock
	private DegreeDTO degreeDto;
	
	@Mock
	private SubjectDTO subjectDto;
	
	@Mock
	private DegreeRepository repository;
	
	@Mock
	private SubjectRepository subjectRepository;
	
	@Mock
	private DegreeReportRepository degreeReportRepository;
	
	@Mock
	private Mapper mapper;
	
	@InjectMocks
	private DegreeServiceImpl service;
	
	@Before
	public void setUp(){
		service = new DegreeServiceImpl(repository, subjectRepository, degreeReportRepository, mapper);
		when(repository.findAll()).thenReturn(List.of(degree));
		when(repository.findAllById(any())).thenReturn(List.of(degree));
		when(mapper.map(eq(degree), eq(DegreeDTO.class))).thenReturn(degreeDto);
		when(degree.getSubjects()).thenReturn(List.of(subject));
		when(mapper.map(eq(subject), eq(SubjectDTO.class))).thenReturn(subjectDto);
		when(mapper.mapList(anyList(), eq(DegreeDTO.class))).thenReturn(List.of(degreeDto));
	}
	
	@Test
	public void testFindAllDegreeWithElements(){
	    assertThat(service.findAll(), is(List.of(degreeDto)));
	    verify(repository).findAll();
	}
	
	@Test
	public void testFindAllDegreeWithSubjectsByIdThenReturnDegreeDTOWithElements(){
		var filter = new DegreeFilterDTO();
		filter.setDegreeIds(List.of(1L));
	    assertThat(service.findAllDegreeByFilter(filter), is(List.of(degreeDto)));
	    verify(repository).findAllById(List.of(1L));
	    verify(repository, times(1)).findAllById(List.of(1L));
	}
}
