package unq.edu.li.pdes.unqpremium.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
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
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Degree;
import unq.edu.li.pdes.unqpremium.repository.DegreeRepository;
import unq.edu.li.pdes.unqpremium.service.impl.DegreeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DegreeServiceTest {

	@Mock
	private Degree degree;
	
	@Mock
	private DegreeDTO degreeDto;
	
	@Mock
	private DegreeRepository repository;
	
	@Mock
	private Mapper mapper;
	
	@InjectMocks
	private DegreeServiceImpl service;
	
	@Before
	public void setUp(){
		service = new DegreeServiceImpl(repository, mapper);
		when(repository.findAll()).thenReturn(List.of(degree));
		when(mapper.mapList(anyList(), eq(DegreeDTO.class))).thenReturn(List.of(degreeDto));
	}
	
	@Test
	public void testFindAllDegreeWithElements(){
	    assertThat(service.findAll(), is(List.of(degreeDto)));
	    verify(repository).findAll();
	}
}
