package my.app.repository;

import my.app.models.AEmail;
import my.app.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddUserRepository extends JpaRepository<AEmail, Integer> {
    Optional<AEmail> findAEmailByEmail(String email);
}
