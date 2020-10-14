package br.com.sigo.partner.service;

import br.com.sigo.partner.dto.RequirementRequestDTO;
import br.com.sigo.partner.dto.RequirementResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequirementService {

    Page<RequirementResponseDTO> getRequirements(Pageable pageable);

    RequirementResponseDTO createRequirement(RequirementRequestDTO requirementRequestDTO);

    RequirementResponseDTO updateRequirement(Integer requirementId, RequirementRequestDTO requirementRequestDTO);

    void deleteRequirement(Integer requirementId);

    RequirementResponseDTO getRequirement(Integer requirementId);
}
