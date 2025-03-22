package my.app.model;

import jakarta.persistence.*;

@Entity(name = "labels")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Form form;

    private String content;
}
