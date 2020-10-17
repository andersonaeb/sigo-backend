package br.com.sigo.partner.controller;

import br.com.sigo.partner.dto.RequirementRequestDTO;
import br.com.sigo.partner.dto.RequirementResponseDTO;
import br.com.sigo.partner.service.RequirementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RequirementController {

    @Autowired
    private RequirementService requirementService;

    @GetMapping(value = "/v1/requirements", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<Page<RequirementResponseDTO>> getRequirements(Pageable pageable) {
        Page<RequirementResponseDTO> requirements = requirementService.getRequirements(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(requirements);
    }

    @GetMapping(value = "/v1/requirements/{requirementId}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<RequirementResponseDTO> getRequirement(@PathVariable Integer requirementId) {
        RequirementResponseDTO requirement = requirementService.getRequirement(requirementId);
        return ResponseEntity.status(HttpStatus.OK).body(requirement);
    }

    @PostMapping(value = "/v1/requirements", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<RequirementResponseDTO> createRequirement(@RequestBody @Valid RequirementRequestDTO requirementRequestDTO) {
        RequirementResponseDTO requirement = requirementService.createRequirement(requirementRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(requirement);
    }

    @PutMapping(value = "/v1/requirements/{requirementId}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<RequirementResponseDTO> updateRequirement(@PathVariable Integer requirementId, @RequestBody @Valid RequirementRequestDTO requirementRequestDTO) {
        RequirementResponseDTO requirement = requirementService.updateRequirement(requirementId, requirementRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(requirement);
    }

    @DeleteMapping(value = "/v1/requirements/{requirementId}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<RequirementResponseDTO> updateRequirement(@PathVariable Integer requirementId) {
        requirementService.deleteRequirement(requirementId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
