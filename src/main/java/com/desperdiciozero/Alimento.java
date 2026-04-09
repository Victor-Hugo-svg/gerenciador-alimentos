package com.desperdiciozero;

import java.time.LocalDate;

public class Alimento {
	private String nome;
	private LocalDate dataValidade;
	
	public Alimento(String nome, LocalDate dataValidade) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do alimento não pode ser vazio.");
        }
        this.nome = nome;
        this.dataValidade = dataValidade;
    }

    public String getNome() { return nome; }
    public LocalDate getDataValidade() { return dataValidade; }

    @Override
    public String toString() {
        return nome + " (Vence em: " + dataValidade + ")";
    }
}


