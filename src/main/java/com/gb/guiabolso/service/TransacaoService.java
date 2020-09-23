package com.gb.guiabolso.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.guiabolso.DTO.TransacaoDTO;
import com.gb.guiabolso.entity.Transacao;
import com.gb.guiabolso.entity.Usuario;
import com.gb.guiabolso.repository.TransacaoRepository;
import com.gb.guiabolso.repository.UsuarioRepository;
import com.gb.guiabolso.utils.CurrencyBuilder;
import com.gb.guiabolso.utils.TimestampBuilder;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Transactional(readOnly = true)
    public List<TransacaoDTO> listarTransacoes(Long usuarioId, Integer transacaoAno, Integer transacaoMes) {
        List<Transacao> transacoes = populate(transacaoAno, transacaoMes);

        List<TransacaoDTO> transacaoDTOList = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            TransacaoDTO transacaoToDTO = objectMapper.convertValue(transacao, TransacaoDTO.class);
            transacaoDTOList.add(transacaoToDTO);
        }

        return transacaoDTOList;
    }

    @Transactional(readOnly = true)
    public List<Transacao> populate(Integer ano, Integer mes) {

        List<Usuario> usuarios = new ArrayList<>();
        List<Transacao> transacoesPorUsuario = new ArrayList<>();

        Random rand = new Random();
        int randUser = rand.nextInt(10);
        int randTrans = rand.nextInt(50);

        for (int i = 1; i < randUser; i++) {
            Usuario usuario = new Usuario();
            usuario.setId((long) i);

            for (int j = 1; j < randTrans; j++) {
                Transacao transacao = new Transacao();

                transacao.setId((long) j);

                Lorem lorem = LoremIpsum.getInstance();
                transacao.setDescricao(lorem.getWords(3, 10));

                String timestamp = TimestampBuilder.randomTimestamp(ano, mes);
                transacao.setData(timestamp);

                transacao.setAno(ano);

                transacao.setMes(mes);

                Integer valor = ThreadLocalRandom.current().nextInt(-9999999, 9999999 + 1);
                transacao.setValor(CurrencyBuilder.formatNumero(valor));

                Random random = new Random();
                transacao.setDuplicated(random.nextBoolean());

                transacoesPorUsuario.add(transacao);
            }

            usuario.setTransacoes(transacoesPorUsuario);
            usuarios.add(usuario);
        }

        return transacoesPorUsuario;
    }
}
