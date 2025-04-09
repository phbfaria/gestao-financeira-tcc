package br.com.seuprojeto.dto;

import br.com.seuprojeto.model.Transacao;

import java.math.BigDecimal;
import java.util.List;

public class FluxoCaixaDTO {

    private BigDecimal receitas;
    private BigDecimal despesas;
    private BigDecimal saldo;
    private List<Transacao> transacoes;

    public BigDecimal getReceitas() {
        return receitas;
    }

    public void setReceitas(BigDecimal receitas) {
        this.receitas = receitas;
    }

    public BigDecimal getDespesas() {
        return despesas;
    }

    public void setDespesas(BigDecimal despesas) {
        this.despesas = despesas;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}
