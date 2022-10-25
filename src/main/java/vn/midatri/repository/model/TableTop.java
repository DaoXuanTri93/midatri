package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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

    @Column(name = "title", length = 45, nullable = false)
    private String title;


    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TabletopStatus status;

    @Column(name = "capacity", nullable = false)
    private Short capacity;

    @Column(name = "create_at", nullable = false)
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @Column(name = "content", length = 45)
    private String content;


}