package my.app.controller;

import lombok.AllArgsConstructor;
import my.app.DTO.FormRequest;
import my.app.service.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FormController {
   private  FormService formService;

    @PostMapping("/create_form")
    public ResponseEntity<?> createForm(@ModelAttribute FormRequest formRequest){
        return formService.createForm(formRequest);
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