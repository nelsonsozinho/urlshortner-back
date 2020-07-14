package br.com.nms.urlshortener.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name="url", schema = "public")
public class Url {

    @Id
    @SequenceGenerator(name="seq-url",sequenceName="seq-url", initialValue=1, allocationSize=10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq-url")
    @Column(name = "id")
    private Long idUrl;

    @NotBlank
    @Column(name = "original_url")
    private String originalUrl;

    @NotBlank
    @Column(name = "creation_date")
    private Date creationDate;

    @NotBlank
    @Column(name = "expiration_date")
    private Date expirationDate;

    @NotBlank
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    private User user;

}
