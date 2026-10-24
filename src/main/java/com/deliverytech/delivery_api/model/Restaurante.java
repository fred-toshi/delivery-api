package com.deliverytech.delivery_api.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private boolean ativo;
    private BigDecimal avaliacao;

    // Relacionamento com Pedido e Produto, 
    // tratativa através do @JsonIgnore para evitar problemas de serialização
    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;

}
