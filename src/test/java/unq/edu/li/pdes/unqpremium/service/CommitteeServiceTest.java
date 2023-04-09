package unq.edu.li.pdes.unqpremium.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import unq.edu.li.pdes.unqpremium.dto.CommitteeDTO;
import unq.edu.li.pdes.unqpremium.exception.CommitteeNotFoundException;
import unq.edu.li.pdes.unqpremium.exception.UnqPremiumException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Committee;
import unq.edu.li.pdes.unqpremium.model.SemesterDegreeSubject;
import unq.edu.li.pdes.unqpremium.repository.CommitteeRepository;
import unq.edu.li.pdes.unqpremium.repository.SemesterDegreeSubjectRepository;
import unq.edu.li.pdes.unqpremium.service.impl.CommitteeServiceImpl;
import unq.edu.li.pdes.unqpremium.vo.CommitteeVO;

@RunWith(MockitoJUnitRunner.class)
public class CommitteeServiceTest {

	private static final Long ID = 1L;
	private static final Long ID_SEMESTER_DEGREE_SUBJECT = 2L;
	private static final Long ID_SEMESTER_DEGREE_SUBJECT_NEW = 12L;
	private static final Long ID_SEMESTER_DEGREE_SUBJECT_WITH_COMMITTEES = 3L;
	private static final Long ID_COMMITTEE_NOT_FOUND = 10L;
	private static final Long ID_SEMESTER_DEGREE_SUBJECT_NOT_FOUND = -1L;
	private static final Long ID_COMMITTEE_DELETE = 2L;
	private static final Long ID_COMMITTEE_UPDATE = 3L;
	private static final Long ID_SEMESTER_DEGREE_SUBJECT_DELETE = 2L;
	private static final String DAYS_CLASS = "TEST1";
	private static final String DAYS_CLASS_UPDATE = "TEST2";

	@Mock
	private Committee committee;
	
	@Mock
	private SemesterDegreeSubject semesterDegreeSubject;
	
	@Mock
	private CommitteeDTO committeeDto;
	
	@Mock
	private CommitteeRepository repository;
	
	@Mock
	private SemesterDegreeSubjectRepository semesterDegreeSubjectRepository;
	
	@Mock
	private Mapper mapper;
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@InjectMocks
	private CommitteeServiceImpl service;
	
	@Before
	public void setUp(){
		service = new CommitteeServiceImpl(repository, semesterDegreeSubjectRepository, mapper);
		when(repository.findById(ID)).thenReturn(Optional.of(committee));
		when(repository.findById(ID_COMMITTEE_NOT_FOUND)).thenReturn(Optional.empty());
		when(mapper.map(any(), eq(Committee.class))).thenReturn(committee);
		when(mapper.map(any(), eq(CommitteeDTO.class))).thenReturn(committeeDto);
		when(mapper.map(eq(committee), eq(CommitteeDTO.class))).thenReturn(committeeDto);
		var aNewSemesterDegreeSubject = new SemesterDegreeSubject();
		var aNewCommittee = new Committee();
		aNewCommittee.setId(ID);
		List<Committee> committees = new ArrayList<>();
		committees.add(aNewCommittee);
		aNewSemesterDegreeSubject.setCommittees(committees);
		when(committee.getDaysClass()).thenReturn(DAYS_CLASS);
		when(repository.findById(ID_COMMITTEE_DELETE)).thenReturn(Optional.of(aNewCommittee));
		when(repository.findById(ID_COMMITTEE_UPDATE)).thenReturn(Optional.of(aNewCommittee));
		when(semesterDegreeSubjectRepository.findById(ID_SEMESTER_DEGREE_SUBJECT)).thenReturn(Optional.of(aNewSemesterDegreeSubject));
		when(semesterDegreeSubjectRepository.findById(ID_SEMESTER_DEGREE_SUBJECT_NEW)).thenReturn(Optional.of(semesterDegreeSubject));
		when(semesterDegreeSubjectRepository.findById(ID_SEMESTER_DEGREE_SUBJECT_WITH_COMMITTEES)).thenReturn(Optional.of(semesterDegreeSubject));
		
	}
	
	@Test
	public void testFindCommitteeByIdThenReturnACommittee(){
		var committeeResult = service.findById(ID);
	    assertThat(committeeResult, is(committeeDto));
	    verify(repository).findById(eq(ID));
	}
	
	@Test
	public void testFindCommitteeByIdAndNotFoundThenReturnException(){
		ex.expect(CommitteeNotFoundException.class);
		ex.expectMessage(String.format("Committee not found with id:%s", ID_COMMITTEE_NOT_FOUND) );
		service.findById(ID_COMMITTEE_NOT_FOUND);
		verify(repository, never()).findById(eq(ID_COMMITTEE_NOT_FOUND));
	}
	
	@Test
	public void testDeleteCommitteeById(){
		service.deleteById(ID_SEMESTER_DEGREE_SUBJECT, ID_COMMITTEE_DELETE);
	    verify(repository).delete(any(Committee.class));
	}
	
	@Test
	public void testDeleteCommitteeByIdWithoutRelationshipSemesterDegreeSubjectThenReturnException(){
		ex.expect(UnqPremiumException.class);
		ex.expectMessage(String.format("No found relationship with semester, degree or subject with:%s ", ID_SEMESTER_DEGREE_SUBJECT_NOT_FOUND));
		service.deleteById(ID_SEMESTER_DEGREE_SUBJECT_NOT_FOUND, ID_COMMITTEE_DELETE);
	    verify(repository).delete(any(Committee.class));
	}
	
	@Test
	public void testCommitteeTheARelationshipSemesterDegreeSubjectItIsNotOnYourListThenReturnException(){
		ex.expect(UnqPremiumException.class);
		ex.expectMessage(String.format("No found committee with id :%s and not found relationship with semester, degree or subject with:%s ", ID, ID_SEMESTER_DEGREE_SUBJECT_DELETE));
		service.deleteById(ID_SEMESTER_DEGREE_SUBJECT_DELETE, ID);
	    verify(repository).delete(any(Committee.class));
	}
	
	@Test
	public void testSaveCommittee(){
		var committeeVO = new CommitteeVO();
		committeeVO.setDaysClass(DAYS_CLASS);
		committeeVO.setSemesterDegreeSubjectId(ID_SEMESTER_DEGREE_SUBJECT_NEW);
	    assertThat(service.save(committeeVO), is(committeeDto));
	    verify(repository).save(eq(committee));
	    verify(semesterDegreeSubjectRepository).findById(eq(ID_SEMESTER_DEGREE_SUBJECT_NEW));
	    verify(semesterDegreeSubjectRepository).save(eq(semesterDegreeSubject));
	}
	
	@Test
	public void testSaveCommitteeWithNotFoundSemesterDegreeSubjectThenReturnException(){
		ex.expect(UnqPremiumException.class);
		ex.expectMessage(String.format("No found relationship with semester, degree or subject with:%s ", ID_SEMESTER_DEGREE_SUBJECT_NOT_FOUND));
		var committeeVO = new CommitteeVO();
		committeeVO.setDaysClass(DAYS_CLASS);
		committeeVO.setSemesterDegreeSubjectId(ID_SEMESTER_DEGREE_SUBJECT_NOT_FOUND);
	    assertThat(service.save(committeeVO), is(committeeDto));
	    verify(repository).save(eq(committee));
	    verify(semesterDegreeSubjectRepository).findById(eq(ID_SEMESTER_DEGREE_SUBJECT_NEW));
	    verify(semesterDegreeSubjectRepository).save(eq(semesterDegreeSubject));
	}
	
	@Test
	public void testUpdateCommittee(){
		assertThat(committee.getDaysClass(), is(DAYS_CLASS));
		committee.setDaysClass(DAYS_CLASS_UPDATE);
	    assertThat(service.update(committeeDto, ID_SEMESTER_DEGREE_SUBJECT, ID_COMMITTEE_UPDATE), is(committeeDto));
	    verify(repository).save(eq(committee));
	    verify(repository, times(1)).save(committee);
	}
	
	@Test
	public void testUpdateCommitteeByIdAndNotFoundThenReturnException(){
		ex.expect(UnqPremiumException.class);
		ex.expectMessage(String.format("No found relationship with semester, degree or subject with:%s ", ID_SEMESTER_DEGREE_SUBJECT_NOT_FOUND));
		service.update(committeeDto, ID_SEMESTER_DEGREE_SUBJECT_NOT_FOUND, ID);
		verify(repository, never()).findById(eq(ID_SEMESTER_DEGREE_SUBJECT_NOT_FOUND));
		verify(semesterDegreeSubjectRepository, never()).findById(eq(ID_SEMESTER_DEGREE_SUBJECT_NOT_FOUND));
	}
	
	@Test
	public void testUpdateCommitteeAndNotFoundSemesterDegreeSubjectThenReturnException(){
		ex.expect(UnqPremiumException.class);
		ex.expectMessage(String.format("No found committee with id :%s and not found relationship with semester, degree or subject with:%s ", ID, ID_SEMESTER_DEGREE_SUBJECT_WITH_COMMITTEES));
		service.update(committeeDto, ID_SEMESTER_DEGREE_SUBJECT_WITH_COMMITTEES, ID);
		verify(repository, never()).findById(eq(ID));
		verify(semesterDegreeSubjectRepository, never()).findById(eq(ID_SEMESTER_DEGREE_SUBJECT_WITH_COMMITTEES));
	}
}
