package br.com.alura.challenger.backendjava.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challenger.backendjava.model.ImportarTransacao;

@Repository
public interface ImportarTransacaoRepository extends JpaRepository<ImportarTransacao, Long>  {
    public boolean existsByDataTransacao(LocalDate localDate);

    public List<ImportarTransacao> findAllByOrderByDataTransacaoDesc();
}
