package br.com.sigo.partner.service;

import br.com.sigo.partner.dto.PartnerRequestDTO;
import br.com.sigo.partner.dto.PartnerResponseDTO;
import br.com.sigo.partner.entity.PartnerEntity;
import br.com.sigo.partner.exception.PartnerException;
import br.com.sigo.partner.repository.PartnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PartnerResponseDTO> getPartners(Pageable pageable) {

        log.info("PartnerServiceImpl.getPartners - start - pageable: [{}]", pageable);
        long timeStart = System.currentTimeMillis();

        Page<PartnerEntity> partners = partnerRepository.findAll(pageable);

        List<PartnerResponseDTO> partnersDTO = partners.stream().map(partnerEntity -> modelMapper.map(partnerEntity, PartnerResponseDTO.class)).collect(Collectors.toList());

        log.info("PartnerServiceImpl.getPartners - end - took: [{}ms]", System.currentTimeMillis() - timeStart);

        return new PageImpl<>(partnersDTO, partners.getPageable(), partners.getTotalElements());
    }

    public PartnerResponseDTO createPartner(PartnerRequestDTO partnerRequestDTO) {

        log.info("PartnerServiceImpl.createPartner - start - partnerRequestDTO: [{}]", partnerRequestDTO);
        long timeStart = System.currentTimeMillis();

        PartnerEntity partnerEntity = modelMapper.map(partnerRequestDTO, PartnerEntity.class);

        partnerEntity = partnerRepository.save(partnerEntity);

        log.info("PartnerServiceImpl.createPartner - end - partnerEntity: [{}] took: [{}ms]", partnerEntity, System.currentTimeMillis() - timeStart);

        return modelMapper.map(partnerEntity, PartnerResponseDTO.class);
    }

    public PartnerResponseDTO updatePartner(Integer partnerId, PartnerRequestDTO partnerRequestDTO) {

        log.info("PartnerServiceImpl.updatePartner - start - partnerRequestDTO: [{}]", partnerRequestDTO);
        long timeStart = System.currentTimeMillis();

        PartnerEntity partnerEntity = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new PartnerException("Partner with ID " + partnerId + " not found"));

        partnerEntity.setCompanyName(partnerRequestDTO.getCompanyName());
        partnerEntity.setCnpj(partnerRequestDTO.getCnpj());
        partnerEntity.setCity(partnerRequestDTO.getCity());
        partnerEntity.setState(partnerRequestDTO.getState());

        partnerEntity = partnerRepository.save(partnerEntity);

        log.info("PartnerServiceImpl.updatePartner - end - partnerEntity: [{}] took: [{}ms]", partnerEntity, System.currentTimeMillis() - timeStart);

        return modelMapper.map(partnerEntity, PartnerResponseDTO.class);
    }

    public PartnerResponseDTO getPartner(Integer partnerId) {

        log.info("PartnerServiceImpl.getPartner - getPartner - partnerId: [{}]", partnerId);
        long timeStart = System.currentTimeMillis();

        PartnerEntity partnerEntity = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new PartnerException("Partner with ID " + partnerId + " not found"));

        log.info("PartnerServiceImpl.getPartner - end - partnerEntity: [{}] took: [{}ms]", partnerEntity, System.currentTimeMillis() - timeStart);

        return modelMapper.map(partnerEntity, PartnerResponseDTO.class);
    }

    public void deletePartner(Integer partnerId) {

        log.info("PartnerServiceImpl.deletePartner - start - partnerId: [{}]", partnerId);
        long timeStart = System.currentTimeMillis();

        PartnerEntity partnerEntity = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new PartnerException("Partner with ID " + partnerId + " not found"));

        partnerRepository.delete(partnerEntity);

        log.info("PartnerServiceImpl.deletePartner - end - partnerId: [{}] took: [{}ms]", partnerId, System.currentTimeMillis() - timeStart);
    }
}
