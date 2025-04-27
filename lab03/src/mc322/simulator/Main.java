/*
 * Main.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação da Main de um
 * simulador de robôs, responsável por testar e simular o 
 * funcionamento das demais classes envolvidas.
 */

 package mc322.simulator;

 import java.util.Scanner;
 
 import mc322.simulator.robos.*;
 
 public class Main {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
 
         // Criação do Ambiente
         Ambiente ambiente = new Ambiente(10, 10, 10);
 
         // Criação dos Robôs
         RoboLimpeza roboLimpeza = new RoboLimpeza("Wall-E", 1, 1, "Norte", 3, ambiente);
         RoboAgricultor roboAgricultor = new RoboAgricultor("Chupacabra", 2, 2, "Leste", 10, 2, "Tomate", ambiente);
         RoboEspacial roboEspacial = new RoboEspacial("R2-D2", 4, 4, "Sul", 10, 3, ambiente);
         RoboRastreador roboRastreador = new RoboRastreador("Pirata", 0, 0, "Oeste", 3, ambiente);
 
         // Adiciona os robôs ao Ambiente
         ambiente.adicionarRobo(roboLimpeza);
         ambiente.adicionarRobo(roboAgricultor);
         ambiente.adicionarRobo(roboEspacial);
         ambiente.adicionarRobo(roboRastreador);
 
         // Adiciona Obstáculos
         ambiente.adicionarObstaculo(new Plantinha(5, 5, "Tomate"));
         ambiente.adicionarObstaculo(new Lixo(3, 3, "Plastico"));
         ambiente.adicionarObstaculo(new Tesouro(7, 7, 2));
         ambiente.adicionarObstaculo(new Portal(8, 8, 3));
 
         int opcao = -1;
 
         while (opcao != 9) {
             System.out.println("\n--- Menu ---");
             System.out.println("1. Mover Robô");
             System.out.println("2. Usar Sensores");
             System.out.println("3. Regar Plantinha");
             System.out.println("4. Tratar Plantinha");
             System.out.println("5. Colher Plantinha");
             System.out.println("6. Limpar Lixo");
             System.out.println("7. Nomear Planeta");
             System.out.println("8. Exibir Mapa");
             System.out.println("9. Sair");
             System.out.print("Escolha uma opção: ");
 
             opcao = sc.nextInt();
             sc.nextLine();
 
             switch (opcao) {
                 case 1:
                     System.out.print("Escolha o robô (1-Limpeza, 2-Agricultor, 3-Espacial, 4-Rastreador): ");
                     int escolha = sc.nextInt();
                     System.out.print("Delta X: ");
                     int dx = sc.nextInt();
                     System.out.print("Delta Y: ");
                     int dy = sc.nextInt();
                     System.out.print("Delta Z: ");
                     int dz = sc.nextInt();
                     if (escolha == 1) roboLimpeza.mover(dx, dy, dz);
                     else if (escolha == 2) roboAgricultor.mover(dx, dy, dz);
                     else if (escolha == 3) roboEspacial.mover(dx, dy, dz);
                     else if (escolha == 4) roboRastreador.mover(dx, dy, dz);
                     break;
 
                 case 2:
                     roboLimpeza.usarSensores();
                     roboAgricultor.usarSensores();
                     roboEspacial.usarSensores();
                     roboRastreador.usarSensores();
                     break;
 
                 case 3:
                     roboAgricultor.regarPlantinha();
                     break;
 
                 case 4:
                     roboAgricultor.tratarPlantaDoente();
                     break;
 
                 case 5:
                     roboAgricultor.colherPlantinha();
                     break;
 
                 case 6:
                     roboLimpeza.irAteLixoProximo();
                     roboLimpeza.classificarELimparLixo();
                     break;
 
                 case 7:
                     System.out.print("Digite o nome do planeta: ");
                     String nomePlaneta = sc.nextLine();
                     roboEspacial.nomearPlaneta(nomePlaneta);
                     break;
 
                 case 8:
                     ambiente.exibirMapa();
                     break;
 
                 case 9:
                     System.out.println("Encerrando simulador...");
                     break;
 
                 default:
                     System.out.println("Opção inválida.");
             }
         }
 
         sc.close();
     }
 }
 