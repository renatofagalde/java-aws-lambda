package br.com.likwi.awsJavaLambda.adapters.inbound.controllers;

import br.com.likwi.awsJavaLambda.adapters.inbound.controllers.dto.PetRequest;
import br.com.likwi.awsJavaLambda.adapters.inbound.controllers.dto.PetResponse;
import br.com.likwi.awsJavaLambda.core.domain.PageManual;
import br.com.likwi.awsJavaLambda.core.domain.PetDomain;
import br.com.likwi.awsJavaLambda.core.ports.PetServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetServicePort petServicePort;
    private final ModelMapper modelMapper;

    public PetController(PetServicePort petServicePort, ModelMapper modelMapper) {
        this.petServicePort = petServicePort;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PetResponse>> list(@PageableDefault(page = 0, size = 10) Pageable pageable){
        List<PetResponse> list = this.petServicePort.list(
                        new PageManual(pageable.getPageNumber(), pageable.getPageSize()))
                .stream()
                .map(petDomain -> this.modelMapper.map(petDomain, PetResponse.class))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<PetResponse>(list,pageable,list.size()));
    }

    @PostMapping
    public ResponseEntity<PetResponse> create(@RequestBody PetRequest petRequest) {
        PetDomain petDomain = this.petServicePort.create(this.modelMapper.map(petRequest, PetDomain.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(this.modelMapper.map(petDomain, PetResponse.class));
    }
}
