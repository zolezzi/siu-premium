package unq.edu.li.pdes.unqpremium.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SubjectTest {

	private static final Long ID = 1L;
	private static final Long OTHER_ID = 10L;
	private static final String NAME = "TEST";
	private Subject subject;
	private Subject otherSubject;
	
	@Before
	public void setUp() throws ParseException{
		subject = new Subject();
		subject.setId(ID);
		subject.setName(NAME);
		otherSubject = new Subject();
		otherSubject.setId(OTHER_ID);
		otherSubject.setName(NAME);
	}
	
	@Test
	public void testAccessors(){
		assertThat(subject.getId(), is(ID));
		assertThat(subject.getName(), is(NAME));
		assertNotNull(subject.toString());
		assertNotNull(subject.hashCode());
		assertFalse(subject.equals(otherSubject));
	}
}
