package vivien.doingthigns.fantasyworldgenerator.config.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vivien.doingthigns.fantasyworldgenerator.usermanagement.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}