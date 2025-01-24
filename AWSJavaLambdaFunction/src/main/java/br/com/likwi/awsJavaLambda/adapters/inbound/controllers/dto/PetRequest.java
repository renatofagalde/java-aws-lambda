package br.com.likwi.awsJavaLambda.adapters.inbound.controllers.dto;

public record PetRequest(PetType type, String breed, String name, String color) {

    public PetRequest {
        if (type == null || breed == null || name == null || color == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }
    }
}