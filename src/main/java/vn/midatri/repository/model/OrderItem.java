package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.midatri.dto.report.Goods;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "order_item")

@NamedNativeQuery(
        name = "sp_getallproducttoday",
        query =
                "call midatri.sp_getallproducttoday();",
        resultSetMapping = "result_getallproduct"
)
@NamedNativeQuery(
        name = "sp_getallproductlastday",
        query =
                "call midatri.sp_getallproductlastday();",
        resultSetMapping = "result_getallproduct"
)
@NamedNativeQuery(
        name = "sp_getallproducttomonth",
        query =
                "call midatri.sp_getallproducttomonth();",
        resultSetMapping = "result_getallproduct"
)
@NamedNativeQuery(
        name = "sp_getallproductlastmonth",
        query =
                "call midatri.sp_getallproductlastmonth();",
        resultSetMapping = "result_getallproduct"
)
@NamedNativeQuery(
        name = "sp_getallproductsevenday",
        query =
                "call midatri.sp_getallproductsevenday();",
        resultSetMapping = "result_getallproduct"
)
@NamedNativeQuery(
        name = "sp_getallproduct",
        query =
                "call midatri.sp_getallproduct(:ToDay, :FromDay);",
        resultSetMapping = "result_getallproduct"
)
@SqlResultSetMapping(
        name = "result_getallproduct",
        classes = @ConstructorResult(
                targetClass = Goods.class,
                columns = {

                        @ColumnResult(name = "createAt", type = Instant.class),
                        @ColumnResult(name = "itemId", type = long.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                        @ColumnResult(name = "quantity", type = int.class),
                        @ColumnResult(name = "title", type = String.class)
                }
        )
)
public class OrderItem {
    public OrderItem(long itemId, long orderId) {
        this.item = new Item(this.itemId = itemId);
        this.order = new Order(this.orderId = orderId);
    }
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", nullable = false, insertable = false, updatable = false)
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "order_id", nullable = false, insertable = false, updatable = false)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "price", nullable = false, precision = 12)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "create_at",nullable = false)
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @Column(name = "content")
    private String content;


}