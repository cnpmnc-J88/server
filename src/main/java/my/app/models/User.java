package my.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter

public class User {
    @Id
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "display_name", nullable = false, length = 50)
    private String displayName;

    public User(String email, String username) {
        this.email = email;
        this.displayName = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
        // Constructor mặc định
    }
    @OneToMany(mappedBy = "user")  // Liên kết với "user" trong Assessment
    private List<Assessment> assessments;

}
