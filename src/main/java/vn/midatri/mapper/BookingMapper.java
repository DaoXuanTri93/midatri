package vn.midatri.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.repository.model.Booking;

@Component
public class BookingMapper {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TableTopMapper tableTopMapper;


    public BookingResult toDTO(Booking booking) {
        return new BookingResult()
                .setId(booking.getId())
                .setFullName(booking.getFullName())
                .setStatus(booking.getStatus())
                .setPhone(booking.getPhone())
                .setEmail(booking.getEmail())
                .setAddress(booking.getAddress())
                .setContent(booking.getContent())
                .setUser_id(booking.getUser().getId())
                .setTableTop_id(booking.getTableTop().getId());
    }

//    public Booking toModel(BookingResult bookingResult){
//        return new Booking()
//                .setId(bookingResult.getId())
//                .setFullName(bookingResult.getFullName())
//                .setStatus(bookingResult.getStatus())
//                .setPhone(bookingResult.getPhone())
//                .setEmail(bookingResult.getEmail())
//                .setAddress(bookingResult.getAddress())
//                .setContent(bookingResult.getContent())
//                .setUser(bookingResult.getUser_id())
//                .setTableTop_id(bookingResult.getTableTop().getId());
//    }
}
