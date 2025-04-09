package br.com.seuprojeto.controller;

import br.com.seuprojeto.model.Transacao;
import br.com.seuprojeto.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public List<Transacao> listar() {
        return transacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public Transacao buscarPorId(@PathVariable Long id) {
        return transacaoService.buscarPorId(id);
    }

    @PostMapping
    public void salvar(@RequestBody Transacao transacao) {
        transacaoService.salvar(transacao);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        transacaoService.excluir(id);
    }
}