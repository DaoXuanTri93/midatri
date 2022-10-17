package vn.midatri.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.dto.bookingItem.BookingItemAdd;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.BookingItem;

@Component
public class BookingItemMapper {
    @Autowired
    ItemMapper itemMapper;

    @Autowired
    BookingMapper bookingMapper;

    public BookingItemResult toDTO(BookingItem bookingItem) {
        BookingResult booking = bookingMapper.toDTO(bookingItem.getBooking());
        ItemResult item = itemMapper.toDTO(bookingItem.getItem());
        return new BookingItemResult()
                .setId(bookingItem.getId())
                .setBooking(booking)
                .setItem(item)
                .setPrice(bookingItem.getPrice())
                .setQuantity(bookingItem.getQuantity())
                .setDiscount(bookingItem.getDiscount())
                .setStatus(bookingItem.getStatus())
                .setContent(bookingItem.getContent())
                .setGrandTotal(bookingItem.getGrandTotal());
    }

//    public BookingItem toModel(BookingItemAdd bookingItemAdd){
//        return new BookingItem()
//                .setId(bookingItemAdd.getId())
//                .setPrice(bookingItemAdd.getPrice())
//                .setQuantity(bookingItemAdd.getQuantity())
//                .setContent(bookingItemAdd.getContent())
//                .setBooking(bookingItemAdd.getBooking_id())
//                .setItem(bookingItemAdd.getItem_id());
//    }

}
