package unq.edu.li.pdes.unqpremium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unq.edu.li.pdes.unqpremium.dto.SemesterDegreeSubjectDTO;
import unq.edu.li.pdes.unqpremium.model.SemesterDegreeSubject;

public interface SemesterDegreeSubjectRepository extends JpaRepository<SemesterDegreeSubject, Long>{

	@Query("SELECT sds FROM SemesterDegreeSubject sds WHERE sds.semester.id = :semesterId")
	public List<SemesterDegreeSubject> findBySemesterId(@Param("semesterId") Long semesterId);

	@Query("SELECT sds FROM SemesterDegreeSubject sds WHERE 1=1 AND (UPPER(sds.degree.name) LIKE (:likeName) OR UPPER(sds.subject.name) LIKE UPPER(:likeName)) AND (COALESCE(:degreeIds, NULL) = '' OR sds.degree.id IN (:degreeIds) OR (COALESCE(:subjectIds, NULL) = '' OR sds.subject.id IN (:subjectIds))) ")
	public List<SemesterDegreeSubjectDTO> searchByFilter(@Param("likeName") String likeName, @Param("degreeIds") List<Long> degreeIds,
			@Param("subjectIds") List<Long> subjectIds);
}
