package my.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "label")

@Getter
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "label_name", nullable = false, length = 50)
    private String label_name;

    @ManyToOne
    @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Form form;


    public int getId() {
        return id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Label(
            String label_name ,
            Form form
    ) {
        this.label_name = label_name;
        this.form = form;
    }
    public Label(){}



}
