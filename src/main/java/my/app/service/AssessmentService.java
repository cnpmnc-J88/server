package my.app.service;

import lombok.AllArgsConstructor;
import my.app.DTO.AssessmentDTO;
import my.app.DTO.FormRequest;
import my.app.model.Assessment;
import my.app.model.Form;
import my.app.repository.IAssessmentRepository;
import my.app.repository.IFormRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    public ResponseEntity<?> getAssessment(Long formID){


        // Đánh giá 1
        AssessmentDTO assessment1 = new AssessmentDTO();
        assessment1.setId(1);
        assessment1.setEmployeeName("Nguyễn Văn A");
        assessment1.setPosition("Nhân viên kinh doanh");
        assessment1.setEvaluationDate(LocalDateTime.of(2024, 3, 20, 0, 0));
        assessment1.setRating(4.5);
        assessment1.setCriteria(Arrays.asList("Bạn làm việc đúng giờ:Đúng giờ",
                "Bạn có kỹ năng giao tiếp tốt: Đúng, tôi giao tiếp hiệu quả",
                "Bạn có khả năng làm việc nhóm: Đúng, tôi hợp tác tốt với đồng nghiệp"));
        assessment1.setStatus("good");
        assessment1.setComment("Thể hiện xuất sắc trong quý này, cần duy trì.");
        assessment1.setImage("https://github.com/shadcn.png");
        return  ResponseEntity.ok(assessment1);

//        if (existingForm.isPresent()) {
//            Form form = existingForm.get();
//            return ResponseEntity.ok(form);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Form not found with ID: " + formID);
//        }

    }
}
