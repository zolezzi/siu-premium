package unq.edu.li.pdes.unqpremium.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SemesterDegreeSubjectTest {
	private static final Long ID = 1L;
	private static final Long OTHER_ID = 10L;
	
	private SemesterDegreeSubject semesterDegreeSubject;
	private SemesterDegreeSubject otherSemesterDegreeSubject;
	
	@Before
	public void setUp() {
		otherSemesterDegreeSubject = new SemesterDegreeSubject();
		otherSemesterDegreeSubject.setId(OTHER_ID);
		semesterDegreeSubject = new SemesterDegreeSubject();
		semesterDegreeSubject.setId(ID);
		semesterDegreeSubject.setDegree(new Degree());
		semesterDegreeSubject.setSubject(new Subject());
		semesterDegreeSubject.setSemester(new Semester());
	}
	
	@Test
	public void testAccessors(){
		assertThat(semesterDegreeSubject.getId(), is(ID));
		assertNotNull(semesterDegreeSubject.getDegree());
		assertNotNull(semesterDegreeSubject.getSemester());
		assertNotNull(semesterDegreeSubject.getSubject());
		assertNotNull(semesterDegreeSubject.getCommittees());
		assertNotNull(semesterDegreeSubject.toString());
		assertNotNull(semesterDegreeSubject.hashCode());
		assertFalse(semesterDegreeSubject.equals(otherSemesterDegreeSubject));
	}
}
