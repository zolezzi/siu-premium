package unq.edu.li.pdes.unqpremium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String name;
}
