package dev.guilhermesv.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @PrePersist // pre inclusao
    private void persistdataCricao() {
        dataCriacao = LocalDateTime.now();
    }

    @PreUpdate // pre alteracao
    private void persistdataAlteracao() {
        dataAlteracao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getdataCricao() {
        return dataCriacao;
    }

    public void setdataCricao(LocalDateTime dataCricao) {
        this.dataCriacao = dataCricao;
    }

    public LocalDateTime getdataAlteracao() {
        return dataAlteracao;
    }

    public void setdataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }


}
