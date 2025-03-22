package my.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "form")
@Getter
@NoArgsConstructor
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
}
