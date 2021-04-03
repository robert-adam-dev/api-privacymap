package co.privacymap.api.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Client implements Serializable, UserDetails {

    @Id @GeneratedValue @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull @Length(min = 8)
    private String password;

    @NotBlank @CPF
    private String cpf;

    @NotBlank
    private String city;

    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Profile> proviles = new ArrayList<>();
    public Client() {
    }

    //name, cpf, city, email, password
    public Client(String name, String cpf, String city, String email, String password) {
        this.name = name;
        this.cpf = cpf;
        this.city = city;
        this.email = email;
        this.password = password;
    }
    //name, cpf, city, password, email
    public Client(String name, String city, String password ){
        this.name = name;
        this.city = city;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
