package org.ikropachev.bdater.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(example = "example@gmail.com")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 4, max = 100)
    @ApiModelProperty(example = "password")
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    @ApiModelProperty(example = "true")
    private boolean enabled = true;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private Date registered = new Date();

    @Column(name = "user_role_id", nullable = false)
    private int userRoleId;

    public User() {
    }

    public User(User u) {
        this(u.id, u.name, u.email, u.password, u.enabled, u.registered, u.userRoleId);
    }

    public User(Integer id, String name, String email, String password, int userRoleId) {
        this(id, name, email, password, true, new Date(), userRoleId);
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Date registered, int userRoleId) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.userRoleId = userRoleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public String getPassword() {
        return password;
    }

    public void setuserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", userRoleId=" + userRoleId +
                '}';
    }
}
