package my.app.model;

import jakarta.persistence.*;

@Entity(name = "labels")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private Form form;

    private String content;

    public Label(
            Form form,
            String content
    ) {
        this.form = form;
        this.content = content;
    }

    public Label() {

    }
}
