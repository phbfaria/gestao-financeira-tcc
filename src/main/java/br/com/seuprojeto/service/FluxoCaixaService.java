package br.com.seuprojeto.service;

import br.com.seuprojeto.enums.TipoTransacao;
import br.com.seuprojeto.dto.FluxoCaixaDTO;
import br.com.seuprojeto.model.Transacao;
import br.com.seuprojeto.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FluxoCaixaService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public FluxoCaixaDTO calcularFluxoCaixaMensal(LocalDate dataInicio, LocalDate dataFim) {
        List<Transacao> transacoes = transacaoRepository.findByDataBetween(dataInicio, dataFim);

        BigDecimal totalReceitas = BigDecimal.ZERO;
        BigDecimal totalDespesas = BigDecimal.ZERO;

        for (Transacao t : transacoes) {
            if (t.getTipo() == TipoTransacao.RECEITA) {
                totalReceitas = totalReceitas.add(t.getValor());
            } else if (t.getTipo() == TipoTransacao.DESPESA) {
                totalDespesas = totalDespesas.add(t.getValor());
            }
        }

        BigDecimal saldo = totalReceitas.subtract(totalDespesas);

        FluxoCaixaDTO dto = new FluxoCaixaDTO();
        dto.setReceitas(totalReceitas);
        dto.setDespesas(totalDespesas);
        dto.setSaldo(saldo);
        dto.setTransacoes(transacoes);

        return dto;
    }
}
