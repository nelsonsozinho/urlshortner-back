package br.com.nms.urlshortener.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {

    @Id
    @SequenceGenerator(name="seq-role",sequenceName="seq-role", initialValue=1, allocationSize=10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq-role")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Role() { }

    public Role(RoleName name) {this.name = name;}

}