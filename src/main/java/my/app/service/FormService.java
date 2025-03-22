package my.app.service;

import lombok.AllArgsConstructor;
import my.app.DTO.FormRequest;

import my.app.helper.GenTestUser;
import my.app.model.Form;
import my.app.repository.IFormRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FormService {
    private IFormRepository formRepository;

    public ResponseEntity<?> createForm(FormRequest formRequest){
        Form form = new Form();
        form.setTitle(formRequest.getTitle());
        form.setDescription(formRequest.getDescription());
        form.setUser(GenTestUser.genUser());
        formRepository.save(form);

        return ResponseEntity.ok(form);
    }
}
