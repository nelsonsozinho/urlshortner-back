package br.com.nms.urlshortener.repository;

import br.com.nms.urlshortener.domain.Role;
import br.com.nms.urlshortener.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false, collectionResourceRel = "role", path = "role")
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);

}