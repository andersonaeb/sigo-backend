package br.com.sigo.standard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String code;

    @NotBlank
    private String category;

    private String keywords;

    private String description;
}
