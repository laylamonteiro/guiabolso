package com.gb.guiabolso.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.guiabolso.DTO.TransacaoDTO;
import com.gb.guiabolso.entity.Transacao;
import com.gb.guiabolso.entity.Usuario;
import com.gb.guiabolso.repository.TransacaoRepository;
import com.gb.guiabolso.utils.CurrencyBuilder;
import com.gb.guiabolso.utils.TimestampBuilder;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    private ObjectMapper objectMapper;

    public List<TransacaoDTO> listarTransacoes(Long usuarioId, Integer transacaoAno, Integer transacaoMes) {

        //Checa se o parametros estão dentro do que é esperado
        if (usuarioId < 1000 || usuarioId > 100000000) {
            throw new EntityNotFoundException("O Id de usuário informado não é válido");
        }
        if (transacaoAno > 2020) {
            throw new IllegalArgumentException("O ano informado para consulta não é válido");
        }
        if (transacaoMes < 1 || transacaoMes > 12) {
            throw new IllegalArgumentException("O mês informado para consulta não é válido");
        }

        //Invoca a função que faz a criação das transações mockadas e salva numa lista
        List<Transacao> transacoes = populate(transacaoAno, transacaoMes);

        //Cria uma lista para iterar sobre as transações e convertê-las para DTOs
        List<TransacaoDTO> transacaoDTOList = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            TransacaoDTO transacaoToDTO = objectMapper.convertValue(transacao, TransacaoDTO.class);
            transacaoDTOList.add(transacaoToDTO);
        }

        //Retorna uma lista de DTOs conforme o contrato para a tela
        return transacaoDTOList;
    }

    @Transactional(readOnly = true)
    public List<Transacao> populate(Integer ano, Integer mes) {

        /**A ideia inicial era utilizar o repositório, mas não funcionou como eu esperava, então resolvi partir para
         * outra abordagem, e retornar dados aleatórios, que seriam previamente criados em uma lista.
         */

        List<Usuario> usuarios = new ArrayList<>();
        List<Transacao> transacoesPorUsuario = new ArrayList<>();

        //Gera um número aleatório de transações
        Random rand = new Random();
        int randUser = rand.nextInt(10);
        int randTrans = rand.nextInt(50);

        for (int i = 1; i < randUser; i++) {
            //Cria um número aleatório de usuários
            Usuario usuario = new Usuario();
            usuario.setId((long) i);

            for (int j = 1; j < randTrans; j++) {
                //Cria um número aleatório de transações
                Transacao transacao = new Transacao();

                transacao.setId((long) j);

                //Gera um texto lorem ipsum aleatório que contém entre 3 e 5 palavras
                Lorem lorem = LoremIpsum.getInstance();
                transacao.setDescricao(lorem.getWords(3, 10));

                //Gera um timestamp aletório, com base nos parâmetros recebidos
                String timestamp = TimestampBuilder.randomTimestamp(ano, mes);
                transacao.setData(timestamp);

                //Gera um valor aleatório entre -999.999.999 e 999.999.999 no formato da moeda brasileira
                Integer valor = ThreadLocalRandom.current().nextInt(-9999999, 9999999 + 1);
                transacao.setValor(CurrencyBuilder.formatNumero(valor));

                //Gera um valor aleatório para determinar se é duplicado ou não
                Random random = new Random();
                transacao.setDuplicated(random.nextBoolean());

                //Adiciona a transação a uma lista de transações
                transacoesPorUsuario.add(transacao);
                transacaoRepository.save(transacao);
            }

            //Adiciona s transações a cada usuário, e adiciona o usuário em uma lista
            usuario.setTransacoes(transacoesPorUsuario);
            usuarios.add(usuario);
        }

        //Retorna a lista de transações
        return transacoesPorUsuario;
    }
}
