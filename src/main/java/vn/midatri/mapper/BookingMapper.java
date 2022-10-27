package vn.midatri.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.dto.booking.CreateBookingParam;
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
                .setUserId(booking.getUserId())
                .setTableTopId(booking.getTableTopId());
    }

    public Booking toModel(CreateBookingParam bookingParam){
        return new Booking()
                .setTableTopId(bookingParam.getTabletopId());
    }

}
