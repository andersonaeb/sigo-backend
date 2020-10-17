package br.com.sigo.partner.controller;

import br.com.sigo.partner.dto.PartnerRequestDTO;
import br.com.sigo.partner.dto.PartnerResponseDTO;
import br.com.sigo.partner.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping(value = "/v1/partners", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<Page<PartnerResponseDTO>> getPartners(Pageable pageable) {
        Page<PartnerResponseDTO> partners = partnerService.getPartners(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(partners);
    }

    @PostMapping(value = "/v1/partners", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<PartnerResponseDTO> createPartner(@RequestBody @Valid PartnerRequestDTO partnerRequestDTO) {
        PartnerResponseDTO partner = partnerService.createPartner(partnerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(partner);
    }

    @GetMapping(value = "/v1/partners/{partnerId}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<PartnerResponseDTO> getPartner(@PathVariable Integer partnerId) {
        PartnerResponseDTO partner = partnerService.getPartner(partnerId);
        return ResponseEntity.status(HttpStatus.OK).body(partner);
    }

    @PutMapping(value = "/v1/partners/{partnerId}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<PartnerResponseDTO> updatePartner(@PathVariable Integer partnerId, @RequestBody @Valid PartnerRequestDTO partnerRequestDTO) {
        PartnerResponseDTO partner = partnerService.updatePartner(partnerId, partnerRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(partner);
    }

    @DeleteMapping(value = "/v1/partners/{partnerId}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<PartnerResponseDTO> deletePartner(@PathVariable Integer partnerId) {
        partnerService.deletePartner(partnerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
