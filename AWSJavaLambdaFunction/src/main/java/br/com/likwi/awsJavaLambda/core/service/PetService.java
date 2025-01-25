package br.com.likwi.awsJavaLambda.core.service;

import br.com.likwi.awsJavaLambda.core.domain.PageManual;
import br.com.likwi.awsJavaLambda.core.domain.PetDomain;
import br.com.likwi.awsJavaLambda.core.ports.PetPersistencePort;
import br.com.likwi.awsJavaLambda.core.ports.PetServicePort;

import java.util.List;

public class PetService implements PetServicePort {

    private final PetPersistencePort petPersistencePort;

    public PetService(PetPersistencePort petPersistencePort) {
        this.petPersistencePort = petPersistencePort;
    }

    @Override
    public List<PetDomain> list(PageManual pageble) {
        return this.petPersistencePort.list(pageble);
    }

    @Override
    public PetDomain create(PetDomain pet) {
        return this.petPersistencePort.create(pet);
    }
}
