package com.desperdiciozero;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class NutricaoServiceTest {

    @Test
    public void testeIntegracaoApiFruityvice() {
        NutricaoService service = new NutricaoService();
        String resultado = service.consultarCalorias("Apple");
        
        // Verifica se a resposta da internet contém a palavra "SUCESSO"
        assertTrue(resultado.contains("SUCESSO"));
    }
}