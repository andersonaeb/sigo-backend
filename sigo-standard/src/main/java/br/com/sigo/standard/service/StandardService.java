package br.com.sigo.standard.service;

import br.com.sigo.standard.dto.StandardRequestDTO;
import br.com.sigo.standard.dto.StandardResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StandardService {

    Page<StandardResponseDTO> getStandards(Pageable pageable);

    StandardResponseDTO createStandard(StandardRequestDTO standardRequestDTO);

    StandardResponseDTO updateStandard(Integer partnerId, StandardRequestDTO standardRequestDTO);

    void deleteStandard(Integer partnerId);

    StandardResponseDTO getStandard(Integer partnerIds);
}
