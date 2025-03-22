package my.app.controller;

import lombok.AllArgsConstructor;
import my.app.DTO.AssessmentDTO;
import my.app.DTO.FormRequest;
import my.app.service.AssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get_assessment")
    public ResponseEntity<?> getAssessment(@RequestParam Long formID){
        return assessmentService.getAssessment(formID);
    }
}
