package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "table_top")
public class TableTop {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "capacity", nullable = false)
    private Short capacity;

    @CreationTimestamp
    @Column(name = "create_at")
    private Instant createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private Instant updateAt;

    @Column(name = "content", length = 45)
    private String content;
}