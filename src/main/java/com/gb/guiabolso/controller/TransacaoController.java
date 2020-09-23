package com.gb.guiabolso.controller;

import com.gb.guiabolso.DTO.TransacaoDTO;
import com.gb.guiabolso.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @GetMapping(path = "{id}/transacoes/{ano}/{mes}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransacaoDTO> getTransacoes(@PathVariable(name = "id") Long usuarioId,
                                            @PathVariable(name = "ano") Integer transacaoAno,
                                            @PathVariable(name = "mes") Integer transacaoMes) {
        return transacaoService.listarTransacoes(usuarioId, transacaoAno, transacaoMes);
    }

}
