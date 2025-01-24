package br.com.likwi.awsJavaLambda.adapters.inbound.controllers.dto;

public record PetResponse(Long id, PetType type, String breed, String name, String color) {

}
