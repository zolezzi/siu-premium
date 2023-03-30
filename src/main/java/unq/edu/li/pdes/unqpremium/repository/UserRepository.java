package unq.edu.li.pdes.unqpremium.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unq.edu.li.pdes.unqpremium.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findOneByEmail(String email);
}
