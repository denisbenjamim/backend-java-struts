package br.com.alura.challenger.backendjava.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challenger.backendjava.model.Importacao;

@Repository
public interface ImportacaoRepository extends JpaRepository<Importacao, LocalDate>  {
    public boolean existsByDataTransacoesImportadas(LocalDate localDate);

    public List<Importacao> findAllByOrderByDataTransacoesImportadasDesc();
}
