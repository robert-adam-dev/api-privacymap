package co.privacymap.api.model.dto;

import co.privacymap.api.model.Client;
import lombok.Getter;

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

    public static List<ClientOutput> converter(List<Client> clients){
        return clients.stream().map(ClientOutput::new).collect(Collectors.toList());
    }

}
