package com.desperdiciozero;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorAlimentos gerenciador = new GerenciadorAlimentos();
        int opcao = -1;

        System.out.println("Bem-vindo ao Gerenciador de Alimentos - Desperdício Zero!");

        while (opcao != 0) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Adicionar novo alimento");
            System.out.println("2. Listar todos os alimentos");
            System.out.println("3. Ver alimentos próximos do vencimento");
            System.out.println("4. Consultar calorias de uma fruta na internet");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Por favor, digite um número válido.");
                scanner.nextLine(); 
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n-- ADICIONAR ALIMENTO --");
                    System.out.print("Digite o nome do alimento: ");
                    String nome = scanner.nextLine();
                    
                    System.out.print("Digite a data de validade (ex: 2026-12-31): ");
                    String dataString = scanner.nextLine();

                    try {
                        // TENTA CONVERTER A STRING PARA LOCALDATE AQUI!
                        LocalDate dataValidade = LocalDate.parse(dataString);
                        
                        // Cria o objeto passando a data convertida e envia para o gerenciador
                        Alimento novoAlimento = new Alimento(nome, dataValidade);
                        gerenciador.adicionarAlimento(novoAlimento);
                        System.out.println("Alimento adicionado com sucesso!");
                        
                    } catch (DateTimeParseException e) {
                        // Se o usuário digitar "amanhã" em vez de "2026-12-31", o programa não quebra
                        System.out.println("Erro: Formato de data inválido! Use o formato AAAA-MM-DD.");
                    }
                    break;

                case 2:
                    System.out.println("\n-- LISTA DE ALIMENTOS --");
                    gerenciador.listarTodos();
                    break;

                case 3:
                    System.out.println("\n-- PRÓXIMOS DO VENCIMENTO --");
                    System.out.print("Avisar vencimento em até quantos dias? ");
                    if (scanner.hasNextInt()) {
                        int dias = scanner.nextInt();
                        scanner.nextLine(); 
                        gerenciador.listarProximosDoVencimento(dias);
                    } else {
                        System.out.println("Por favor, digite um número inteiro para os dias.");
                        scanner.nextLine();
                    }
                    break;
                    
                case 4:
                    System.out.println("\n-- CONSULTA DE CALORIAS --");
                    System.out.print("Digite uma fruta em INGLÊS (ex: Apple, Banana, Orange): ");
                    String fruta = scanner.nextLine();
                    NutricaoService api = new NutricaoService();
                    System.out.println(api.consultarCalorias(fruta));
                    break;

                case 0:
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("\nOpção inválida! Escolha um número do menu.");
                    break;
            }
        }

        scanner.close(); 
    }


}