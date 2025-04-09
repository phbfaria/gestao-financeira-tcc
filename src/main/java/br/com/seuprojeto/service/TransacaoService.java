package br.com.seuprojeto.service;

import br.com.seuprojeto.enums.TipoTransacao;
import br.com.seuprojeto.model.Transacao;
import br.com.seuprojeto.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<Transacao> listarTodas() {
        return transacaoRepository.findAll();
    }

    public Transacao buscarPorId(Long id) {
        return transacaoRepository.findById(id).orElse(null);
    }

    public void salvar(Transacao transacao) {
        transacaoRepository.save(transacao);
    }

    public void excluir(Long id) {
        transacaoRepository.deleteById(id);
    }

    public Map<String, Double[]> obterDadosGraficoPorPeriodo(LocalDate inicio, LocalDate fim) {
        List<Transacao> transacoes = transacaoRepository.findByDataBetween(inicio, fim);
        Map<String, Double[]> dados = new TreeMap<>();

        for (Transacao t : transacoes) {
            String mesAno = String.format("%02d/%d", t.getData().getMonthValue(), t.getData().getYear());
            Double[] valores = dados.getOrDefault(mesAno, new Double[]{0.0, 0.0});

            if (t.getTipo() == TipoTransacao.RECEITA) {
                valores[0] += t.getValor().doubleValue();
            } else if (t.getTipo() == TipoTransacao.DESPESA) {
                valores[1] += t.getValor().doubleValue();
            }

            dados.put(mesAno, valores);
        }

        return dados;
    }
}
