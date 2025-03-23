package my.app.repository;

import my.app.models.Form;
import my.app.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Integer> {
    Page<Form> findAll(Pageable pageable);

    List<Form> findByUser(User user);
}
