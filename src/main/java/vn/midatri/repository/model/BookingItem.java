package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import vn.midatri.mapper.BookingMapper;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "booking_item")
public class BookingItem {
    public BookingItem(long bookingId, long itemId) {
        this.booking = new Booking(this.bookingId = bookingId);
        this.item = new Item(this.itemId = itemId);
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id", nullable = false, insertable = false, updatable = false)
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "item_id", nullable = false, insertable = false, updatable = false)
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "price", nullable = false, precision = 12)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingItemStatus status;

    @Column(name = "create_at")
    @CreationTimestamp
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @Column(name = "content", length = 45)
    private String content;


}