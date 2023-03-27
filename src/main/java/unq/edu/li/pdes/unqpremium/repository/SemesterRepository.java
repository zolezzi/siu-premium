package unq.edu.li.pdes.unqpremium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import unq.edu.li.pdes.unqpremium.model.Semester;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long>{

	@Query("select s from Semester s where (:year is null or (year(s.fromDate) =:year or year(s.toDate) =:year))")
//			+ "and (:semesterType is null or s.semesterType=:semesterType))")
	List<Semester> searchSemestersByFilter(@Param("year") Integer year);

}
