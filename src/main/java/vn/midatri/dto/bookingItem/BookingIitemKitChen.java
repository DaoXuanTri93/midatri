package vn.midatri.dto.bookingItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.repository.model.BookingItemStatus;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BookingIitemKitChen {
    private Long id;
    private Integer quantity;
    private BookingItemStatus status;
    private Instant createAt;
    private long itemId;
    private String tableTopTitle;
}
