package my.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AUserEmailDTO {
    @JsonProperty("email")
    private String email;
    @JsonProperty("object")
    private String object;
    @JsonProperty("body")
    private String body;
}
