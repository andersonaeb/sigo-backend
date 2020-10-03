package br.com.sigo.partner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PartnerRequestDTO {

    @NotBlank
    private String companyName;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String state;

    @NotBlank
    private String city;
}
