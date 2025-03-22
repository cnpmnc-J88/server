package my.app.model;

import jakarta.persistence.*;

@Entity(name = "contents")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Label label;

    private String content;
}
