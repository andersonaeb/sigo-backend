package br.com.sigo.standard.controller;

import br.com.sigo.standard.dto.StandardRequestDTO;
import br.com.sigo.standard.dto.StandardResponseDTO;
import br.com.sigo.standard.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StandardController {

    @Autowired
    private StandardService standardService;

    @GetMapping(value = "/v1/standards", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<Page<StandardResponseDTO>> getStandards(Pageable pageable) {
        Page<StandardResponseDTO> standards = standardService.getStandards(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(standards);
    }

    @PostMapping(value = "/v1/standards", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<StandardResponseDTO> createStandard(@RequestBody @Valid StandardRequestDTO standardRequestDTO) {
        StandardResponseDTO standard = standardService.createStandard(standardRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(standard);
    }

    @GetMapping(value = "/v1/standards/{standardId}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<StandardResponseDTO> getStandard(@PathVariable Integer standardId) {
        StandardResponseDTO standard = standardService.getStandard(standardId);
        return ResponseEntity.status(HttpStatus.OK).body(standard);
    }

    @PutMapping(value = "/v1/standards/{standardId}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<StandardResponseDTO> updateStandard(@PathVariable Integer standardId, @RequestBody @Valid StandardRequestDTO standardRequestDTO) {
        StandardResponseDTO standard = standardService.updateStandard(standardId, standardRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(standard);
    }

    @DeleteMapping(value = "/v1/standards/{standardId}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<StandardResponseDTO> deleteStandard(@PathVariable Integer standardId) {
        standardService.deleteStandard(standardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
