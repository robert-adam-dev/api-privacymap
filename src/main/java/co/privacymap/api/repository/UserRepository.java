package co.privacymap.api.repository;

import co.privacymap.api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Client, UUID> {

    Optional <Client> findByEmail(String email);
}
