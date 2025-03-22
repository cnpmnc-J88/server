package my.app.service;

import lombok.AllArgsConstructor;
import my.app.DTO.FormRequest;
import my.app.model.Form;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FormService {

    public ResponseEntity<?> createForm(FormRequest formRequest){
        Form form = new Form();
        form.setTitle(formRequest.getTitle());
        form.setDescription(formRequest.getDescription());
        form
        return ResponseEntity.ok(form);



    }
}
