package br.com.nms.urlshortener.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;


@Entity
@Getter
@Setter
@Table(name = "user", schema = "public")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name="seq-user",sequenceName="seq-user", initialValue=1, allocationSize=10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq-user")
    @Column(name = "id")
    private Long idUser;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "username")
    private String userName;

    @NotBlank
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "creation_date")
    private Date creationDate;

    @NotBlank
    @Column(name = "last_login")
    private Date lastLogin;

    @OneToMany(mappedBy = "user")
    private List<Url> urls;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;


    public User() {}

    public User(String name, String userName, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;

        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.enabled = true;
        this.credentialsNonExpired = true;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
