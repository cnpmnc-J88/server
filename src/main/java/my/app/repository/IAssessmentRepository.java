package my.app.repository;

import my.app.model.Assessment;
import my.app.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssessmentRepository extends JpaRepository<Assessment, Long> {

}
