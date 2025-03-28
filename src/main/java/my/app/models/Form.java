package my.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "form")
@Getter
@Setter

public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Đảm bảo cột này trùng với tên cột trong database
    private Long id;

    @Column(name = "form_name", nullable = false, length = 50)
    private String form_name;

    @Column(name = "form_description", nullable = false, length = 50)
    private String form_description;

    @Column(name = "former_position")
    private String former_position;

    @ManyToOne
    @JoinColumn(name = "form_owner", referencedColumnName = "email", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
   
    public Form(
            String form_name,
            String form_description,
            User user
    ) {
        this.form_name = form_name;
        this.form_description = form_description;
        this.user = user;
    }
    public Form(){

    }
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Assessment> assessments = new ArrayList<>();

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Label> labels = new ArrayList<>();

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
    public void setlabels(){

    }

    public List<Label> getLabels() {
        return labels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForm_name() {
        return form_name;
    }

    public void setForm_name(String form_name) {
        this.form_name = form_name;
    }

    public String getForm_description() {
        return form_description;
    }

    public void setForm_description(String form_description) {
        this.form_description = form_description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
