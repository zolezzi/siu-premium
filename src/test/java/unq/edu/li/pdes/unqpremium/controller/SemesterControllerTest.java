package unq.edu.li.pdes.unqpremium.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
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

import unq.edu.li.pdes.unqpremium.dto.SemesterDTO;
import unq.edu.li.pdes.unqpremium.dto.SemesterFilterDTO;
import unq.edu.li.pdes.unqpremium.model.SemesterType;
import unq.edu.li.pdes.unqpremium.service.impl.SemesterServiceImpl;
import unq.edu.li.pdes.unqpremium.vo.SemesterVO;

@RunWith(MockitoJUnitRunner.class)
public class SemesterControllerTest {
	private static final Long ID = 1L;
	private static final String NAME = SemesterType.FIRST.name();
	private static final Integer YEAR_LIKE = 2022;
	
	@Mock
	private SemesterServiceImpl service;
	
	@Mock
	private SemesterDTO semesterDto;
	
	@InjectMocks
	private SemesterController controller;
	
	@Before
	public void setUp(){
		when(service.findSemesterById(ID)).thenReturn(semesterDto);
		when(service.saveSemester(any())).thenReturn(semesterDto);
		when(service.updateSemester(semesterDto, ID)).thenReturn(semesterDto);
		when(service.searchSemestersByFilter(any())).thenReturn(List.of(semesterDto));
	}
	
	@Test
	public void testFindSemesterByIdThenReturnASemesteDTO(){
		assertThat(controller.findSemesterById(ID), is(semesterDto));
		verify(service).findSemesterById(eq(ID));
	}
	
	@Test
	public void testSaveSemesterThenReturnSemesterDTO(){
		var semesterVO = new SemesterVO();
		semesterVO.setSemesterType(NAME);
		var response = controller.saveSemester(semesterVO);
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).saveSemester(eq(semesterVO));
	}
	
	@Test
	public void testUpdateSemesterThenReturnSemesterDTO(){
		var response = controller.updateSemester(semesterDto, ID);
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).updateSemester(eq(semesterDto), eq(ID));
	}
	
	@Test
	public void testDeleteSemesterThenReturnBasicResponse(){
		var response = controller.deleteSemesterById(ID);
		assertThat(response.getMessage(),is(notNullValue()));    
		assertThat(response.getError(),is(Boolean.FALSE));
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).deleteSemesterById(eq(ID));
	}
	
	@Test
	public void testSearchSemestersByFilter(){
		var semesterFilterDto = new SemesterFilterDTO(YEAR_LIKE, null);
		assertThat(controller.searchSemestersByFilter(semesterFilterDto), is(List.of(semesterDto)));
		verify(service).searchSemestersByFilter(eq(semesterFilterDto));
	}
}
