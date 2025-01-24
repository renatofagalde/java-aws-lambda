package br.com.likwi.awsJavaLambda.adapters.outbound.persistence.entity;

import br.com.likwi.awsJavaLambda.core.domain.PetTypeDomain;
import jakarta.persistence.*;

@Table(name = "pet")
@Entity
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetTypeDomain type;

    private String name;

    private String breed;

    private String color;

    public PetEntity(Long id, PetTypeDomain type, String name, String breed, String color) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.breed = breed;
        this.color = color;
    }

    public PetEntity() {

    }

    public Long getId() {
        return id;
    }

    public PetTypeDomain getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }
}
