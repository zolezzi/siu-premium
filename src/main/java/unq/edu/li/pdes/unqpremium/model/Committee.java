package unq.edu.li.pdes.unqpremium.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "committee")
public class Committee {

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	@JoinTable(
			name = "committee_accounts_professor",
			joinColumns = @JoinColumn(
					name = "professor_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "committee_id", referencedColumnName = "id"))
	private List<Account> professors;
	
	@ManyToMany
	@JoinTable(
			name = "committee_accounts_students",
			joinColumns = @JoinColumn(
					name = "student_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "committee_id", referencedColumnName = "id"))
	
	private List<Account> students;
	
	@Column
	private String daysClass;
	
	//private List<ClassDays> clasessDays; Podriamos cambiar los dias de clases por un objecto asi podemos buscar materias por dia y/o incluso hora
	//List of the groups
}
