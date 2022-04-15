package br.com.alura.challenger.backendjava.model;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_importar_transacao")
public class ImportarTransacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowId;
    private String bancoOrigem;
    private String agenciaOrigem;
    private String contaOrigem;

    private String bancoDestino;
    private String agenciaDestino;
    private String contaDestino;

    private BigDecimal valorTransacao;
    
    @Transient
    private LocalDateTime dataHoraTransacao;
    private LocalDate dataTransacao;
    private LocalTime horaTransacao;
    
    private LocalDateTime dataHoraImportacao;

    public ImportarTransacao(String[] dados) throws CampoInvalidoException {
        this.bancoOrigem = validarCampo(dados[0]);
        this.agenciaOrigem = validarCampo(dados[1]);
        this.contaOrigem = validarCampo(dados[2]);
        this.bancoDestino = validarCampo(dados[3]);
        this.agenciaDestino = validarCampo(dados[4]);
        this.contaDestino = validarCampo(dados[5]);
        this.valorTransacao = new BigDecimal(validarCampo(dados[6]));
        this.dataHoraTransacao = LocalDateTime.parse(validarCampo(dados[7]));
        this.dataTransacao = this.dataHoraTransacao.toLocalDate();
        this.horaTransacao = this.dataHoraTransacao.toLocalTime();
    }

    private String validarCampo(String campo) throws CampoInvalidoException {
        if (campo == null || campo.trim().isEmpty())
            throw new CampoInvalidoException(MessageFormat.format("O campo {0} é invalido.", campo));

        return campo;
    }

    @PrePersist
    private void prePersist(){
        this.dataHoraImportacao = LocalDateTime.now();
    }
}
