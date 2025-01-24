package br.com.likwi.awsJavaLambda.core.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;


public class PetDomain {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetTypeDomain type;

    private String name;

    private String breed;

    private String color;

    public PetDomain() {
    }

    public PetDomain(Long id, PetTypeDomain type, String name, String breed, String color) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.breed = breed;
        this.color = color;
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
