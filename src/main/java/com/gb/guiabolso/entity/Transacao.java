package com.gb.guiabolso.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer ano;

    @Column
    private Integer mes;

    @Column
    private String descricao;

    @Column
    private String data;

    @Column
    private String valor;

    @Column
    private Boolean duplicated;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @NotNull
    @JsonBackReference
    private Usuario usuarioId;
}
