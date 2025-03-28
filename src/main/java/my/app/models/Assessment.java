package my.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name = "assessment")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assID")
    private Long assID;
    @ManyToOne
    @JoinColumn(name = "formID", nullable = false)
    private Form form;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "evaluation_date")
    private LocalDate evaluationDate;

    @Column(name = "comment")
    private String comment;


    @Column(name = "status")
    private String status;

    @Column(name = "assessor_position")
    private String assessor_position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ass_owner", referencedColumnName = "email", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty("ass_owner")  // Đảm bảo rằng trường này được serialize
    private User user;


    public Long getAssID() {
        return assID;
    }



    public void setAssID(Long assID) {
        this.assID = assID;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Assessment(

            User user
    ) {

        this.user = user;
    }

    public Assessment() {
    }
}

