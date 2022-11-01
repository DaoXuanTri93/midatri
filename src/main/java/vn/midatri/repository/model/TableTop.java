package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;


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