package my.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import my.app.models.User;

import java.util.List;

@Getter
@Setter
public class AssessmentDTO {

    @JsonProperty("assID")
    private Long assID;

    @JsonProperty("formId")
    private Integer formId;

    @JsonProperty("rating")
    private Long rating;

    @JsonProperty("evaluationDate")
    private String evaluationDate;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("status")
    private String status;

    @JsonProperty("form_email")
    private String formemail;

    @JsonProperty("former_position")
    private String formerposition;

    @JsonProperty("labelNames")
    private List<String> labelNames;

    @JsonProperty("answers")
    private List<String> answers;

    @JsonProperty("assessor_position")
    private String assessor_position;

    @JsonProperty("assessor_email")
    private String email;
    // Constructor
    public AssessmentDTO(Long assID, Integer formId, Long rating, String evaluationDate, String comment, String status, String email, String assessorposition, List<String> labelNames, List<String> answers ){
        this.assID = assID;
        this.formId = formId;
        this.rating = rating;
        this.evaluationDate = evaluationDate;
        this.comment = comment;
        this.status = status;
        this.email = email;
        this.assessor_position = assessorposition;
        this.labelNames = labelNames;
        this.answers = answers;

    }



}
