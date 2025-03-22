package my.app.repository;

import my.app.model.Form;
import my.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFormRepository extends JpaRepository<Form, Long> {
    Page<Form> findByUser(User user, Pageable pageable);
}
