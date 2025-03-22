package my.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class User {
    @Id
    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

    @OneToMany
    List<Form>  forms;
}
