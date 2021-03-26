package co.privacymap.api.model.dto;

import co.privacymap.api.model.Client;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class ClientOutput {

    private UUID id;
    private String name;
    private String city;

    public ClientOutput(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.city = client.getCity();
    }

    public static Page<ClientOutput> converter(Page<Client> clients){
        return clients.map(ClientOutput::new);
    }

}
