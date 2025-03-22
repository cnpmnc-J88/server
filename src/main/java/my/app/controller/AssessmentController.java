package my.app.controller;

import lombok.AllArgsConstructor;
import my.app.DTO.AssessmentDTO;
import my.app.DTO.FormRequest;
import my.app.service.AssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssessmentController {
    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }


    @PostMapping("/create_assessment")
    public ResponseEntity<?> createAssessment(@RequestParam Long formID){
        return assessmentService.createAssessment(formID);
    }
}
