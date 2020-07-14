package br.com.nms.urlshortener.repository;


import br.com.nms.urlshortener.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UrlRepository extends JpaRepository<Url, Long> {

}
