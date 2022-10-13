package vn.midatri.dto.bookingItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.Booking;
import vn.midatri.repository.model.Item;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BookingItemResult {
    private Long id;
    private BookingResult bookingResult;
    private ItemResult itemResult;
    private BigDecimal price;
    private Integer quantity;
    private Float discount;
    private Short status;
    private String content;
    private BigDecimal grandTotal;

}
