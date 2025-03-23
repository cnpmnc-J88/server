package my.app.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import my.app.models.Form;
import my.app.repository.FormRepository;
import my.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/form")
public class FormController {
    @Autowired
    FormRepository formRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getForms(
            @RequestParam(defaultValue = "0") Integer page
    ) {
        Pageable pageable = PageRequest.of(page, 10);
        List<Form> forms = formRepository.findAll(pageable).getContent();

        return ResponseEntity.ok(forms);
    }

    @PostMapping("")
    public ResponseEntity<?> form(
            @RequestParam(required=true) String form_name,
            @RequestParam(required=true) String form_description,
            HttpServletRequest request
    ) {
        try {
            String email = (String) request.getAttribute("email");

            Form form = new Form(
                    form_name,
                    form_description,
                    userRepository.findByEmail(email).orElseThrow(
                            () -> new Exception("User not found with email: " + email)
                    )
            );

            formRepository.save(form);

            return ResponseEntity.ok(form);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
