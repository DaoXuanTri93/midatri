package vn.midatri.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.midatri.dto.bookingItem.BookingItemAdd;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.repository.model.BookingItem;

@Component
public class BookingItemMapper {
    @Autowired
    ItemMapper itemMapper;

    @Autowired
    BookingMapper bookingMapper;

    public BookingItemResult toDTO(BookingItem bookingItem) {
        return new BookingItemResult()
                .setId(bookingItem.getId())
                .setBookingId(bookingItem.getBooking().getId())
                .setItemId(bookingItem.getItem().getId())
                .setPrice(bookingItem.getPrice())
                .setQuantity(bookingItem.getQuantity())
                .setDiscount(bookingItem.getDiscount())
                .setStatus(bookingItem.getStatus())
                .setContent(bookingItem.getContent())
                .setGrandTotal(bookingItem.getGrandTotal());
    }

}
