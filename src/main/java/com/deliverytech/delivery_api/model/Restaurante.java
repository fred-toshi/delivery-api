package com.deliverytech.delivery_api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;
    private String endereco;
    private String telefone;
    private BigDecimal taxaEntrega;
    private Integer tempoEntrega;
    private String horarioFuncionamento;
    private boolean ativo;
    private BigDecimal avaliacao;

    /* Data e hora de cadastri do restaurante, preenchida automaticamente */
    @Column
    private LocalDateTime dataCadastro;

    // Relacionamento com Pedido e Produto, 
    // tratativa através do @JsonIgnore para evitar problemas de serialização
    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;

    // Define automaticamente a data de cadastro antes de persistir
    @PrePersist
    protected void aoPersistir() {
        this.dataCadastro = LocalDateTime.now();
    }
}