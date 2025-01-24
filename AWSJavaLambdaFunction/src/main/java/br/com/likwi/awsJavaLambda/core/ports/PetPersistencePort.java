package br.com.likwi.awsJavaLambda.core.ports;

import br.com.likwi.awsJavaLambda.core.domain.PageManual;
import br.com.likwi.awsJavaLambda.core.domain.PetDomain;

import java.util.List;

public interface PetPersistencePort {
    List<PetDomain> list(PageManual pageable);
    PetDomain create(PetDomain pet);
}
