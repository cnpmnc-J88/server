package my.app.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import my.app.models.History;
import my.app.models.User;
import my.app.repository.HistoryRepository;
import my.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getHistory(@RequestParam(defaultValue = "0") Integer page,
            HttpServletRequest request
    ) {
        try {
            String email = (String) request.getAttribute("email");
            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new Exception("User not found with email: " + email)
            );

            Pageable pageable = PageRequest.of(page, 10);
            Page<History> history = historyRepository.findByUser(user, pageable);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
