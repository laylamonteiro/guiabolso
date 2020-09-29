package com.gb.guiabolso.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO {

    //O DTO a ser enviado para a tela
    private String descricao;
    private String data;
    private Float valor;
    private Boolean duplicated;

}
