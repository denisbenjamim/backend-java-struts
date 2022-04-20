package br.com.alura.challenger.backendjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challenger.backendjava.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>  {
    public List<Transacao> findAllByOrderByDataTransacaoDesc();
}
