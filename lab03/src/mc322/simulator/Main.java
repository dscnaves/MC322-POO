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



 import java.util.Scanner;

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
 
         // Adiciona robôs ao Ambiente
         ambiente.adicionarRobo(roboLimpeza);
         ambiente.adicionarRobo(roboAgricultor);
         ambiente.adicionarRobo(roboEspacial);
         ambiente.adicionarRobo(roboRastreador);
 
         // Adiciona Obstáculos
         ambiente.adicionarObstaculo(new Plantinha(5, 5, 0, 5, 5, 0, "Tomate"));
         ambiente.adicionarObstaculo(new Lixo(3, 3, 0, 3, 3, 0, "Plastico"));
         //ambiente.adicionarObstaculo(new Tesouro(7, 7, 7, 7, 0));
         ambiente.adicionarObstaculo(new Portal(8, 8, 0, 8, 8, 0, 1, 1, 0));
 
         // Testes obrigatórios fora do menu
         roboLimpeza.exibirPosicao();
         roboAgricultor.exibirPosicao();
         roboEspacial.exibirPosicao();
         roboRastreador.exibirPosicao();
         roboRastreador.checkCriptomoeda();
         roboLimpeza.usarSensores();
         roboAgricultor.usarSensores();
         roboEspacial.usarSensores();
         roboRastreador.usarSensores();
         roboLimpeza.limparLixo();
         roboAgricultor.regarPlantinha();
         roboAgricultor.tratarPlantaDoente();
         roboAgricultor.colherPlantinha();
 
         // Início do menu interativo
         int opcao = -1;
 
         while (opcao != 0) {
             System.out.println("\n--- MENU INTERATIVO ---");
             System.out.println("1. Mover Robô");
             System.out.println("2. Visualizar Status dos Robôs");
             System.out.println("3. Visualizar Ambiente");
             System.out.println("4. Usar Sensores");
             System.out.println("5. Regar Plantinha");
             System.out.println("6. Tratar Plantinha Doente");
             System.out.println("7. Colher Plantinha");
             System.out.println("8. Limpar e Classificar Lixo");
             System.out.println("9. Nomear Planeta");
             System.out.println("10. Visualizar 1 andar de Ambiente");
             System.out.println("0. Sair");
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
                     roboLimpeza.exibirPosicao();
                     roboAgricultor.exibirPosicao();
                     roboEspacial.exibirPosicao();
                     roboRastreador.exibirPosicao();
                     break;
                 case 3:
                     ambiente.exibirMapa();
                     break;
                 case 4:
                     roboLimpeza.usarSensores();
                     roboAgricultor.usarSensores();
                     roboEspacial.usarSensores();
                     roboRastreador.usarSensores();
                     break;
                 case 5:
                     roboAgricultor.regarPlantinha();
                     break;
                 case 6:
                     roboAgricultor.tratarPlantaDoente();
                     break;
                 case 7:
                     roboAgricultor.colherPlantinha();
                     break;
                 case 8:
                     roboLimpeza.classificarELimparLixo();
                     break;
                 case 9:
                     roboEspacial.nomearPlaneta();
                     break;
                 case 10:
                     System.out.print("Escolha um andar de " + 0 + " a " + ambiente.getAmbienteAltitude() + ": ");
                     int plano = sc.nextInt();
                     ambiente.exibirPlanoXMapa(plano);
                     break;
                 case 0:
                     System.out.println("Encerrando o simulador...");
                     break;
                 default:
                     System.out.println("Opção inválida!");
             }
         }
 
         sc.close();
     }
 }
 