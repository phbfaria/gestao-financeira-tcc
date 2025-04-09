package br.com.seuprojeto.controller;

import br.com.seuprojeto.model.Transacao;
import br.com.seuprojeto.service.ContaService;
import br.com.seuprojeto.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/transacoes")
public class WebTransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ContaService contaService;

    @GetMapping
    public String listarTransacoes(Model model) {
        model.addAttribute("transacoes", transacaoService.listarTodas());
        return "transacoes/listar";
    }

    @GetMapping("/nova")
    public String novaTransacaoForm(Model model) {
        model.addAttribute("transacao", new Transacao());
        model.addAttribute("contas", contaService.listarTodas());
        return "transacoes/formulario";
    }

    @PostMapping("/salvar")
    public String salvarTransacao(@ModelAttribute Transacao transacao) {
        transacaoService.salvar(transacao);
        return "redirect:/transacoes";
    }

    @GetMapping("/editar/{id}")
    public String editarTransacao(@PathVariable Long id, Model model) {
        Transacao transacao = transacaoService.buscarPorId(id);
        model.addAttribute("transacao", transacao);
        model.addAttribute("contas", contaService.listarTodas());
        return "transacoes/formulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluirTransacao(@PathVariable Long id) {
        transacaoService.excluir(id);
        return "redirect:/transacoes";
    }

    @GetMapping("/grafico")
    public String exibirGrafico(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim,
            Model model) {

        if (inicio == null) {
            inicio = LocalDate.now().withDayOfMonth(1);
        }
        if (fim == null) {
            fim = LocalDate.now();
        }

        Map<String, Double[]> dados = transacaoService.obterDadosGraficoPorPeriodo(inicio, fim);
        model.addAttribute("dadosGrafico", dados);
        model.addAttribute("inicio", inicio);
        model.addAttribute("fim", fim);
        return "transacoes/grafico";
    }
}
