package br.com.likwi.awsJavaLambda.adapters.outbound.persistence;

import br.com.likwi.awsJavaLambda.adapters.outbound.persistence.entity.PetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    Page<PetEntity> findAll(Pageable pageable);
}
