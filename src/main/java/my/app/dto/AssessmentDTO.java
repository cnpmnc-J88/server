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

    @JsonProperty("image")
    private String image;

    public Long getAssID() {
        return assID;
    }

    public void setAssID(Long assID) {
        this.assID = assID;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(String evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(List<String> labelNames) {
        this.labelNames = labelNames;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @JsonProperty("labelNames")
    private List<String> labelNames;

    @JsonProperty("answers")
    private List<String> answers;

    @JsonProperty("form_ower_email")
    private String email;
    // Constructor
    public AssessmentDTO(Long assID, Integer formId, Long rating, String evaluationDate, String comment, String status, String email, List<String> labelNames, List<String> answers ){
        this.assID = assID;
        this.formId = formId;
        this.rating = rating;
        this.evaluationDate = evaluationDate;
        this.comment = comment;
        this.status = status;
        this.email = email;
        this.labelNames = labelNames;
        this.answers = answers;

    }



}
