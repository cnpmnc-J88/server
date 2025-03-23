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
@NoArgsConstructor

public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Đảm bảo cột này trùng với tên cột trong database
    private Long id;

    @Column(name = "form_name", nullable = false, length = 50)
    private String form_name;

    @Column(name = "form_description", nullable = false, length = 50)
    private String form_description;

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

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Assessment> assessments = new ArrayList<>();
}
