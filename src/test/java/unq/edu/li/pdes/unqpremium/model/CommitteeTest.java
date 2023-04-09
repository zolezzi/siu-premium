package unq.edu.li.pdes.unqpremium.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommitteeTest {
	private static final Long ID = 1L;
	private static final String DAYS_CLASS = "Lunes y martes de 18 a 22";
	private static final Long OTHER_ID = 10L;
	
	private Committee committee;
	private Committee otherCommittee;
	
	@Before
	public void setUp() {
		otherCommittee = new Committee();
		otherCommittee.setId(OTHER_ID);
		otherCommittee.setDaysClass(DAYS_CLASS);
		committee = new Committee();
		committee.setId(ID);
		committee.setDaysClass(DAYS_CLASS);
		committee.setProfessors(List.of());
		committee.setStudents(List.of());
	}
	
	@Test
	public void testAccessors(){
		assertThat(committee.getId(), is(ID));
		assertThat(committee.getDaysClass(), is(DAYS_CLASS));
		assertNotNull(committee.getProfessors());
		assertNotNull(committee.getStudents());
		assertNotNull(committee.toString());
		assertNotNull(committee.hashCode());
		assertFalse(committee.equals(otherCommittee));
	}
}
