package com.desperdiciozero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GerenciadorAlimentosTest {

    @Test
    public void testAdicionarAlimentoCaminhoFeliz() {
        GerenciadorAlimentos gerenciador = new GerenciadorAlimentos();
        Alimento maca = new Alimento("Maçã", LocalDate.now().plusDays(5));
        
        gerenciador.adicionarAlimento(maca);
        assertEquals(1, gerenciador.listarTodos().size());
        assertEquals("Maçã", gerenciador.listarTodos().get(0).getNome());
    }

    @Test
    public void testEntradaInvalidaNomeVazio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Alimento("", LocalDate.now());
        });
        assertEquals("O nome do alimento não pode ser vazio.", exception.getMessage());
    }

    @Test
    public void testAlertaVencimentoCasoLimite() {
        GerenciadorAlimentos gerenciador = new GerenciadorAlimentos();
        LocalDate hoje = LocalDate.now();
        
        // Vence amanhã (deve alertar)
        gerenciador.adicionarAlimento(new Alimento("Iogurte", hoje.plusDays(1)));
        // Vence em 10 dias (NÃO deve alertar)
        gerenciador.adicionarAlimento(new Alimento("Feijão", hoje.plusDays(10)));

        List<Alimento> alertas = gerenciador.listarProximosDoVencimento(3);
        
        assertEquals(1, alertas.size());
        assertEquals("Iogurte", alertas.get(0).getNome());
    }
}
