package br.com.sigo.partner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PartnerResponseDTO {

    private Integer id;

    private String companyName;

    private String cnpj;

    private String state;

    private String city;
}
