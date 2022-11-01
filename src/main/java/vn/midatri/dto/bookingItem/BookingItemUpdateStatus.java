package vn.midatri.dto.bookingItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.repository.model.BookingItemStatus;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class BookingItemUpdateStatus {
    private Long id;
    private BookingItemStatus status;
}
