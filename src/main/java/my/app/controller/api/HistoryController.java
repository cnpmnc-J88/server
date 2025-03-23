package my.app.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import my.app.models.Answer;
import my.app.models.History;
import my.app.models.User;
import my.app.repository.AnswerRepository;
import my.app.repository.HistoryRepository;
import my.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

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

    @GetMapping("/{history_id}")
    public ResponseEntity<?> getAnswers(
            @PathVariable String history_id
    ) {
        try {
            History history = historyRepository.findById(Integer.parseInt(history_id))
                    .orElseThrow(() -> new Exception("history not found with ID:"));

            List<Answer> answer = answerRepository.getByHistory(history);

            return ResponseEntity.ok(answer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
