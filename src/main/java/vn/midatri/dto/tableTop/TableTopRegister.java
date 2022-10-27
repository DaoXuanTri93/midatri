package vn.midatri.dto.tableTop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.repository.model.TabletopStatus;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TableTopRegister {
    private Long id;
    private TabletopStatus status;
    private String title;
    private Instant createAt;
    private Short capacity;
    private String content;
}
