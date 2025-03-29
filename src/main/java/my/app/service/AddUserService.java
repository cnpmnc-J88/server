package my.app.service;

import lombok.AllArgsConstructor;
import my.app.dto.AUserEmailDTO;
import my.app.models.AEmail;
import my.app.models.Assessment;
import my.app.repository.AddUserRepository;
import my.app.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AddUserService {
    AddUserRepository addUserRepository;
    MailService mailService;
    UserRepository userRepository;
    public ResponseEntity<?> adduserservice(AUserEmailDTO aUserEmailDTO, String email) {
        try {


            Optional<AEmail> aeMail = addUserRepository.findAEmailByEmail(aUserEmailDTO.getEmail());
            if (aeMail.isPresent()) {
                return ResponseEntity.badRequest().body("User already exists");
            } else {
                AEmail aEmail = new AEmail(userRepository.findByEmail(email).orElseThrow(
                        () -> new Exception("User not found with email: " + email)
                ),email);
                aEmail.setEmail(aUserEmailDTO.getEmail());
                mailService.sendEmail(aUserEmailDTO.getEmail(), aUserEmailDTO.getObject(), aUserEmailDTO.getBody());
                addUserRepository.save(aEmail);
                return ResponseEntity.ok("User added successfully");
            }
        } catch (jakarta.mail.MessagingException e) {
            return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
        } catch (java.io.UnsupportedEncodingException e) {
            return ResponseEntity.status(500).body("Encoding error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}

