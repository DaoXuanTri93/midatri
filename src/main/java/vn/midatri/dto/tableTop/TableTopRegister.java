package vn.midatri.dto.tableTop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TableTopRegister {
    private Long id;
    private Boolean status;
    private Instant createAt;
    private Instant updateAt;
    private Short capacity;
    private String content;
}
