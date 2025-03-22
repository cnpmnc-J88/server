package my.app.repository;

import my.app.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILabelRepository extends JpaRepository<Label,Long> {
}
