package unq.edu.li.pdes.unqpremium.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Degree {

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String name;
	
	@Column
	private Boolean isActived = Boolean.TRUE;
	
	@OneToMany
    @JoinColumn(name = "subject_id")
	private List<Subject> subjects = new ArrayList<>();

	public boolean getContainsBySubjectId(Long subjectId) {
		return subjects.stream()
				.map(subject -> subject.getId())
				.collect(Collectors.toList())
				.contains(subjectId);
	}
}
