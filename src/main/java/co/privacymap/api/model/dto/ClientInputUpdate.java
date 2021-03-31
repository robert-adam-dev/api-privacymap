package co.privacymap.api.model.dto;

import co.privacymap.api.model.Client;
import co.privacymap.api.repository.ClientRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ClientInputUpdate {

    @NotBlank
    String name;

    @NotBlank
    String city;

    @NotNull @Length(min = 8)
    String password;

    public Client converter(){
        return new Client(name, city, password);
    }

    public Client update(UUID id, ClientRepository clientRepository) {
        Client client = clientRepository.getOne(id);
        client.setName(this.name);
        client.setCity(this.city);

        return client;
    }
}
