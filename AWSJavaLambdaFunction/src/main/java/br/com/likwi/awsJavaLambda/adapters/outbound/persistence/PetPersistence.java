package br.com.likwi.awsJavaLambda.adapters.outbound.persistence;

import br.com.likwi.awsJavaLambda.adapters.outbound.persistence.entity.PetEntity;
import br.com.likwi.awsJavaLambda.core.domain.PageManual;
import br.com.likwi.awsJavaLambda.core.domain.PetDomain;
import br.com.likwi.awsJavaLambda.core.ports.PetPersistencePort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetPersistence implements PetPersistencePort {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;


    public PetPersistence(PetRepository petRepository, ModelMapper modelMapper) {
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PetDomain> list(PageManual pageManual) {

        Pageable pageable = PageRequest.of(pageManual.getPageNumber(), pageManual.getPageSize());
        return this.petRepository.findAll(pageable)
                .stream()
                .map(entity -> this.modelMapper.map(entity, PetDomain.class))
                .collect(Collectors.toList());
    }

    @Override
    public PetDomain create(PetDomain pet) {
        PetEntity petEntity = this.modelMapper.map(pet, PetEntity.class);
        PetEntity save = this.petRepository.save(petEntity);
        return this.modelMapper.map(save, PetDomain.class);
    }
}
