package unq.edu.li.pdes.unqpremium.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SemesterTest {

	private static final Long ID = 1L;
	private static final Long OTHER_ID = 10L;
	private static final SemesterType FIRST_SEMESTER_TYPE = SemesterType.FIRST;
	private Semester semester;
	private Semester otherSemester;
	
	@Before
	public void setUp() throws ParseException{
		String dateFromString = "2022-13-03";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");      
		Date fromDate = formatter.parse(dateFromString);  
		String toDateString = "2022-25-06";
		Date toDate = formatter.parse(toDateString);  
		semester = new Semester();
		semester.setId(ID);
		semester.setSemesterType(FIRST_SEMESTER_TYPE);
		semester.setFromDate(fromDate);
		semester.setToDate(toDate);
		otherSemester = new Semester();
		otherSemester.setId(OTHER_ID);
		otherSemester.setSemesterType(FIRST_SEMESTER_TYPE);
		otherSemester.setFromDate(fromDate);
		otherSemester.setToDate(toDate);
	}
	
	@Test
	public void testAccessors(){
		assertThat(semester.getId(), is(ID));
		assertThat(semester.getSemesterType(), is(FIRST_SEMESTER_TYPE));
		assertNotNull(semester.toString());
		assertNotNull(semester.hashCode());
		assertFalse(semester.equals(otherSemester));
	}
}
