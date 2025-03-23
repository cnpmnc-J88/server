package my.app.controller.api;

import lombok.AllArgsConstructor;
import my.app.dto.AssessmentDTO;
import my.app.models.Assessment;
import my.app.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;
    @PostMapping("/create_assessment")
    public ResponseEntity<?> create_assessment(@RequestParam Integer formID, @ModelAttribute AssessmentDTO assessmentDTO) {
        return assessmentService.createassessment(formID,assessmentDTO);

    }


    @GetMapping("/getassessment/{id}")
    public ResponseEntity<Assessment> getAssessmentById(@PathVariable Integer id) {
        Assessment assessment = assessmentService.getAssessmentById(id);
        if (assessment != null) {
            return ResponseEntity.ok(assessment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
