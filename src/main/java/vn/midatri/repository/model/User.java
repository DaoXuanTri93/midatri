package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fullname", length = 45)
    private String fullname;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "vendor", nullable = false)
    private Boolean vendor = false;

    @Column(name = "admin", nullable = false)
    private Boolean admin = false;

    @Column(name = "bartender", nullable = false)
    private Boolean bartender = false;

    @Column(name = "deleted")
    private Boolean deleted;



}