package co.privacymap.api.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Client implements Serializable {

    @Id @GeneratedValue @Column(columnDefinition = "BINARY(16)")
    UUID id;
    @NotBlank
    String name;
    @NotBlank @CPF
    String cpf;
    @NotBlank
    String city;
    LocalDateTime creationDate = LocalDateTime.now();

    public Client() {
    }

    public Client(String name, String cpf, String city) {
        this.name = name;
        this.cpf = cpf;
        this.city = city;
    }
    public Client(String name, String city){
        this.name = name;
        this.city = city;
    }
}
