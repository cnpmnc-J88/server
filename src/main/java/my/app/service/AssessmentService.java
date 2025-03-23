package my.app.service;

import my.app.dto.AssessmentDTO;
import my.app.models.Assessment;
import my.app.models.Form;
import my.app.models.User;
import my.app.repository.AssessmentRepository;
import my.app.repository.FormRepository;
import my.app.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssessmentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<?> createassessment(Integer formID, AssessmentDTO assessmentDTO, String email){

        Optional<Form> existingForm = formRepository.findById(formID);
        if (existingForm.isPresent()) {
            Form form = existingForm.get();
            // Xử lý logic tạo Assessment
            Optional<User> existingUser = userRepository.findByEmail(email);
            if (existingUser.isEmpty()) {
                return ResponseEntity.status(404).body("User not found with email: " + email);
            }
    
            Assessment assessment = new Assessment();
            assessment.setForm(form);
            assessment.setRating(assessmentDTO.getRating());
            assessment.setEvaluationDate(LocalDate.now());
            assessment.setComment(assessmentDTO.getComment());
            assessment.setStatus(assessmentDTO.getStatus());
            assessmentRepository.save(assessment);
            assessment.setUser(existingUser.get());


            return ResponseEntity.ok("Assessment created successfully");
        } else {
            return ResponseEntity.status(404).body("Form not found");
        }

    }


    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    public Assessment getAssessmentById(Integer id) {
        List<Assessment> assessment = assessmentRepository.findAllByForm_Id(id);
        if (assessment.isEmpty()) {
            return null; // Return null if no assessments found
        }
        return assessment.get(0); // Return the first assessment
    }

}
