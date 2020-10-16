package br.com.sigo.standard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponseDTO {

    private Integer id;

    private String title;

    private String code;

    private String category;

    private String keywords;

    private String description;
}
