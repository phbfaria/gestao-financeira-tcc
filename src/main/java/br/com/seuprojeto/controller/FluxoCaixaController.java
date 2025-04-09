package br.com.seuprojeto.controller;

import br.com.seuprojeto.dto.FluxoCaixaDTO;
import br.com.seuprojeto.model.Transacao;
import br.com.seuprojeto.service.FluxoCaixaService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/fluxo-caixa")
public class FluxoCaixaController {

    @Autowired
    private FluxoCaixaService fluxoCaixaService;

    @GetMapping
    public String exibirFluxoCaixa(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            Model model) {

        if (dataInicio == null) {
            dataInicio = LocalDate.now().withDayOfMonth(1);
        }

        if (dataFim == null) {
            dataFim = LocalDate.now();
        }

        FluxoCaixaDTO dto = fluxoCaixaService.calcularFluxoCaixaMensal(dataInicio, dataFim);
        model.addAttribute("dados", dto);
        model.addAttribute("transacoes", dto.getTransacoes());
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        return "fluxo-caixa";
    }

    @GetMapping("/pdf")
    public void exportarPdf(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            HttpServletResponse response) throws IOException {

        FluxoCaixaDTO dto = fluxoCaixaService.calcularFluxoCaixaMensal(dataInicio, dataFim);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=fluxo-caixa.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        document.add(new Paragraph("Fluxo de Caixa", titleFont));
        document.add(new Paragraph("Período: " + dataInicio + " até " + dataFim, normalFont));
        document.add(new Paragraph("Receitas: R$ " + dto.getReceitas(), normalFont));
        document.add(new Paragraph("Despesas: R$ " + dto.getDespesas(), normalFont));
        document.add(new Paragraph("Saldo: R$ " + dto.getSaldo(), normalFont));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Transações:", titleFont));

        PdfPTable tabela = new PdfPTable(5);
        tabela.setWidthPercentage(100);
        tabela.addCell("Data");
        tabela.addCell("Descrição");
        tabela.addCell("Valor");
        tabela.addCell("Tipo");
        tabela.addCell("Conta");

        for (Transacao t : dto.getTransacoes()) {
            tabela.addCell(t.getData().toString());
            tabela.addCell(t.getDescricao());
            tabela.addCell("R$ " + t.getValor());
            tabela.addCell(t.getTipo().toString());
            tabela.addCell(t.getConta() != null ? t.getConta().getNome() : "Sem conta");
        }

        document.add(tabela);
        document.close();
    }
}
