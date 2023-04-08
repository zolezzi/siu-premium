package unq.edu.li.pdes.unqpremium.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "semester_degree_subject")
public class SemesterDegreeSubject {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "semester_id", referencedColumnName = "id")
	private Semester semester;
	
	@OneToOne
    @JoinColumn(name = "degree_id", referencedColumnName = "id")
	private Degree degree;
	
	@OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
	private Subject subject;
	
	@OneToMany
    @JoinColumn(name = "committee_id")
	private List<Committee> committees;
}
