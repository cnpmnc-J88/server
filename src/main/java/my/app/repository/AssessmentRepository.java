package my.app.repository;

import my.app.models.Answer;
import my.app.models.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssessmentRepository  extends JpaRepository<Assessment, Long> {
    List<Assessment> findAllByForm_Id(Integer formId);
}
