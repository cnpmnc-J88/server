package my.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FormController {
    @PostMapping("/create_form")
    public ResponseEntity<?> creatForm{

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