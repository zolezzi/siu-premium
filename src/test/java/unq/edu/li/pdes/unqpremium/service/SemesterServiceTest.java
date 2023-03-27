package unq.edu.li.pdes.unqpremium.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import unq.edu.li.pdes.unqpremium.dto.SemesterDTO;
import unq.edu.li.pdes.unqpremium.dto.SemesterFilterDTO;
import unq.edu.li.pdes.unqpremium.exception.SemesterNotFoundException;
import unq.edu.li.pdes.unqpremium.model.Semester;
import unq.edu.li.pdes.unqpremium.model.SemesterType;
import unq.edu.li.pdes.unqpremium.repository.SemesterRepository;
import unq.edu.li.pdes.unqpremium.service.impl.SemesterServiceImpl;
import unq.edu.li.pdes.unqpremium.utils.Mapper;
import unq.edu.li.pdes.unqpremium.vo.SemesterVO;

@RunWith(MockitoJUnitRunner.class)
public class SemesterServiceTest {

	private static final Long ID = 1L;
	private static final Long ID_SEMESTER_NOT_FOUND = 10L;
	private static final Long ID_SEMESTER_DELETE = 2L;
	private static final Long ID_SEMESTER_DELETE_NOT_FOUND = 10L;
	private static final Integer DATE_YEAR_LIKE = 2021;
	private static final Integer DATE_YEAR_LIKE_NOT_MATCH = 1920;
	private static final SemesterType FIRST_SEMESTER_TYPE = SemesterType.FIRST;
	
	@Mock
	private Semester semester;
	
	@Mock
	private SemesterDTO semesterDto;
	
	@Mock
	private SemesterRepository repository;
	
	@Mock
	private Mapper mapper;
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@InjectMocks
	private SemesterServiceImpl service;
	
	@Before
	public void setUp(){
		service = new SemesterServiceImpl(repository, mapper);
		when(semester.getSemesterType()).thenReturn(FIRST_SEMESTER_TYPE);
		when(repository.findById(ID)).thenReturn(Optional.of(semester));
		when(mapper.map(eq(semester), eq(SemesterDTO.class))).thenReturn(semesterDto);
		when(mapper.map(any(), eq(Semester.class))).thenReturn(semester);
		when(repository.findById(ID_SEMESTER_DELETE)).thenReturn(Optional.of(semester));
		when(repository.save(semester)).thenReturn(semester);
		when(repository.save(semester)).thenReturn(semester);
		when(repository.searchSemestersByFilter(DATE_YEAR_LIKE)).thenReturn(List.of(semester));
		when(repository.searchSemestersByFilter(DATE_YEAR_LIKE_NOT_MATCH)).thenReturn(Collections.emptyList());
		when(repository.searchSemestersByFilter(DATE_YEAR_LIKE)).thenReturn(List.of(semester));
		when(repository.searchSemestersByFilter(DATE_YEAR_LIKE_NOT_MATCH)).thenReturn(Collections.emptyList());
	}
	@Test
	public void testFindSemesterByIdThenReturnASemester(){
		SemesterDTO semesterResponse = service.findSemesterById(ID);
	    assertThat(semesterResponse, is(semesterDto));
	    verify(repository).findById(eq(ID));
	}
	
	@Test
	public void testFindSemesterByIdAndNotFoundThenReturnException(){
		ex.expect(SemesterNotFoundException.class);
		ex.expectMessage("Semester not found");
		service.findSemesterById(ID_SEMESTER_NOT_FOUND);
		verify(repository, never()).findById(eq(ID_SEMESTER_NOT_FOUND));
	}
	
	@Test
	public void testDeleteSemesterById(){
		service.deleteSemesterById(ID_SEMESTER_DELETE);
	    verify(repository).delete(any(Semester.class));
	}
	
	@Test
	public void testDeleteSemesterByIdAndNotFoundThenReturnException(){
		ex.expect(SemesterNotFoundException.class);
		ex.expectMessage("Semester not found");
		service.deleteSemesterById(ID_SEMESTER_DELETE_NOT_FOUND);
		verify(repository, never()).findById(eq(ID_SEMESTER_DELETE_NOT_FOUND));
	}
	
	@Test
	public void testSaveSemester(){
		var semesterVO = new SemesterVO();
		semesterVO.setSemesterType(FIRST_SEMESTER_TYPE.name());
	    assertThat(service.saveSemester(semesterVO), is(semesterDto));
	    verify(repository).save(eq(semester));
	}
	
	@Test
	public void testUpdateSemester(){
		assertThat(semester.getSemesterType(), is(FIRST_SEMESTER_TYPE));
		semester.setSemesterType(SemesterType.SECOND);
	    assertThat(service.updateSemester(semesterDto, ID), is(semesterDto));
	    verify(repository).save(eq(semester));
	    verify(repository, times(1)).save(semester);
	}
	
	@Test
	public void testUpdateSemesterAndNotFoundThenReturnException(){
		ex.expect(SemesterNotFoundException.class);
		ex.expectMessage("Semester not found");
		service.updateSemester(semesterDto, ID_SEMESTER_NOT_FOUND);
	    verify(repository, never()).findById(eq(ID_SEMESTER_NOT_FOUND));
	    verify(repository, never()).save(eq(semester));
	}
	
	@Test
	public void testSearchSemesterByNameThenReturnSemestersLists(){
		var semesterFilterDto = new SemesterFilterDTO(DATE_YEAR_LIKE, null);
	    assertThat(service.searchSemestersByFilter(semesterFilterDto), is(List.of(semesterDto)));
	    verify(repository).searchSemestersByFilter(DATE_YEAR_LIKE);
	    verify(repository, times(1)).searchSemestersByFilter(DATE_YEAR_LIKE);
	}
	
	@Test
	public void testSearchSemesterByFilterYearAndNotMatchedThenReturnEmptyLists(){
		var semesterFilterDto = new SemesterFilterDTO(DATE_YEAR_LIKE_NOT_MATCH, null);
	    assertThat(service.searchSemestersByFilter(semesterFilterDto), is(Collections.emptyList()));
	    verify(repository).searchSemestersByFilter(DATE_YEAR_LIKE_NOT_MATCH);
	    verify(repository, times(1)).searchSemestersByFilter(DATE_YEAR_LIKE_NOT_MATCH);
	}
	
	@Test
	public void testSearchSemesterByNameAndNotFoundThenReturnException(){
		ex.expect(SemesterNotFoundException.class);
		ex.expectMessage("Semester not found");
		service.searchSemestersByFilter(null);
	    verify(repository, never()).searchSemestersByFilter(eq(any()));
	}
}
