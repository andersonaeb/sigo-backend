package br.com.sigo.process.controller;

import br.com.sigo.process.dto.ProcessesDTO;
import br.com.sigo.process.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @GetMapping(value = "/v1/processes", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<ProcessesDTO> listAllProcesses() {
        ProcessesDTO responseDTO = processService.listAllProcesses();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
