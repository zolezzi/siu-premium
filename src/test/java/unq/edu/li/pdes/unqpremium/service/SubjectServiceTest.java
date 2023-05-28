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

import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.exception.DegreeNotFoundException;
import unq.edu.li.pdes.unqpremium.exception.SubjectNotFoundException;
import unq.edu.li.pdes.unqpremium.exception.UnqPremiumException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Degree;
import unq.edu.li.pdes.unqpremium.model.Subject;
import unq.edu.li.pdes.unqpremium.repository.DegreeReportRepository;
import unq.edu.li.pdes.unqpremium.repository.DegreeRepository;
import unq.edu.li.pdes.unqpremium.repository.SubjectRepository;
import unq.edu.li.pdes.unqpremium.service.impl.SubjectServiceImpl;
import unq.edu.li.pdes.unqpremium.vo.SubjectVO;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTest {
	
	private static final Long ID = 1L;
	private static final Long ID_DEGREE = 2L;
	private static final Long ID_DEGREE_NEW = 12L;
	private static final Long ID_DEGREE_WITH_SUBJECT = 3L;
	private static final Long ID_SUBJECT_NOT_FOUND = 10L;
	private static final Long ID_DEGREE_NOT_FOUND = -1L;
	private static final Long ID_SUBJECT_DELETE = 2L;
	private static final Long ID_DEGREE_DELETE = 2L;
	private static final Long ID_SUBJECT_DELETE_NOT_FOUND = 10L;
	private static final Long ID_DEGREE_DELETE_NOT_FOUND = 10L;
	private static final String NAME = "TEST1";
	private static final String NAME_UPDATE = "TEST2";

	@Mock
	private Subject subject;
	
	@Mock
	private Degree degree;
	
	@Mock
	private SubjectDTO subjectDto;
	
	@Mock
	private SubjectRepository repository;
	
	@Mock
	private DegreeRepository degreeRepository;
	
	@Mock
	private DegreeReportRepository degreeReportRepository;
	
	@Mock
	private Mapper mapper;
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@InjectMocks
	private SubjectServiceImpl service;
	
	@Before
	public void setUp(){
		service = new SubjectServiceImpl(repository, degreeRepository, degreeReportRepository, mapper);
		when(repository.findById(ID)).thenReturn(Optional.of(subject));
		var degreeAnew = new Degree();
		var subjectANew = new Subject();
		subjectANew.setId(ID);
		List<Subject> list = new ArrayList<>();
		list.add(subject);
		degreeAnew.setSubjects(list);
		when(degreeRepository.findById(ID_DEGREE)).thenReturn(Optional.of(degreeAnew));
		when(degreeRepository.findById(ID_DEGREE_NEW)).thenReturn(Optional.of(degree));
		when(degreeRepository.findById(ID_DEGREE_WITH_SUBJECT)).thenReturn(Optional.of(degree));
		when(subject.getId()).thenReturn(ID);
		when(subject.getName()).thenReturn(NAME);
		when(mapper.map(eq(subject), eq(SubjectDTO.class))).thenReturn(subjectDto);
		when(mapper.map(any(), eq(Subject.class))).thenReturn(subject);
		when(repository.findById(ID_SUBJECT_DELETE)).thenReturn(Optional.of(subject));
		when(repository.save(subject)).thenReturn(subject);
	}
	
	@Test
	public void testFindSubjectByIdThenReturnASubject(){
		SubjectDTO subjectResponse = service.findById(ID);
	    assertThat(subjectResponse, is(subjectDto));
	    verify(repository).findById(eq(ID));
	}
	
	@Test
	public void testFindSubjectByIdAndNotFoundThenReturnException(){
		ex.expect(SubjectNotFoundException.class);
		ex.expectMessage(String.format("Subject not found with id:%s", ID_SUBJECT_NOT_FOUND) );
		service.findById(ID_SUBJECT_NOT_FOUND);
		verify(repository, never()).findById(eq(ID_SUBJECT_NOT_FOUND));
	}
	
	@Test
	public void testDeleteSubjectById(){
		service.deleteById(ID_DEGREE, ID);
	    verify(repository).delete(any(Subject.class));
	}
	
	@Test
	public void testDeleteSubjectForDegreeByIdWithoutSubjectThenReturnException(){
		ex.expect(UnqPremiumException.class);
		ex.expectMessage(String.format("No found subject with id :%s, for degree with id:%s ", ID_SUBJECT_DELETE, ID_DEGREE_DELETE));
		service.deleteById(ID_DEGREE_DELETE, ID_SUBJECT_DELETE);
	    verify(repository).delete(any(Subject.class));
	}
	
	@Test
	public void testDeleteSubjectByIdAndNotFoundThenReturnException(){
		ex.expect(DegreeNotFoundException.class);
		ex.expectMessage(String.format("No found degree:%s", ID_DEGREE_DELETE_NOT_FOUND));
		service.deleteById(ID_DEGREE_DELETE_NOT_FOUND, ID);
		verify(degreeRepository).save(eq(degree));
		verify(repository).delete(any(Subject.class));
	}
	
	@Test
	public void testDeleteSubjectAndNotFoundDegreeThenReturnException(){
		ex.expect(SubjectNotFoundException.class);
		ex.expectMessage(String.format("Subject not found with id:%s", ID_SUBJECT_NOT_FOUND) );
		service.deleteById(ID_DEGREE_DELETE_NOT_FOUND, ID_SUBJECT_DELETE_NOT_FOUND);
		verify(repository, never()).findById(eq(ID_SUBJECT_NOT_FOUND));
	}

	@Test
	public void testSaveSubject(){
		var subjectVO = new SubjectVO();
		subjectVO.setName(NAME);
		subjectVO.setDegreeId(ID_DEGREE_NEW);
	    assertThat(service.save(subjectVO), is(subjectDto));
	    verify(repository).save(eq(subject));
	    verify(degreeRepository).findById(eq(ID_DEGREE_NEW));
	    verify(degreeRepository).save(eq(degree));
	}
	
	@Test
	public void testSaveSubjectWithNotFoundDegreeThenReturnException(){
		ex.expect(DegreeNotFoundException.class);
		ex.expectMessage(String.format("No found degree:%s", ID_DEGREE_NOT_FOUND) );
		var subjectVO = new SubjectVO();
		subjectVO.setName(NAME);
		subjectVO.setDegreeId(ID_DEGREE_NOT_FOUND);
	    assertThat(service.save(subjectVO), is(subjectDto));
	    verify(repository).save(eq(subject));
	    verify(degreeRepository).findById(eq(ID_DEGREE_NEW));
	    verify(degreeRepository).save(eq(degree));
	}
	
	@Test
	public void testSaveSubjectAndNotFoundDegreeThenReturnException(){
		ex.expect(DegreeNotFoundException.class);
		ex.expectMessage(String.format("No found degree:%s", ID_DEGREE_NOT_FOUND));
		var subjectVO = new SubjectVO();
		subjectVO.setName(NAME);
		subjectVO.setDegreeId(ID_DEGREE_NOT_FOUND);
	    assertThat(service.save(subjectVO), is(subjectDto));
	    verify(repository).save(eq(subject));
	    verify(degreeRepository).findById(eq(ID_DEGREE_NEW));
	    verify(degreeRepository).save(eq(degree));
	}
	
	@Test
	public void testUpdateSubject(){
		assertThat(subject.getName(), is(NAME));
		subject.setName(NAME_UPDATE);
	    assertThat(service.update(subjectDto, ID_DEGREE, ID), is(subjectDto));
	    verify(repository).save(eq(subject));
	    verify(repository, times(1)).save(subject);
	}
	
	@Test
	public void testUpdateSubjectAndNotFoundThenReturnException(){
		ex.expect(SubjectNotFoundException.class);
		ex.expectMessage("Subject not found");
		service.update(subjectDto,ID_DEGREE, ID_SUBJECT_NOT_FOUND);
	    verify(repository, never()).findById(eq(ID_SUBJECT_NOT_FOUND));
	    verify(repository, never()).save(eq(subject));
	}

	@Test
	public void testUpdateSubjectByIdAndNotFoundThenReturnException(){
		ex.expect(DegreeNotFoundException.class);
		ex.expectMessage(String.format("No found degree:%s", ID_DEGREE_NOT_FOUND));
		service.update(subjectDto, ID_DEGREE_NOT_FOUND, ID);
		verify(repository, never()).findById(eq(ID_SUBJECT_NOT_FOUND));
		verify(degreeRepository, never()).findById(eq(ID_DEGREE_NOT_FOUND));
	}
	
	@Test
	public void testUpdateSubjectAndNotFoundDegreeThenReturnException(){
		ex.expect(UnqPremiumException.class);
		ex.expectMessage(String.format("No found subject with id :%s, for degree with id:%s ", ID, ID_DEGREE_WITH_SUBJECT));
		service.update(subjectDto, ID_DEGREE_WITH_SUBJECT, ID);
		verify(repository, never()).findById(eq(ID));
		verify(degreeRepository, never()).findById(eq(ID_DEGREE_WITH_SUBJECT));
	}
}
