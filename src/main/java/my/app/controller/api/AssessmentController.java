package my.app.controller.api;

import lombok.AllArgsConstructor;
import my.app.dto.AssessmentDTO;
import my.app.models.Assessment;
import my.app.service.AssessmentService;
import my.app.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AssessmentController {
    @Autowired
    MailService  mailService;

    @Autowired
    private AssessmentService assessmentService;
    @PostMapping("/create_assessment")
    public ResponseEntity<?> create_assessment(@RequestParam Integer formID, 
    @ModelAttribute AssessmentDTO assessmentDTO,
    HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        
        return assessmentService.createassessment(formID,assessmentDTO,email);
    }


    @GetMapping("/getassessment/{id}")
    public ResponseEntity<List<AssessmentDTO>> getAssessmentsByFormId(@PathVariable Integer id) {
        List<AssessmentDTO> assessments = assessmentService.getAssessmentsByFormId(id);
        if (assessments != null && !assessments.isEmpty()) {
            return ResponseEntity.ok(assessments); // Return the list of assessments
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if no assessments are found
        }
    }


}
