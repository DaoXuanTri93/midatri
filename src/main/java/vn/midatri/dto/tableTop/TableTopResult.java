package vn.midatri.dto.tableTop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.repository.model.TabletopStatus;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class TableTopResult {
    private Long id;
    private Instant createAt;
    private Instant updateAt;
    private String code;
    private TabletopStatus status;
    private Short capacity;
    private String content;
    private Boolean deleted;
}
