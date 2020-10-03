package br.com.sigo.partner.service;

import br.com.sigo.partner.dto.PartnerRequestDTO;
import br.com.sigo.partner.dto.PartnerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartnerService {

    Page<PartnerResponseDTO> getPartners(Pageable pageable);

    PartnerResponseDTO createPartner(PartnerRequestDTO partnerRequestDTO);

    PartnerResponseDTO updatePartner(Integer partnerId, PartnerRequestDTO partnerRequestDTO);
}
