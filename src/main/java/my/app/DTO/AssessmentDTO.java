package my.app.DTO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import my.app.model.Form;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
public class AssessmentDTO {
//    public class Object {
//        private String key;
//        private String value;
//    }
//
//    List<Object> ans;
   private Integer id;

//    public List<Object> getAns() {
//        return ans;
//    }
//
//    public void setAns(List<Object> ans) {
//        this.ans = ans;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDateTime evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<String> criteria) {
        this.criteria = criteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String employeeName;
    private String position;
    private LocalDateTime evaluationDate;
    private double rating;


    private List<String> criteria;

    private String status;
    private String comment;
    private String image;

}
