package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "`order`")
public class Order {
    public Order(Long id) {
        this.id = id;
    }

    public Order(long userId) {
        this.user = new User(this.userId = userId);
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "grand_total")
    private BigDecimal grandTotal;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "full_name", length = 45)
    private String fullName;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "address", length = 45)
    private String address;


    @Column(name = "create_at", nullable = false)
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @Column(name = "content")
    private String content;


}