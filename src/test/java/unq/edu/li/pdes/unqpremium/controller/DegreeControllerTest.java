package unq.edu.li.pdes.unqpremium.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
import unq.edu.li.pdes.unqpremium.service.impl.DegreeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DegreeControllerTest {

	@Mock
	private DegreeServiceImpl service;
	
	@Mock
	private DegreeDTO degreeDto;
	
	@InjectMocks
	private DegreeController controller;
	
	@Before
	public void setUp(){
		when(service.findAll()).thenReturn(List.of(degreeDto));
		when(service.findAllDegreeByFilter(any())).thenReturn(List.of(degreeDto));
	}
	
	@Test
	public void testFindAllDegree(){
		assertThat(controller.findAll(), is(List.of(degreeDto)));
		verify(service).findAll();
	}
	
	@Test
	public void testFindAllDegreeByFilterThenReturnDegreeList(){
		var filter = new DegreeFilterDTO();
		filter.setDegreeIds(List.of(1L));
		assertThat(controller.findAllDegreeByFilter(filter), is(List.of(degreeDto)));
		verify(service).findAllDegreeByFilter(filter);
	}
}
