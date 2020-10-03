package br.com.sigo.partner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequirementResponseDTO {

    private Integer id;

    private String title;

    private String description;

    private String status;

    private String standardCode;

    private Date validity;
}
