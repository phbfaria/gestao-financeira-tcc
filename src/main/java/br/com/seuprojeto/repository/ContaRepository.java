package br.com.seuprojeto.repository;

import br.com.seuprojeto.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
