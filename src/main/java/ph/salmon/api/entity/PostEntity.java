package ph.salmon.api.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PostEntity {
public List<DataItem> dataItemList;

}
