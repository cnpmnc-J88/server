package my.app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelDTORequest {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
