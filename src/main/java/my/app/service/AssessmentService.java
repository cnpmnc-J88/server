package my.app.service;

import lombok.AllArgsConstructor;
import my.app.DTO.AssessmentDTO;
import my.app.DTO.FormRequest;
import my.app.model.Assessment;
import my.app.model.Form;
import my.app.repository.IAssessmentRepository;
import my.app.repository.IFormRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentService {
    private final IAssessmentRepository assessmentRepository;
    private final IFormRepository formRepository;

    public AssessmentService(IAssessmentRepository assessmentRepository, IFormRepository formRepository) {
        this.assessmentRepository = assessmentRepository;
        this.formRepository = formRepository;
    }

    public ResponseEntity<?> createAssessment(Long formID){
        Optional<Form> existingForm = formRepository.findById(formID);
        if (existingForm.isPresent()) {
            Form form = existingForm.get();
            // Xử lý logic tạo Assessment
            Assessment assessment = new Assessment();
            assessment.setForm(form);
            assessmentRepository.save(assessment);
            return ResponseEntity.ok("Assessment created successfully");
        } else {
            return ResponseEntity.status(404).body("Form not found");
        }



    }

}
