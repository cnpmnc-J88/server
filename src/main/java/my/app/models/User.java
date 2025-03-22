package my.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.persistence.Column;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "display_name", nullable = false, length = 50)
    private String displayName;
}
