package my.app.repository;


import my.app.models.Form;
import my.app.models.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<Label, String> {
    List<Label> findByForm(Form form);
}
