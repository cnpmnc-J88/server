package my.app.DTO;

import lombok.Getter;
import lombok.Setter;
import my.app.model.Form;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
public class AssessmentDTO {
    public class Object {
        private String key;
        private String value;
    }

    List<Object> ans;


    private String employeeName;
    private String position;
    private LocalDateTime evaluationDate;
    private Double rating;
    private List<String> criteria;
    private String status;
    private String comment;
    private String image; // hình của nhân viên


}
