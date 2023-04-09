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

import unq.edu.li.pdes.unqpremium.dto.CommitteeDTO;
import unq.edu.li.pdes.unqpremium.service.impl.CommitteeServiceImpl;
import unq.edu.li.pdes.unqpremium.vo.CommitteeVO;

@RunWith(MockitoJUnitRunner.class)
public class CommitteeControllerTest {

	private static final Long ID = 1L;
	private static final Long ID_SEMESTER_DEGREE_SUBJECT = 1L;
	
	@Mock
	private CommitteeServiceImpl service;
	
	@Mock
	private CommitteeDTO committeeDto;
	
	@InjectMocks
	private CommitteeController controller;
	
	@Before
	public void setUp(){
		when(service.findById(ID)).thenReturn(committeeDto);
		when(service.save(any())).thenReturn(committeeDto);
		when(service.update(committeeDto, ID_SEMESTER_DEGREE_SUBJECT, ID)).thenReturn(committeeDto);
	}
	
	@Test
	public void testfindByIdThenReturnACommitteeDTO(){
		assertThat(controller.findById(ID), is(committeeDto));
		verify(service).findById(eq(ID));
	}
	
	@Test
	public void testsaveThenReturnCommitteeDto(){
		var committeeVO = new CommitteeVO();
		var response = controller.save(committeeVO);
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).save(eq(committeeVO));
	}
	
	@Test
	public void testupdateThenReturnCommitteeDto(){
		var response = controller.update(committeeDto, ID_SEMESTER_DEGREE_SUBJECT, ID);
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).update(eq(committeeDto), eq(ID_SEMESTER_DEGREE_SUBJECT), eq(ID));
	}
	
	@Test
	public void testDeleteSubjectThenReturnBasicResponse(){
		var response = controller.deleteById(ID_SEMESTER_DEGREE_SUBJECT, ID);
		assertThat(response.getMessage(),is(notNullValue()));    
		assertThat(response.getError(),is(Boolean.FALSE));
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).deleteById(eq(ID_SEMESTER_DEGREE_SUBJECT), eq(ID));
	}
}
