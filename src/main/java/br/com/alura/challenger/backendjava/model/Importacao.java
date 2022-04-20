package br.com.alura.challenger.backendjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_importacao")
public class Importacao {

    @Id
    @Column(name = "dt_transacoes_importadas")
    private LocalDate dataTransacoesImportadas;
    
    @Column(name = "dt_hr_importacao")
    private LocalDateTime dataHoraImportacao;
   

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "importacao", cascade = {CascadeType.ALL})
    private List<Transacao> transacoes;

    @PrePersist
    private void prePersist(){
        this.dataHoraImportacao = LocalDateTime.now();
    }
}
