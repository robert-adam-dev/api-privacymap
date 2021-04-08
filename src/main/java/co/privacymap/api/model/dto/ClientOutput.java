package co.privacymap.api.model.dto;

import co.privacymap.api.model.Client;
import co.privacymap.api.model.Profile;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ClientOutput {

    private UUID id;
    private String name;
    private String city;
    private String cpf;
    private String email;
    private List<Profile> proviles;

    public ClientOutput(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.city = client.getCity();
        this.cpf = client.getCpf();
        this.email = client.getEmail();
        this.proviles = client.getProviles();
    }

    public static Page<ClientOutput> converter(Page<Client> clients){
        return clients.map(ClientOutput::new);
    }

}
