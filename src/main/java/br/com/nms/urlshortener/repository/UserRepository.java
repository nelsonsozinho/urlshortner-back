package br.com.nms.urlshortener.repository;

import br.com.nms.urlshortener.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

}
