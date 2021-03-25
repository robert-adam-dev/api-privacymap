package co.privacymap.api.model.dto;

import co.privacymap.api.model.Client;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@Data
public class ClientInput {

    @NotBlank
    String name;
    @CPF
    String cpf;
    @NotBlank
    String city;

    public Client converter(){
        return new Client(name, cpf, city);
    }
}
