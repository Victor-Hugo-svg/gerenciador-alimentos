package com.desperdiciozero;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorAlimentos gerenciador = new GerenciadorAlimentos();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("=== Gerenciador de Validade de Alimentos ===");
        System.out.println("Evite o desperdício controlando sua despensa!");

        while (true) {
            System.out.println("\n1. Adicionar Alimento");
            System.out.println("2. Listar Todos");
            System.out.println("3. Alerta de Vencimento (Próximos 3 dias)");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                System.out.print("Nome do alimento: ");
                String nome = scanner.nextLine();
                System.out.print("Data de validade (DD/MM/AAAA): ");
                String dataStr = scanner.nextLine();
                
                try {
                    LocalDate validade = LocalDate.parse(dataStr, formatter);
                    gerenciador.adicionarAlimento(new Alimento(nome, validade));
                    System.out.println("Alimento adicionado com sucesso!");
                } catch (DateTimeParseException e) {
                    System.out.println("Erro: Formato de data inválido. Use DD/MM/AAAA.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            } else if (opcao.equals("2")) {
                System.out.println("\n--- Sua Despensa ---");
                for (Alimento a : gerenciador.listarTodos()) {
                    System.out.println(a);
                }
            } else if (opcao.equals("3")) {
                System.out.println("\n--- ATENÇÃO: Vencendo em breve ---");
                List<Alimento> alertas = gerenciador.listarProximosDoVencimento(3);
                if (alertas.isEmpty()) {
                    System.out.println("Nenhum alimento vencendo nos próximos 3 dias.");
                } else {
                    for (Alimento a : alertas) {
                        System.out.println(a);
                    }
                }
            } else if (opcao.equals("4")) {
                System.out.println("Encerrando... Evite o desperdício!");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}


