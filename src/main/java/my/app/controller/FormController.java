package my.app.controller;

import lombok.AllArgsConstructor;
import my.app.DTO.FormRequest;
import my.app.DTO.LabelDTORequest;
import my.app.helper.GenTestUser;
import my.app.model.Form;
import my.app.model.Label;
import my.app.repository.IFormRepository;
import my.app.repository.ILabelRepository;
import my.app.service.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FormController {
   private  FormService formService;
   private IFormRepository formRepository;
   private ILabelRepository labelRepository;

    @PostMapping("/create_form")
    public ResponseEntity<?> createForm(@ModelAttribute FormRequest formRequest){
        return formService.createForm(formRequest);
    }

    @PostMapping("/create_label/{form_id}")
    public ResponseEntity<?> createLabel(
            @PathVariable("form_id") Long form_id,
            @ModelAttribute LabelDTORequest labelDTORequest
    ){
        try {
            if(labelDTORequest.getContent() != null){
                throw new Exception("undified content");
            }

            Form form = formRepository.findById(form_id)
                    .orElseThrow(() -> new RuntimeException("Form không tồn tại"));

            Label label = labelRepository.save(new Label(
                    form,
                    labelDTORequest.getContent()
            ));

            return ResponseEntity.ok().body(label);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


//@RequestMapping("/api/forms")
//public class FormController {
//
//    @Autowired
//    private FormService formService;
//
//    @PostMapping("/create")
//    public Form createForm() {
//        return formService.createForm();
//    }