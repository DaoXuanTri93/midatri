package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "user")
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