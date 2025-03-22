package my.app.DTO;

import lombok.Getter;
import lombok.Setter;
import my.app.model.Form;

import java.util.List;
@Setter
@Getter
public class AssessmentDTO {
    public class Object {
        private String key;
        private String value;
    }

    List<Object> ans;
}
