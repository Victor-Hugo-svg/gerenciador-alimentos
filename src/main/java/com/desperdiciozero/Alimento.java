package com.desperdiciozero;

import java.time.temporal.Temporal;

public class Alimento {
	private String nome;
	private Temporal dataValidade;
	
	public Alimento(String nome, Temporal dataValidade2) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do alimento não pode ser vazio.");
        }
        this.nome = nome;
        this.dataValidade = dataValidade2;
    }

    public String getNome() { return nome; }
    public Temporal getDataValidade() { return dataValidade; }

    @Override
    public String toString() {
        return nome + " (Vence em: " + dataValidade + ")";
    }
}


