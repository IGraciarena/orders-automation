package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "url",
        "text"
})
public class Support {
    @JsonProperty("url")
    private String url;
    @JsonProperty("text")
    private String text;
}
