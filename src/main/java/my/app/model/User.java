package my.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Getter

public class User {
    @Id
    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;


    @OneToMany
    List<Form>  forms;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // Constructor không tham số
    public User() {
    }
}
