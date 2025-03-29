package my.app.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import my.app.dto.AUserEmailDTO;
import my.app.dto.AssessmentDTO;
import my.app.service.AddUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AddUserController {
    AddUserService addUserService;
    @PostMapping("/add_user")
    public ResponseEntity<?> create_user(@ModelAttribute AUserEmailDTO aUserEmailDTO,
                                         HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        try {
            return addUserService.adduserservice(aUserEmailDTO,email);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
