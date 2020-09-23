package com.gb.guiabolso.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO {

    private String descricao;
    private String data;
    private String valor;
    private Boolean duplicated;

}
