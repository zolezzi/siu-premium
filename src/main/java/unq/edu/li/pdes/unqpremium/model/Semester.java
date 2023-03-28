package unq.edu.li.pdes.unqpremium.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "semester")
public class Semester {

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private SemesterType semesterType;
	
	@Temporal(TemporalType.DATE)
	@Column(name="from_date", nullable=false)
	private Date fromDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="to_date", nullable=false)
	private Date toDate;

}
