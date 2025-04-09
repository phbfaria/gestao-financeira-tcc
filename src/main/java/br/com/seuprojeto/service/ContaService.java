package br.com.seuprojeto.service;

import br.com.seuprojeto.model.Conta;
import br.com.seuprojeto.repository.ContaRepository;
import br.com.seuprojeto.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<Conta> listarTodas() {
        return contaRepository.findAll();
    }

    public Conta buscarPorId(Long id) {
        Optional<Conta> conta = contaRepository.findById(id);
        return conta.orElse(null);
    }

    public void salvar(Conta conta) {
        contaRepository.save(conta);
    }

    public void excluir(Long id) {
        if (transacaoRepository.existsByContaId(id)) {
            throw new IllegalStateException("Não é possível excluir a conta. Existem transações associadas.");
        }
        contaRepository.deleteById(id);
    }
}
