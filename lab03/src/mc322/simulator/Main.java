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
         RoboAgricultor roboAgricultor = new RoboAgricultor("Agricultor_Dust", 2, 2, "Leste", 10, 2, "Tomate", ambiente);
         RoboEspacial roboEspacial = new RoboEspacial("R2-D2", 4, 4, "Sul", 10, 3, ambiente);
         RoboRastreador roboRastreador = new RoboRastreador("Pirata", 0, 0, "Oeste", 3, ambiente);
 
         // Adiciona robôs ao Ambiente
         ambiente.adicionarRobo(roboLimpeza);
         ambiente.adicionarRobo(roboAgricultor);
         ambiente.adicionarRobo(roboEspacial);
         ambiente.adicionarRobo(roboRastreador);
 
         // Adiciona Plantinhas
         ambiente.adicionarObstaculo(new Plantinha(5, 5, 0, 5, 5, 0, "Tomate"));
         ambiente.adicionarObstaculo(new Plantinha(3, 7, 0, 3, 7, 0, "Tomate"));
         ambiente.adicionarObstaculo(new Plantinha(10, 3, 0, 10, 3, 0, "Tomate"));
         
         // Adiciona Lixos
         ambiente.adicionarObstaculo(new Lixo(3, 3, 0, 3, 3, 0, "Plastico"));
         ambiente.adicionarObstaculo(new Lixo(2, 3, 0, 2, 3, 4, "Plastico"));
         ambiente.adicionarObstaculo(new Lixo(8, 8, 0, 8, 8, 0, "Vidro"));
         ambiente.adicionarObstaculo(new Lixo(7, 4, 0, 7, 4, 3, "Vidro"));
         ambiente.adicionarObstaculo(new Lixo(4, 8, 0, 4, 8, 0, "Metal"));
         ambiente.adicionarObstaculo(new Lixo(2, 5, 0, 2, 5, 7, "Metal"));
         ambiente.adicionarObstaculo(new Lixo(2, 2, 0, 2, 2, 5, "Metal"));

         // ambiente.adicionarObstaculo(new Tesouro(7, 7, 7, 7, 0));

         // Adicionando Planeta e respectivo portal
         ambiente.adicionarObstaculo(new Portal(8, 8, 0, 8, 8, 0, 1, 1, 6));
         ambiente.adicionarObstaculo(new Planeta(1, 1, 6, 4, 4, 0));

         // Adiciona Tesouros para o RoboRastreador
         ambiente.adicionarObstaculo(new Obstaculo(7, 7, 0, 7, 7, 0, TipoObstaculo.TESOURO));
         ambiente.adicionarObstaculo(new Obstaculo(3, 5, 0, 3, 5, 0, TipoObstaculo.TESOURO));
         ambiente.adicionarObstaculo(new Obstaculo(9, 2, 0, 9, 2, 0, TipoObstaculo.TESOURO));

         // Testes obrigatórios fora do menu
         roboLimpeza.exibirPosicao();
         roboAgricultor.exibirPosicao();
         roboEspacial.exibirPosicao();
         roboRastreador.exibirPosicao();
         roboRastreador.checkBauDeTesouros();
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
             System.out.println("11. Usar Sensor de Detector de metais");
             System.out.println("11. Extrair Tesouro (Rastreador)");
             System.out.println("12. Verificar Tesouros Coletados");
             System.out.println("13. Atravessar Portal (Robo Espacial)");
            System.out.println("14. Nomear Planeta (Robo Espacial)");
            System.out.println("15. Usar Sensor de Portal (Robo Espacial)");
            System.out.println("16. Usar Sensor de Povoamento (Robo Espacial)");
            System.out.println("17. Ver Planetas Descobertos");
             System.out.println("0. Sair");
             System.out.print("Escolha uma opção: ");
             System.out.print("\n");
 
             opcao = sc.nextInt();
             sc.nextLine();
 
             switch (opcao) {
                 case 1:
                     System.out.print("Escolha o robô (1-Limpeza, 2-Agricultor, 3-Espacial, 4-Rastreador): ");
                     System.out.print("\n");
                     int escolha1 = sc.nextInt();
                     System.out.print("Delta X: ");
                     int dx = sc.nextInt();
                     System.out.print("Delta Y: ");
                     int dy = sc.nextInt();
                     System.out.print("Delta Z: ");
                     int dz = sc.nextInt();
                     if (escolha1 == 1) roboLimpeza.mover(dx, dy, dz);
                     else if (escolha1 == 2) roboAgricultor.mover(dx, dy, dz);
                     else if (escolha1 == 3) roboEspacial.mover(dx, dy, dz);
                     else if (escolha1 == 4) roboRastreador.mover(dx, dy, dz);
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
                     System.out.print("Escolha o robô (1-Limpeza, 2-Agricultor, 3-Espacial, 4-Rastreador): ");
                     System.out.print("\n");
                     int escolha4 = sc.nextInt();
                     if (escolha4 == 1) roboLimpeza.usarSensores();
                     else if (escolha4 == 2) roboAgricultor.usarSensores();
                     else if (escolha4 == 3) roboEspacial.usarSensores();
                     else if (escolha4 == 4){
                        roboRastreador.usarSensores();
                        if (roboRastreador.temTesouroLocalizado()) {
                            System.out.println("Use a opção 11 para extrair o tesouro detectado!");
                        }
                     }
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
                 case 11:
                     System.out.println("Extraindo tesouro com Robo Rastreador...");
                     roboRastreador.extrairTesouro();
                     break;
                 case 12:
                     roboRastreador.checkBauDeTesouros();
                     break;
                 case 13: // Atravessar Portal
                     roboEspacial.atravessarPortal();
                     break;
                     
                 case 14: // Nomear Planeta
                     if (roboEspacial.estaEmPlaneta()) {
                         System.out.print("Digite o nome para o planeta: ");
                         String nomePlaneta = sc.nextLine();
                         roboEspacial.nomearPlaneta(nomePlaneta);
                     } else {
                         System.out.println("Nenhum planeta disponível para nomear. Use o sensor de povoamento primeiro.");
                     }
                     break;
                     
                 case 15: // Usar Sensor de Portal
                     roboEspacial.usarSensorPortal();
                     break;
                     
                 case 16: // Usar Sensor de Povoamento
                     roboEspacial.usarSensorPovoamento();
                     break;
                     
                 case 17: // Ver Planetas Descobertos
                     roboEspacial.getQtdePlanetasDescobertos();
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
 