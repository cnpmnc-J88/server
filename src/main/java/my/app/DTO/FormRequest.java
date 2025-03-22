package my.app.DTO;

public class FormRequest {
    private String title;
    private String description;

    // Constructor
    public FormRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getter cho title
    public String getTitle() {
        return title;
    }

    // Setter cho title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter cho description
    public String getDescription() {
        return description;
    }

    // Setter cho description
    public void setDescription(String description) {
        this.description = description;
    }
}
