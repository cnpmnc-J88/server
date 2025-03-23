package my.app.models;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "assessment")
public class Assessment {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAssID() {
        return assID;
    }

    public void setAssID(Long assID) {
        this.assID = assID;
    }
}
