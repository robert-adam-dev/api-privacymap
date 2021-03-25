package co.privacymap.api.model.dto;

import co.privacymap.api.model.Client;
import co.privacymap.api.repository.ClientRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class ClientInputUpdate {

    @NotBlank
    String name;
    @NotBlank
    String city;

    public Client converter(){
        return new Client(name, city);
    }

    public Client update(UUID id, ClientRepository clientRepository) {
        Client client = clientRepository.getOne(id);
        client.setName(this.name);
        client.setCity(this.city);

        return client;
    }
}
