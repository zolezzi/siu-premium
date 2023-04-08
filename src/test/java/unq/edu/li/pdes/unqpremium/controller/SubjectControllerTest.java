package unq.edu.li.pdes.unqpremium.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.service.impl.SubjectServiceImpl;
import unq.edu.li.pdes.unqpremium.vo.SubjectVO;

@RunWith(MockitoJUnitRunner.class)
public class SubjectControllerTest {
	
	private static final Long ID = 1L;
	private static final Long ID_DEGREE = 1L;

	@Mock
	private SubjectServiceImpl service;
	
	@Mock
	private SubjectDTO subjectDto;
	
	@InjectMocks
	private SubjectController controller;
	
	@Before
	public void setUp(){
		when(service.findById(ID)).thenReturn(subjectDto);
		when(service.save(any())).thenReturn(subjectDto);
		when(service.update(subjectDto, ID_DEGREE, ID)).thenReturn(subjectDto);
	}
	
	@Test
	public void testfindByIdThenReturnASemesteDTO(){
		assertThat(controller.findById(ID), is(subjectDto));
		verify(service).findById(eq(ID));
	}
	
	@Test
	public void testsaveThenReturnsubjectDto(){
		var subjectVO = new SubjectVO();
		var response = controller.save(subjectVO);
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).save(eq(subjectVO));
	}
	
	@Test
	public void testupdateThenReturnSubjectDto(){
		var response = controller.update(subjectDto, ID_DEGREE, ID);
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).update(eq(subjectDto), eq(ID_DEGREE), eq(ID));
	}
	
	@Test
	public void testDeleteSubjectThenReturnBasicResponse(){
		var response = controller.deleteById(ID_DEGREE, ID);
		assertThat(response.getMessage(),is(notNullValue()));    
		assertThat(response.getError(),is(Boolean.FALSE));
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).deleteById(eq(ID_DEGREE), eq(ID));
	}
	
}
