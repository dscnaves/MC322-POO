/*
* Main.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação da Main de um
* simulador de robôs, responsável por testar e simular o 
* funcionamento das demais classes envolvidas.
*/

package mc322.main;

import java.util.Scanner;
import mc322.ambiente.Ambiente;
import mc322.ambiente.Lixo;
import mc322.ambiente.Obstaculo;
import mc322.ambiente.Planeta;
import mc322.ambiente.Plantinha;
import mc322.ambiente.Portal;
import mc322.ambiente.TipoObstaculo;
import mc322.comunicacao.CentralComunicacao;
import mc322.exceptions.RoboDesligadoException;
import mc322.interfaces.Autodesligavel;
import mc322.interfaces.Comunicavel;
import mc322.interfaces.Diagnosticavel;
import mc322.interfaces.Recarregavel;
import mc322.interfaces.Sensoreavel;
import mc322.missao.Missao;
import mc322.missao.MissaoBuscarPonto;
import mc322.missao.MissaoCircular;
import mc322.missao.MissaoMonitorarZona;
import mc322.robo.AgenteInteligente;
import mc322.robo.Robo;
import mc322.robo.RoboAgricultor;
import mc322.robo.RoboEspacial;
import mc322.robo.RoboLimpeza;
import mc322.robo.RoboRastreador;

public class Main {

    public static void TentarUsarSensores(Robo robo) {
        try {
            robo.usarSensores();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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

        // Adicionando mais Plantinhas de outras espécies
        ambiente.adicionarEntidade(new Plantinha(1, 3, 3, 1, 3, 0, "Alface"));
        ambiente.adicionarEntidade(new Plantinha(2, 3, 3, 2, 3, 0, "Alface"));
        ambiente.adicionarEntidade(new Plantinha(4, 5, 3, 4, 5, 0, "Laranja"));
        ambiente.adicionarEntidade(new Plantinha(8, 2, 9, 8, 2, 0, "Laranja"));  
        
        // Adiciona Lixos
        ambiente.adicionarEntidade(new Lixo(3, 3, 5, 3, 3, 0, "Plastico"));
        ambiente.adicionarEntidade(new Lixo(2, 3, 4, 2, 3, 4, "Plastico"));
        ambiente.adicionarEntidade(new Lixo(8, 8, 3, 8, 8, 0, "Vidro"));
        ambiente.adicionarEntidade(new Lixo(7, 4, 8, 7, 4, 3, "Vidro"));
        ambiente.adicionarEntidade(new Lixo(4, 8, 7, 4, 8, 0, "Metal"));
        ambiente.adicionarEntidade(new Lixo(2, 5, 6, 2, 5, 7, "Metal"));
        ambiente.adicionarEntidade(new Lixo(2, 1, 2, 2, 1, 5, "Metal"));       

        // Adicionar Portal perto de roboEspacial
        ambiente.adicionarEntidade(new Portal(1, 0, 0, 1, 0, 0, 4, 4, 6)); 
        ambiente.adicionarEntidade(new Portal(4, 3, 0, 4, 3, 0, 1, 1, 6)); 
        ambiente.adicionarEntidade(new Portal(8, 7, 0, 8, 7, 0, 1, 1, 6));

        // Adicionando Planeta perto de roboEspacial
        ambiente.adicionarEntidade(new Planeta(8, 9, 9, 9, 9, 0));
        ambiente.adicionarEntidade(new Planeta(1, 2, 6, 4, 4, 0)); 

        // Adicionar Lixo próximo ao RoboRastreador
        ambiente.adicionarEntidade(new Lixo(0, 2, 0, 0, 2, 0, "Metal"));
        ambiente.adicionarEntidade(new Lixo(1, 0, 0, 1, 0, 0, "Metal"));

        // Adiciona Tesouros para o RoboRastreador
        ambiente.adicionarEntidade(new Obstaculo(7, 7, 0, 7, 7, 0, TipoObstaculo.TESOURO));
        ambiente.adicionarEntidade(new Obstaculo(3, 5, 0, 3, 5, 0, TipoObstaculo.TESOURO));
        ambiente.adicionarEntidade(new Obstaculo(9, 2, 0, 9, 2, 0, TipoObstaculo.TESOURO));
        ambiente.adicionarEntidade(new Obstaculo(6, 1, 4, 1, 3, 0, TipoObstaculo.TESOURO));
        ambiente.adicionarEntidade(new Obstaculo(7, 6, 7, 7, 6, 0, TipoObstaculo.TESOURO));

        // Adicionar um alienigena dentro de um planeta próximo do RoboEspacial
        ambiente.adicionarEntidade(new Obstaculo(8, 8, 0, 8, 8, 0, TipoObstaculo.ALIENIGENA)); // Está dentro do Planeta 1

        // Testes obrigatórios fora do menu
        System.out.println("============== Posição Inicial dos Robôs ==============");
        roboLimpeza.exibirPosicao();
        roboAgricultor.exibirPosicao();
        roboEspacial.exibirPosicao();
        roboRastreador.exibirPosicao();
        System.out.println("\n\n");

        roboRastreador.checkBauDeTesouros();
        System.out.println("\n");
        
        //Testes dos sensores obrigatórios
        System.out.println("============== Testando Sensores ==============");
        TentarUsarSensores(roboLimpeza);
        System.out.print("\n");
        TentarUsarSensores(roboAgricultor);
        System.out.print("\n");
        TentarUsarSensores(roboEspacial);
        System.out.print("\n");
        TentarUsarSensores(roboRastreador);
        System.out.println("\n");

        // Testes dos métodos obrigatórios
        roboLimpeza.limparLixo();
        roboAgricultor.regarPlantinha();
        roboAgricultor.tratarPlantaDoente();
        roboAgricultor.colherPlantinha();

        // Testes das missões autônomas
        /*
        roboRastreador.ligar();
        roboRastreador.definirMissao(new MissaoBuscarPonto(0, 1, 0)); 
        roboRastreador.executarMissao(ambiente); 
        roboRastreador.definirMissao(new MissaoCircular()); 
        roboRastreador.executarMissao(ambiente);
        roboRastreador.desligar();
        
        roboLimpeza.ligar();
        roboLimpeza.definirMissao(new MissaoMonitorarZona()); 
        roboLimpeza.executarMissao(ambiente); 
        roboLimpeza.desligar();
        */

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
                        System.out.println((i + 1) + ". " + robos[i].getId()+ "\n");
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
                            if (roboSelecionado instanceof AgenteInteligente) {
                                System.out.println(menuIndex++ + ". Atribuir missão");
                            }
                            if (roboSelecionado instanceof AgenteInteligente) {
                                System.out.println(menuIndex++ + ". Executar missão atribuída");
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
                                            central.registrarMensagem(roboSelecionado.getId(), robos[dest].getId(), msg);
                                        } catch (RoboDesligadoException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                
                                case 7:
                                    if (roboSelecionado instanceof Recarregavel) {
                                        ((Recarregavel) roboSelecionado).recarregar();
                                    } else {
                                        System.out.println("Este robô não pode ser recarregado.");
                                    }
                                    break;

                                case 8:
                                    if (roboSelecionado instanceof Diagnosticavel) {
                                        ((Diagnosticavel) roboSelecionado).realizarDiagnostico();
                                    } else {
                                        System.out.println("Este robô não possui capacidade de diagnóstico.");
                                    }
                                    break;

                                case 9:
                                    if (roboSelecionado instanceof Autodesligavel) {
                                        ((Autodesligavel) roboSelecionado).desligarSeBateriaBaixa();
                                    } else {
                                        System.out.println("Este robô não possui função de autodesligamento.");
                                    }
                                    break;

                                case 10:
                                    if (roboSelecionado instanceof AgenteInteligente) {
                                        System.out.println("Escolha o tipo de missão:");
                                        System.out.println("1 - Buscar Ponto");
                                        System.out.println("2 - Circular");
                                        System.out.println("3 - Monitorar Zona");

                                        int escolhaMissao = sc.nextInt();
                                        sc.nextLine();

                                        Missao missao = null;

                                        switch (escolhaMissao) {
                                            case 1:
                                                System.out.print("Digite X do destino: ");
                                                int x = sc.nextInt();
                                                System.out.print("Digite Y do destino: ");
                                                int y = sc.nextInt();
                                                System.out.print("Digite Z do destino: ");
                                                int zz = sc.nextInt();
                                                sc.nextLine();
                                                missao = new MissaoBuscarPonto(x, y, zz);
                                                break;

                                            case 2:
                                                missao = new MissaoCircular();
                                                break;

                                            case 3:
                                                missao = new MissaoMonitorarZona();
                                                break;

                                            default:
                                                System.out.println("Tipo de missão inválido.");
                                        }

                                        if (missao != null) {
                                            ((AgenteInteligente) roboSelecionado).definirMissao(missao);
                                            System.out.println("Missão atribuída com sucesso!");
                                        }

                                    } else {
                                        System.out.println("Este robô não aceita missões autônomas.");
                                    }
                                    break;
                                case 11:
                                    if (roboSelecionado instanceof AgenteInteligente) {
                                        try {
                                            ((AgenteInteligente) roboSelecionado).executarMissao(ambiente);
                                        } catch (Exception e) {
                                            System.out.println("Erro ao executar missão: " + e.getMessage());
                                        }
                                    } else {
                                        System.out.println("Este robô não possui missões autônomas.");
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
                    // Seleciona o robô para exibir status
                    System.out.println("Escolha o número do robô para interagir:");
                    for (int i = 0; i < robos.length; i++) {
                        System.out.println((i + 1) + ". " + robos[i].getId()+ "\n");
                    }
                    int escolhaRobo = sc.nextInt();
                    sc.nextLine();
                    if (escolhaRobo >= 1 && escolhaRobo <= robos.length) {
                        roboSelecionado = robos[escolhaRobo - 1];
                    }
                    // Exibe o status do robô selecionado
                    System.out.println("Status do robô " + roboSelecionado.getId() + ":");
                    System.out.println("Bateria: " + roboSelecionado.getBateria() + "%");
                    System.out.println("Posição: (" + roboSelecionado.getPosicaoX() + ", " + roboSelecionado.getPosicaoY() + ", " + roboSelecionado.getAltitude() + ")");
                    System.out.println("Estado: " + roboSelecionado.getEstado());

                case 4:
                    System.out.print("Escolha o andar (0 a " + (ambiente.getAmbienteAltitude() - 1) + ") OU digite -1 para ver todos os andares: ");
                    int z = sc.nextInt();
                    if (z == -1) {
                        ambiente.exibirMapa();
                    } else {
                        ambiente.exibirPlanoXMapa(z);
                    }
                    break;

                case 5:
                    central.exibirHistoticoMensagens();
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
