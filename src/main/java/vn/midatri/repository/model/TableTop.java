package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@NamedNativeQuery(
        name = "sp_chartlastday",
        query =
                "call midatri.sp_chartlastday();",
        resultSetMapping = "result_chartLastDayDto"
)
@SqlResultSetMapping(
        name = "result_chartLastDayDto",
        classes = @ConstructorResult(
                targetClass = Chart.class,
                columns = {
                        @ColumnResult(name = "total", type = BigDecimal.class),
                        @ColumnResult(name = "dates", type = Instant.class)

                }
        )
)
@NamedNativeQuery(
        name = "sp_last7day",
        query =
                "call midatri.sp_last7day();",
        resultSetMapping = "result_chartLast7DayDto"
)
@SqlResultSetMapping(
        name = "result_chartLast7DayDto",
        classes = @ConstructorResult(
                targetClass = Chart.class,
                columns = {
                        @ColumnResult(name = "total", type = BigDecimal.class),
                        @ColumnResult(name = "dates", type = Instant.class)

                }
        )
)
@NamedNativeQuery(
        name = "sp_chartbymonth",
        query =
                "call midatri.sp_chartbymonth();",
        resultSetMapping = "result_chartByMonthDto"
)
@SqlResultSetMapping(
        name = "result_chartByMonthDto",
        classes = @ConstructorResult(
                targetClass = Chart.class,
                columns = {
                        @ColumnResult(name = "total", type = BigDecimal.class),
                        @ColumnResult(name = "dates", type = Instant.class)

                }
        )
)
//chart by last month
@NamedNativeQuery(
        name = "sp_last1monht",
        query =
                "call midatri.sp_last1monht();",
        resultSetMapping = "result_chartByLastMonthDto"
)
@SqlResultSetMapping(
        name = "result_chartByLastMonthDto",
        classes = @ConstructorResult(
                targetClass = Chart.class,
                columns = {
                        @ColumnResult(name = "total", type = BigDecimal.class),
                        @ColumnResult(name = "dates", type = Instant.class)

                }
        )
)

//show bill
@NamedNativeQuery(
        name = "sp_test",
        query =
                "call midatri.sp_test();",
        resultSetMapping = "result_showBillDto"
)
@SqlResultSetMapping(
        name = "result_showBillDto",
        classes = @ConstructorResult(
                targetClass = Chart.class,
                columns = {
                        @ColumnResult(name = "username", type = String.class),
                        @ColumnResult(name = "dates", type = Instant.class),
                        @ColumnResult(name = "total", type = BigDecimal.class)

                }
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "table_top")
public class TableTop {
    public TableTop(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Tên bàn bắt buộc phải có")
    @Column(name = "title", length = 45, nullable = false)
    private String title;


    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TabletopStatus status;

    @NotNull(message = "Bạn phải nhập số ghế cho bàn")
    @Column(name = "capacity", nullable = false)
//    @Size(min = 2,max = 16)
    private Short capacity;

    @Column(name = "content", length = 45)
    private String content;

}
//chart last day
