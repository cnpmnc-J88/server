package my.app.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import my.app.models.Form;
import my.app.models.Label;
import my.app.models.User;
import my.app.repository.FormRepository;
import my.app.repository.LabelRepository;
import my.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/label")
public class LabelController {
    @Autowired
    LabelRepository labelRepository;

    @Autowired
    FormRepository formRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/{form_id}")
    public ResponseEntity<?> label(
            @RequestParam(required = true) String label_name,
            @PathVariable("form_id") String formId,
            HttpServletRequest request
    )
    {
        try {
            String email = (String) request.getAttribute("email");

            Form form = formRepository.findById(Integer.parseInt(formId))
                    .orElseThrow(() -> new Exception("Form not found with ID: " + formId));

            Label label = new Label(label_name, form);
            labelRepository.save(label);

            return ResponseEntity.ok(label);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{form_id}")
    public ResponseEntity<?> getLabels(HttpServletRequest request, @PathVariable("form_id") String formId) {
        try {
            Form form = formRepository.findById(Integer.parseInt(formId))
                    .orElseThrow(() -> new Exception("Form not found with ID: " + formId));

            List<Label> labels = labelRepository.findByForm(form);

            return ResponseEntity.ok(labels);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
