package unq.edu.li.pdes.unqpremium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unq.edu.li.pdes.unqpremium.model.Semester;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long>{

}
