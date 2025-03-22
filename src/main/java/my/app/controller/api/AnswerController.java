package my.app.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import my.app.dto.AnswerRequest;
import my.app.models.*;
import my.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ans")
public class AnswerController {
    @Autowired
    LabelRepository labelRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    private FormRepository formRepository;

    @PostMapping("/{form_id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createAnswer(
            @PathVariable String form_id,
            @RequestParam List<Integer> qId,
            @RequestParam List<String> ansValue,
            HttpServletRequest request
    ) throws Exception {
            String email = (String) request.getAttribute("email");
            User user = userRepository.findByEmail(email).orElseThrow(() -> new Exception("User not found with email: " + email));
            Form form = formRepository.findById(Integer.parseInt(form_id)).orElseThrow(() -> new Exception("Form not found with ID: " + form_id));

            History history = new History(form, user);
            historyRepository.save(history);

            List<Map<String, Object>> answers = new ArrayList<>();
            for (int i = 0; i < qId.size(); i++) {
                int finalI = i;
                Label label = labelRepository.findById(String.valueOf(qId.get(i)))
                        .orElseThrow(() -> new Exception("Label not found with ID: " + qId.get(finalI)));

                answerRepository.save(new Answer(label, ansValue.get(i), history));

                answers.add(Map.of(
                        "questionId", qId.get(i),
                        "answerValue", ansValue.get(i)
                ));
            }

            return ResponseEntity.ok(history);
    }
}
