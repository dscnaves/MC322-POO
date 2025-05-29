/*
* Main.java
* 
* Última modificação: 28/04/2025
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

        CentralComunicacao central = new CentralComunicacao();

        // Criação dos Robôs
        RoboLimpeza roboLimpeza = new RoboLimpeza("Wall-E", 1, 1, "Norte", 3, ambiente);
        RoboAgricultor roboAgricultor = new RoboAgricultor("Agricultor_Dust", 2, 2, "Leste", 10, 2, "Tomate", ambiente);
        RoboEspacial roboEspacial = new RoboEspacial("R2-D2", 4, 4, "Sul", 10, 3, ambiente);
        RoboRastreador roboRastreador = new RoboRastreador("Pirata", 0, 0, "Oeste", 3, ambiente);

        // Adiciona robôs ao Ambiente
        ambiente.adicionarEntidade(roboLimpeza);
        ambiente.adicionarEntidade(roboAgricultor);
        ambiente.adicionarEntidade(roboEspacial);
        ambiente.adicionarEntidade(roboRastreador);

        // Adiciona Plantinhas
        ambiente.adicionarEntidade(new Plantinha(5, 5, 0, 5, 5, 0, "Tomate"));
        Plantinha tomateDoente = new Plantinha(3, 7, 0, 3, 7, 0, "Tomate");
        ambiente.adicionarEntidade(tomateDoente);
        tomateDoente.setSaudavel(false);
        Plantinha tomateCrescido = new Plantinha(6, 3, 0, 6, 3, 0, "Tomate");
        ambiente.adicionarEntidade(tomateCrescido);
        tomateCrescido.setCrescimento(80);
        
        // Adiciona Lixos
        ambiente.adicionarEntidade(new Lixo(3, 3, 0, 3, 3, 0, "Plastico"));
        ambiente.adicionarEntidade(new Lixo(2, 3, 0, 2, 3, 4, "Plastico"));
        ambiente.adicionarEntidade(new Lixo(8, 8, 0, 8, 8, 0, "Vidro"));
        ambiente.adicionarEntidade(new Lixo(7, 4, 0, 7, 4, 3, "Vidro"));
        ambiente.adicionarEntidade(new Lixo(4, 8, 0, 4, 8, 0, "Metal"));
        ambiente.adicionarEntidade(new Lixo(2, 5, 0, 2, 5, 7, "Metal"));
        ambiente.adicionarEntidade(new Lixo(2, 2, 0, 2, 2, 5, "Metal"));

        // ambiente.adicionarEntidade(new Tesouro(7, 7, 7, 7, 0));

        // Adicionando Planeta e respectivo portal
        ambiente.adicionarEntidade(new Portal(8, 8, 0, 8, 8, 0, 1, 1, 6));
        ambiente.adicionarEntidade(new Planeta(1, 1, 6, 4, 4, 0));

        // Adiciona Tesouros para o RoboRastreador
        ambiente.adicionarEntidade(new Obstaculo(7, 7, 0, 7, 7, 0, TipoObstaculo.TESOURO));
        ambiente.adicionarEntidade(new Obstaculo(3, 5, 0, 3, 5, 0, TipoObstaculo.TESOURO));
        ambiente.adicionarEntidade(new Obstaculo(9, 2, 0, 9, 2, 0, TipoObstaculo.TESOURO));

        // Testes obrigatórios fora do menu
        roboLimpeza.exibirPosicao();
        roboAgricultor.exibirPosicao();
        roboEspacial.exibirPosicao();
        roboRastreador.exibirPosicao();
        System.out.println("\n");
        roboRastreador.checkBauDeTesouros();
        System.out.println("\n");

        try {
            roboLimpeza.usarSensores();
        } catch (RoboDesligadoException e) {
            System.out.println(e.getMessage());
        }
        roboLimpeza.usarSensores();
        System.out.print("\n");
        roboAgricultor.usarSensores();
        System.out.print("\n");
        roboEspacial.usarSensores();
        System.out.print("\n");
        roboRastreador.usarSensores();
        System.out.println("\n");
        roboLimpeza.limparLixo();
        roboAgricultor.regarPlantinha();
        roboAgricultor.tratarPlantaDoente();
        roboAgricultor.colherPlantinha();

        // Início do menu interativo
        Robo[] robos = {roboLimpeza, roboAgricultor, roboEspacial, roboRastreador};

        Robo roboSelecionado = null;

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n========== MENU INTERATIVO ==========");
            System.out.println("1. Listar robôs por tipo e estado");
            System.out.println("2. Selecionar robô para interagir");
            System.out.println("3. Visualizar status do robô e ambiente");
            System.out.println("4. Visualizar mapa 2D do ambiente");
            System.out.println("5. Listar mensagens trocadas entre robôs");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Robôs disponíveis:");
                    for (int i = 0; i < robos.length; i++) {
                        System.out.println((i + 1) + ". " + robos[i].getId() + " - Estado: " + robos[i].getEstado());
                    }
                    break;

                case 2:
                    System.out.println("Escolha o número do robô para interagir:");
                    for (int i = 0; i < robos.length; i++) {
                        System.out.println((i + 1) + ". " + robos[i].getId());
                    }
                    int escolha = sc.nextInt();
                    sc.nextLine();
                    if (escolha >= 1 && escolha <= robos.length) {
                        roboSelecionado = robos[escolha - 1];

                        int subOpcao = -1;
                        while (subOpcao != 0) {
                            System.out.println("\nInteragindo com: " + roboSelecionado.getId());
                            System.out.println("1. Mover robô (frente/trás/direita/esquerda/cima/baixo)");
                            System.out.println("2. Ativar robô");
                            System.out.println("3. Desligar robô");
                            System.out.println("4. Executar tarefa");

                            int menuIndex = 5;

                            if (roboSelecionado instanceof Sensoreavel) {
                                System.out.println(menuIndex++ + ". Leitura de sensores");
                            }
                            if (roboSelecionado instanceof Comunicavel) {
                                System.out.println(menuIndex++ + ". Comunicação (enviar mensagem)");
                            }
                            if (roboSelecionado instanceof Recarregavel) {
                                System.out.println(menuIndex++ + ". Recarregar bateria");
                            }
                            if (roboSelecionado instanceof Diagnosticavel) {
                                System.out.println(menuIndex++ + ". Realizar diagnóstico");
                            }
                            if (roboSelecionado instanceof Autodesligavel) {
                                System.out.println(menuIndex++ + ". Autodesligar se bateria <= 10");
                            }
                            System.out.println("0. Voltar ao menu principal");

                            subOpcao = sc.nextInt();
                            sc.nextLine();

                            switch (subOpcao) {
                                case 1:
                                    System.out.print("Delta X: ");
                                    int dx = sc.nextInt();
                                    System.out.print("Delta Y: ");
                                    int dy = sc.nextInt();
                                    System.out.print("Delta Z: ");
                                    int dz = sc.nextInt();
                                    try {
                                        roboSelecionado.moverPara(dx, dy, dz);
                                    } catch (Exception e) {
                                        System.out.println("Erro ao mover: " + e.getMessage());
                                    }
                                    break;

                                case 2:
                                    roboSelecionado.ligar();
                                    System.out.println("Robô ligado!");
                                    break;

                                case 3:
                                    roboSelecionado.desligar();
                                    System.out.println("Robô desligado!");
                                    break;

                                case 4:
                                    try {
                                        roboSelecionado.executarTarefa();
                                    } catch (Exception e) {
                                        System.out.println("Erro ao executar tarefa: " + e.getMessage());
                                    }
                                    break;

                                case 5:
                                    if (roboSelecionado instanceof Sensoreavel) {
                                        try {
                                            ((Sensoreavel) roboSelecionado).acionarSensores();
                                        } catch (RoboDesligadoException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;

                                case 6:
                                    if (roboSelecionado instanceof Comunicavel) {
                                        System.out.print("Digite a mensagem: ");
                                        String msg = sc.nextLine();
                                        // Escolher outro robô como destinatário
                                        System.out.println("Escolha o número do destinatário:");
                                        for (int i = 0; i < robos.length; i++) {
                                            System.out.println((i + 1) + ". " + robos[i].getId());
                                        }
                                        int dest = sc.nextInt();
                                        sc.nextLine();
                                        Comunicavel destinatario = (Comunicavel) robos[dest - 1];
                                        try {
                                            ((Comunicavel) roboSelecionado).enviarMensagem(destinatario, msg);
                                            central.registrarMensagem(roboSelecionado.getId(), msg);
                                        } catch (RoboDesligadoException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;

                                case 7:
                                    if (roboSelecionado instanceof Diagnosticavel) {
                                        ((Diagnosticavel) roboSelecionado).realizarDiagnostico();
                                    } else {
                                        System.out.println("Este robô não possui capacidade de diagnóstico.");
                                    }
                                    break;

                                case 8:
                                    if (roboSelecionado instanceof Recarregavel) {
                                        ((Recarregavel) roboSelecionado).recarregar();
                                    } else {
                                        System.out.println("Este robô não pode ser recarregado.");
                                    }
                                    break;

                                case 9:
                                    if (roboSelecionado instanceof Autodesligavel) {
                                        ((Autodesligavel) roboSelecionado).desligarSeBateriaBaixa();
                                    } else {
                                        System.out.println("Este robô não possui função de autodesligamento.");
                                    }
                                    break;

                                case 0:
                                    System.out.println("Voltando ao menu principal...");
                                    break;

                                default:
                                    System.out.println("Opção inválida.");
                            }
                        }
                    } else {
                        System.out.println("Robô inválido.");
                    }
                    break;

                case 3:
                    if (roboSelecionado != null) {
                        roboSelecionado.exibirPosicao();
                    } else {
                        System.out.println("Nenhum robô selecionado.");
                    }
                    break;

                case 4:
                    System.out.print("Escolha o andar (0 a " + (ambiente.getAmbienteAltitude() - 1) + "): ");
                    int z = sc.nextInt();
                    ambiente.exibirPlanoXMapa(z);
                    break;

                case 5:
                    central.exibirMensagens();
                    break;

                case 0:
                    System.out.println("Encerrando o simulador...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}
