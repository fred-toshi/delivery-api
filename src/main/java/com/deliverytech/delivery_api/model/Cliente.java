package com.deliverytech.delivery_api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
@Entity
public class Cliente {

    /*
    @id e @GeneratedValue(strategy = GenerationType.IDENTITY) informa que o campo é a chave primaria e que os valores serão gerados automaticamente pelo banco de dados, no caso do MySQL, o campo será auto_increment
    
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private boolean ativo;

    /*
    TO-DO: add Data em que o Cliente foi cadastrado no sistema
    private LocalDateTime dataCastrado; 
    VER UUIDV4, UUID V7, SNOWFLAKE...
    https://en.wikipedia.org/wiki/Universally_unique_identifier
    https://medium.com/@dinesharney/understanding-uuid-v4-uuid-v7-snowflake-id-and-nano-id-in-simple-terms-c50acf185b00
    */

    /*
    @JsonIgnore evita LazyInitialization ao seriealizar o Cliente em Json,
    ja que "pedidos" é uma coleção Lazy e a sessao do hibernates já que está fechado
    quando o Json tenta serializar (Open-in-view=false)
    */
   @JsonIgnore
   @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
   private List<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

}