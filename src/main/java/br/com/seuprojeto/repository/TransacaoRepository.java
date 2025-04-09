package br.com.seuprojeto.repository;

import br.com.seuprojeto.model.Transacao;
import br.com.seuprojeto.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    boolean existsByContaId(Long contaId);

    List<Transacao> findByCategoria(String categoria);

    List<Transacao> findByDataBetween(LocalDate inicio, LocalDate fim);

    List<Transacao> findByTipoAndDataBetween(TipoTransacao tipo, LocalDate inicio, LocalDate fim);
}
