package my.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;
}
