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


                Assessment assessment = new Assessment(userRepository.findByEmail(email).orElseThrow(
                        () -> new Exception("User not found with email: " + email)
                ));
                assessment.setForm(form);
                assessment.setRating(assessmentDTO.getRating());
                assessment.setEvaluationDate(LocalDate.now());
                assessment.setComment(assessmentDTO.getComment());
                assessment.setStatus(assessmentDTO.getStatus());
                assessment.setAssessor_position(assessmentDTO.getAssessor_position());
                assessmentRepository.save(assessment);

                return ResponseEntity.ok("Assessment created successfully");
            } else {
                return ResponseEntity.status(404).body("Form not found");
            }
        } catch (Exception e) {

            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }


    }


//    public List<Assessment> getAllAssessments() {
//        return assessmentRepository.findAll();
//    }

    public List<AssessmentDTO> getAssessmentsByFormId(Integer formId) {
        //
        List<Assessment> assessments = assessmentRepository.findAllByFormId(formId);
        if (assessments.isEmpty()) {
            return null;
        }

        List<AssessmentDTO> assessmentDTOs = new ArrayList<>();


        for (Assessment assessment : assessments) {
            List<String> labelNames = new ArrayList<>();
            String formOwnerEmail = assessment.getForm().getUser() != null ? assessment.getForm().getUser().getEmail() : null;
            String formerposition = assessment.getForm().getFormer_position();
            List<Label> labels = labelRepository.findByForm_Id(formId);

            for (Label label : labels) {
                labelNames.add(label.getLabel_name());
            }


            List<String> answers = new ArrayList<>();
            for (Label label : labels) {

                List<Answer> labelAnswers = answerRepository.findAllByLabel_Id(label.getId()); // Tìm các Answer liên kết với Label
                for (Answer answer : labelAnswers) {
                    answers.add(answer.getContent());
                }
            }


            AssessmentDTO assessmentDTO = new AssessmentDTO(
                    assessment.getAssID(),
                    assessment.getForm().getId().intValue(),
                    assessment.getRating(),
                    assessment.getEvaluationDate().toString(),
                    assessment.getComment(),
                    assessment.getStatus(),
                    assessment.getUser().getEmail(),
                    assessment.getAssessor_position(),
                    labelNames,
                    answers
            );

            assessmentDTO.setFormemail(formOwnerEmail);
            assessmentDTO.setFormerposition(formerposition);


            assessmentDTOs.add(assessmentDTO);
        }


        return assessmentDTOs;
    }



}
