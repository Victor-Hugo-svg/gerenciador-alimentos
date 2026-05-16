package com.desperdiciozero;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NutricaoService {

    public String consultarCalorias(String fruta) {
        String url = "https://www.fruityvice.com/api/fruit/" + fruta.toLowerCase();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonObject nutritions = jsonObject.getAsJsonObject("nutritions");
                double calorias = nutritions.get("calories").getAsDouble();
                return "SUCESSO: A fruta " + fruta + " tem " + calorias + " calorias por 100g.";
            } else {
                return "Fruta não encontrada na base de dados.";
            }
        } catch (Exception e) {
            return "Erro de conexão: " + e.getMessage();
        }
    }
}