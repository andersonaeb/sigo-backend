package br.com.sigo.standard.service;

import br.com.sigo.standard.dto.StandardRequestDTO;
import br.com.sigo.standard.dto.StandardResponseDTO;
import br.com.sigo.standard.entity.StandardEntity;
import br.com.sigo.standard.exception.StandardException;
import br.com.sigo.standard.repository.StandardRepository;
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
public class StandardServiceImpl implements StandardService {

    @Autowired
    private StandardRepository standardRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<StandardResponseDTO> getStandards(Pageable pageable) {

        log.info("StandardServiceImpl.getStandards - start - pageable: [{}]", pageable);
        long timeStart = System.currentTimeMillis();

        Page<StandardEntity> standards = standardRepository.findAll(pageable);

        List<StandardResponseDTO> standardsDTO = standards.stream().map(standardEntity -> modelMapper.map(standardEntity, StandardResponseDTO.class)).collect(Collectors.toList());

        log.info("StandardServiceImpl.getStandards - end - took: [{}ms]", System.currentTimeMillis() - timeStart);

        return new PageImpl<>(standardsDTO, standards.getPageable(), standards.getTotalElements());
    }

    public StandardResponseDTO createStandard(StandardRequestDTO standardRequestDTO) {

        log.info("StandardServiceImpl.createStandard - start - standardRequestDTO: [{}]", standardRequestDTO);
        long timeStart = System.currentTimeMillis();

        StandardEntity standardEntity = modelMapper.map(standardRequestDTO, StandardEntity.class);

        standardEntity = standardRepository.save(standardEntity);

        log.info("StandardServiceImpl.createStandard - end - standardEntity: [{}] took: [{}ms]", standardEntity, System.currentTimeMillis() - timeStart);

        return modelMapper.map(standardEntity, StandardResponseDTO.class);
    }

    public StandardResponseDTO updateStandard(Integer standardId, StandardRequestDTO standardRequestDTO) {

        log.info("StandardServiceImpl.updateStandard - start - standardRequestDTO: [{}]", standardRequestDTO);
        long timeStart = System.currentTimeMillis();

        StandardEntity standardEntity = standardRepository.findById(standardId)
                .orElseThrow(() -> new StandardException("Standard with ID " + standardId + " not found"));

        standardEntity.setTitle(standardRequestDTO.getTitle());
        standardEntity.setCode(standardRequestDTO.getCode());
        standardEntity.setCategory(standardRequestDTO.getCategory());
        standardEntity.setKeywords(standardRequestDTO.getKeywords());
        standardEntity.setDescription(standardRequestDTO.getDescription());

        standardEntity = standardRepository.save(standardEntity);

        log.info("StandardServiceImpl.updateStandard - end - standardEntity: [{}] took: [{}ms]", standardEntity, System.currentTimeMillis() - timeStart);

        return modelMapper.map(standardEntity, StandardResponseDTO.class);
    }

    public StandardResponseDTO getStandard(Integer standardId) {

        log.info("StandardServiceImpl.getStandard - start - standardId: [{}]", standardId);
        long timeStart = System.currentTimeMillis();

        StandardEntity standardEntity = standardRepository.findById(standardId)
                .orElseThrow(() -> new StandardException("Standard with ID " + standardId + " not found"));

        log.info("StandardServiceImpl.getStandard - end - standardEntity: [{}] took: [{}ms]", standardEntity, System.currentTimeMillis() - timeStart);

        return modelMapper.map(standardEntity, StandardResponseDTO.class);
    }

    public void deleteStandard(Integer standardId) {

        log.info("StandardServiceImpl.deleteStandard - start - standardId: [{}]", standardId);
        long timeStart = System.currentTimeMillis();

        StandardEntity standardEntity = standardRepository.findById(standardId)
                .orElseThrow(() -> new StandardException("Standard with ID " + standardId + " not found"));

        standardRepository.delete(standardEntity);

        log.info("StandardServiceImpl.deleteStandard - end - standardId: [{}] took: [{}ms]", standardId, System.currentTimeMillis() - timeStart);
    }
}
