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
public class DegreeTest {

	private static final Long ID = 1L;
	private static final String NAME = "TEST";
	private static final Long OTHER_ID = 10L;
	
	private Degree degree;
	private Degree otherDegree;
	
	@Before
	public void setUp() {
		otherDegree = new Degree();
		otherDegree.setId(OTHER_ID);
		otherDegree.setName(NAME);
		degree = new Degree();
		degree.setId(ID);
		degree.setName(NAME);
	}
	
	@Test
	public void testAccessors(){
		assertThat(degree.getId(), is(ID));
		assertThat(degree.getName(), is(NAME));
		assertNotNull(degree.toString());
		assertNotNull(degree.hashCode());
		assertFalse(degree.equals(otherDegree));
	}
}
