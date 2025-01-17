package br.com.sigo.partner.service;

import br.com.sigo.partner.dto.RequirementRequestDTO;
import br.com.sigo.partner.dto.RequirementResponseDTO;
import br.com.sigo.partner.entity.RequirementEntity;
import br.com.sigo.partner.exception.RequirementNotFoundException;
import br.com.sigo.partner.repository.RequirementRepository;
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
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<RequirementResponseDTO> getRequirements(Pageable pageable) {

        log.info("RequirementServiceImpl.getRequirements - start - pageable: [{}]", pageable);
        long timeStart = System.currentTimeMillis();

        Page<RequirementEntity> requirements = requirementRepository.findAll(pageable);

        List<RequirementResponseDTO> requirementsDTO = requirements.stream().map(requirementEntity -> modelMapper.map(requirementEntity, RequirementResponseDTO.class))
                .collect(Collectors.toList());

        log.info("RequirementServiceImpl.getRequirements - end - took: [{}ms]", System.currentTimeMillis() - timeStart);
        return new PageImpl<>(requirementsDTO, requirements.getPageable(), requirements.getTotalElements());
    }

    @Override
    public RequirementResponseDTO createRequirement(RequirementRequestDTO requirementRequestDTO) {

        log.info("RequirementServiceImpl.createRequirement - start - requirementRequestDTO: [{}]", requirementRequestDTO);
        long timeStart = System.currentTimeMillis();

        RequirementEntity requirementEntity = modelMapper.map(requirementRequestDTO, RequirementEntity.class);

        requirementEntity = requirementRepository.save(requirementEntity);

        log.info("RequirementServiceImpl.createRequirement - end - requirementEntity: [{}] took: [{}ms]", requirementEntity, System.currentTimeMillis() - timeStart);
        return modelMapper.map(requirementEntity, RequirementResponseDTO.class);
    }

    @Override
    public RequirementResponseDTO updateRequirement(Integer requirementId, RequirementRequestDTO requirementRequestDTO) {

        log.info("RequirementServiceImpl.updateRequirement - start - requirementRequestDTO: [{}]", requirementRequestDTO);
        long timeStart = System.currentTimeMillis();

        RequirementEntity requirementEntity = requirementRepository.findById(requirementId)
                .orElseThrow(() -> new RequirementNotFoundException("Requirement with ID " + requirementId + " not found"));

        requirementEntity.setTitle(requirementRequestDTO.getTitle());
        requirementEntity.setDescription(requirementRequestDTO.getDescription());
        requirementEntity.setStatus(requirementRequestDTO.getStatus());
        requirementEntity.setStandardCode(requirementRequestDTO.getStandardCode());
        requirementEntity.setValidity(requirementRequestDTO.getValidity());

        requirementRepository.save(requirementEntity);

        log.info("RequirementServiceImpl.updateRequirement - end - requirementEntity: [{}] took: [{}ms]", requirementEntity, System.currentTimeMillis() - timeStart);

        return modelMapper.map(requirementEntity, RequirementResponseDTO.class);
    }

    @Override
    public void deleteRequirement(Integer requirementId) {

        log.info("RequirementServiceImpl.deleteRequirement - start - requirementId: [{}]", requirementId);
        long timeStart = System.currentTimeMillis();

        RequirementEntity requirementEntity = requirementRepository.findById(requirementId)
                .orElseThrow(() -> new RequirementNotFoundException("Requirement with ID " + requirementId + " not found"));

        requirementRepository.delete(requirementEntity);

        log.info("RequirementServiceImpl.deleteRequirement - end - requirementId: [{}] took: [{}ms]", requirementId, System.currentTimeMillis() - timeStart);
    }

    @Override
    public RequirementResponseDTO getRequirement(Integer requirementId) {

        log.info("RequirementServiceImpl.getRequirement - start - requirementId: [{}]", requirementId);
        long timeStart = System.currentTimeMillis();

        RequirementEntity requirementEntity = requirementRepository.findById(requirementId)
                .orElseThrow(() -> new RequirementNotFoundException("Requirement with ID " + requirementId + " not found"));

        requirementRepository.save(requirementEntity);

        log.info("RequirementServiceImpl.getRequirement - end - requirementEntity: [{}] took: [{}ms]", requirementEntity, System.currentTimeMillis() - timeStart);

        return modelMapper.map(requirementEntity, RequirementResponseDTO.class);
    }
}
