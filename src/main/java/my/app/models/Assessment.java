package my.app.models;

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

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "ass_owner", referencedColumnName = "email", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    

   
}
