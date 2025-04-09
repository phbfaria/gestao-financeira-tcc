package br.com.seuprojeto.controller;

import br.com.seuprojeto.model.Conta;
import br.com.seuprojeto.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contas")
public class WebContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public String listarContas(Model model) {
        model.addAttribute("contas", contaService.listarTodas());
        return "contas/listar";
    }

    @GetMapping("/criar")
    public String criarConta(Model model) {
        model.addAttribute("conta", new Conta());
        return "contas/formulario";
    }

    @PostMapping("/salvar")
    public String salvarConta(@ModelAttribute Conta conta) {
        contaService.salvar(conta);
        return "redirect:/contas";
    }

    @GetMapping("/editar/{id}")
    public String editarConta(@PathVariable Long id, Model model) {
        Conta conta = contaService.buscarPorId(id);
        if (conta != null) {
            model.addAttribute("conta", conta);
            return "contas/formulario";
        } else {
            return "redirect:/contas";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirConta(@PathVariable Long id) {
        contaService.excluir(id);
        return "redirect:/contas";
    }
}
