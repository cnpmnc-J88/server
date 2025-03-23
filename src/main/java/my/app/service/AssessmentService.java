package my.app.service;

import my.app.dto.AssessmentDTO;
import my.app.models.*;
import my.app.repository.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private LabelRepository labelRepository;
    public ResponseEntity<?> createassessment(Integer formID, AssessmentDTO assessmentDTO, String email){

        try {
            Optional<Form> existingForm = formRepository.findById(formID);
            if (existingForm.isPresent()) {
                Form form = existingForm.get();

                // Tạo đối tượng Assessment
                Assessment assessment = new Assessment(userRepository.findByEmail(email).orElseThrow(
                        () -> new Exception("User not found with email: " + email)
                ));
                assessment.setForm(form);
                assessment.setRating(assessmentDTO.getRating());
                assessment.setEvaluationDate(LocalDate.now());
                assessment.setComment(assessmentDTO.getComment());
                assessment.setStatus(assessmentDTO.getStatus());
                assessment.setImage(assessmentDTO.getImage());
                assessmentRepository.save(assessment);

                return ResponseEntity.ok("Assessment created successfully");
            } else {
                return ResponseEntity.status(404).body("Form not found");
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }


    }


//    public List<Assessment> getAllAssessments() {
//        return assessmentRepository.findAll();
//    }

    public List<AssessmentDTO> getAssessmentsByFormId(Integer formId) {
        // Lấy danh sách các Assessment theo form_id
        List<Assessment> assessments = assessmentRepository.findAllByFormId(formId);
        if (assessments.isEmpty()) {
            return null; // Trả về null nếu không có Assessment nào
        }

        List<AssessmentDTO> assessmentDTOs = new ArrayList<>();

        // Duyệt qua tất cả các Assessment
        for (Assessment assessment : assessments) {
            // Lấy các Label liên kết với Form từ mỗi Assessment
            List<String> labelNames = new ArrayList<>();
            // Sử dụng findAllByFormId nếu bạn muốn lấy các Label từ Form
            List<Label> labels = labelRepository.findByForm_Id(formId);

            for (Label label : labels) {
                labelNames.add(label.getLabel_name()); // Lấy tên của mỗi label
            }

            // Lấy các Answer dựa trên từng Label
            List<String> answers = new ArrayList<>();
            for (Label label : labels) {
                // Tìm Answer dựa trên label_id
                List<Answer> labelAnswers = answerRepository.findAllByLabel_Id(label.getId()); // Tìm các Answer liên kết với Label
                for (Answer answer : labelAnswers) {
                    answers.add(answer.getContent());  // Lấy nội dung của Answer
                }
            }

            // Chuyển đổi Assessment thành AssessmentDTO
            AssessmentDTO assessmentDTO = new AssessmentDTO(
                    assessment.getAssID(),
                    assessment.getForm().getId().intValue(), // Lấy formId từ Form liên kết
                    assessment.getRating(),
                    assessment.getEvaluationDate().toString(),
                    assessment.getComment(),
                    assessment.getStatus(),
                    assessment.getUser().getEmail(),
                    labelNames,
                    answers
            );

            // Thêm AssessmentDTO vào danh sách trả về
            assessmentDTOs.add(assessmentDTO);
        }

        // Trả về danh sách các AssessmentDTO
        return assessmentDTOs;
    }



}
