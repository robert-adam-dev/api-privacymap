package co.privacymap.api.repository;

import co.privacymap.api.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Page<Client> findByCpf(String cpf, Pageable pageable);
    Optional <Client> findByCpf(String cpf);
}
