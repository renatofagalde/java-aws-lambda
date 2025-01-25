package br.com.likwi.awsJavaLambda.adapters.config;

import br.com.likwi.awsJavaLambda.Application;
import br.com.likwi.awsJavaLambda.adapters.inbound.controllers.dto.PetResponse;
import br.com.likwi.awsJavaLambda.adapters.inbound.controllers.dto.PetType;
import br.com.likwi.awsJavaLambda.adapters.outbound.persistence.entity.PetEntity;
import br.com.likwi.awsJavaLambda.core.domain.PetDomain;
import br.com.likwi.awsJavaLambda.core.ports.PetPersistencePort;
import br.com.likwi.awsJavaLambda.core.ports.PetServicePort;
import br.com.likwi.awsJavaLambda.core.service.PetService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Application.class)

public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(Boolean.TRUE)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        domainToResponse(modelMapper);
        domainToEntity(modelMapper);
        entityToDomain(modelMapper);

        return modelMapper;
    }

    private static void domainToEntity(ModelMapper modelMapper) {
        Converter<PetDomain, PetEntity> domainToEntityConverter = mappingContext -> {
            PetDomain domain = mappingContext.getSource();
            return new PetEntity(
                    domain.getId(),
                    domain.getType(),
                    domain.getName(),
                    domain.getBreed(),
                    domain.getColor()
            );
        };

        modelMapper.addConverter(domainToEntityConverter, PetDomain.class, PetEntity.class);
    }

    private static void entityToDomain(ModelMapper modelMapper) {
        Converter<PetEntity, PetDomain> entityToDomainConverter = mappingContext -> {
            PetEntity entity = mappingContext.getSource();
            return new PetDomain(
                    entity.getId(),
                    entity.getType(),
                    entity.getName(),
                    entity.getBreed(),
                    entity.getColor()
            );
        };

        modelMapper.addConverter(entityToDomainConverter, PetEntity.class, PetDomain.class);
    }

    private static void domainToResponse(ModelMapper modelMapper) {
        Converter<PetDomain, PetResponse> sdConverter = mappingContext -> {
            PetDomain domain = mappingContext.getSource();
            return new PetResponse(domain.getId(), PetType.valueOf(domain.getType().name()), domain.getBreed(), domain.getName(), domain.getColor());
        };

        modelMapper.addConverter(sdConverter,PetDomain.class,PetResponse.class);
    }

    @Bean
    PetServicePort petServicePort(PetPersistencePort petPersistencePort) {
        return new PetService(petPersistencePort);
    }
}
