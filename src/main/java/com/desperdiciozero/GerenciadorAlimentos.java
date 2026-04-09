package com.desperdiciozero;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorAlimentos {
    private List<Alimento> despensa = new ArrayList<>();

    public void adicionarAlimento(Alimento alimento) {
        despensa.add(alimento);
    }

    public List<Alimento> listarTodos() {
        return new ArrayList<>(despensa);
    }

    public List<Alimento> listarProximosDoVencimento(int diasAlerta) {
        List<Alimento> alertas = new ArrayList<>();
        LocalDate hoje = LocalDate.now();

        for (Alimento a : despensa) {
            long diasAteVencer = ChronoUnit.DAYS.between(hoje, a.getDataValidade());
            if (diasAteVencer >= 0 && diasAteVencer <= diasAlerta) {
                alertas.add(a);
            }
        }
        return alertas;
    }
}