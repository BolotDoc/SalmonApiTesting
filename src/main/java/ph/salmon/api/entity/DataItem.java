package ph.salmon.api.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class DataItem {
    private int userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;
    private String title;
    private String body;
}
