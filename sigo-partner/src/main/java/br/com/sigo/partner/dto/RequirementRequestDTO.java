package br.com.sigo.partner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequirementRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String status;

    private String standardCode;

    private Date validity;
}
