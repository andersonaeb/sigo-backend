package br.com.sigo.process.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDTO {

    private String taskName;

    private String assignedTo;

    private String status;
}
