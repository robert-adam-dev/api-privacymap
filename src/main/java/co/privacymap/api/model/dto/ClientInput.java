package co.privacymap.api.model.dto;

import co.privacymap.api.model.Client;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ClientInput {

    @NotBlank
    String name;

    @CPF
    String cpf;

    @NotBlank
    String city;

    @Email
    String email;

    @NotNull @Length(min = 8)
    String password;

    public Client converter(){
        return new Client(name, cpf, city, email, password);
    }
}
