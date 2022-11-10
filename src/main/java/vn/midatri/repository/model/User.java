package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "user")


//chart total
@NamedNativeQuery(
        name = "sp_chart",
        query =
                "call midatri.sp_barchart();",
        resultSetMapping = "result_chartdto"
)
@SqlResultSetMapping(
        name = "result_chartdto",
        classes = @ConstructorResult(
                targetClass = Chart.class,
                columns = {
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "total", type = BigDecimal.class)
                }
        )
)

//chart day
@NamedNativeQuery(
        name = "sp_chartbyday",
        query =
                "call midatri.sp_chartbyday();",
        resultSetMapping = "result_chartDayDto"
)
@SqlResultSetMapping(
        name = "result_chartDayDto",
        classes = @ConstructorResult(
                targetClass = Chart.class,
                columns = {
                        @ColumnResult(name = "total", type = BigDecimal.class),
                        @ColumnResult(name = "dates", type = Instant.class)

                }
        )
)




public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 45, nullable = false)
    private String userName;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "full_name", length = 45, nullable = false)
    private String fullName;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "email", length = 45)
    private String email;

    private Boolean vendor;

    private Boolean admin;

    private Boolean bartender;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;

    public User(long id) {
        this.id = id;
    }
}

