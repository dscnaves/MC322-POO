/*
* RoboEspacial.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de RoboEspacial
* o RoboEspacial é um robô aéreo que explora o espaço.
*/

import java.util.Scanner;

public class RoboEspacial extends RoboAereo implements 
    Sensoreavel, Comunicavel, Diagnosticavel, Recarregavel {

    // Atributo para armazenar a quantidade de planetas descobertos
    private int qtdePlanetasDescobertos;
    private SensorPortal sensorPortal;
    private SensorPovoamento sensorPovoamento;
    private Planeta planetaAtual;

    // Construtor da classe RoboEspacial
    public RoboEspacial(String id, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, Ambiente ambiente) {
        super(id, posicaoX, posicaoY, direcao, altitudeMaxima, ambiente);
        this.qtdePlanetasDescobertos = 0;
        this.planetaAtual = null;
        
        // Adicionar sensores ao RoboEspacial
        this.sensorPortal = new SensorPortal(6.0);
        this.sensorPovoamento = new SensorPovoamento(7.0);
        this.adicionarSensor(sensorPortal);
        this.adicionarSensor(sensorPovoamento);
    }

     @Override
    // Método para funções bases do RoboEspacial
    public void executarTarefa(){
        // Verifica se o robô está em um planeta
        if (estaEmPlaneta()) {
            System.out.println("Robo " + getId() + " está em um planeta. Procurar sinais de vida extraterrestre...");
            usarSensorPovoamento();
        } else {
            System.out.println("Robo " + getId() + " não está em um planeta. Explorando o espaço...");
            atravessarPortal();
        }
    }

    // Método para usar sensores individualmente
    public void usarSensorPortal() {
        sensorPortal.monitorar(this);
        Portal portal = sensorPortal.checkPortal(this);
        if (portal != null) {
            System.out.println("Portal detectado em (" + portal.getX1() + "," + portal.getY1() + "," + portal.getZ1() + ")");
        }
        consumirBateria(10);
    }

    // Método para usar sensor de povoamento
    public void usarSensorPovoamento() {
        sensorPovoamento.monitorar(this);
        planetaAtual = sensorPovoamento.roboDentroPlaneta(this);
        if (planetaAtual != null) {
            System.out.println("Planeta descoberto! Precisamos verificar se já é povoado.");
            if (sensorPovoamento.planetaJaPovoado(ambiente, planetaAtual)) {
                System.out.println("Planeta já está povoado.");
            } else {
                System.out.println("Planeta desabitado! Pode ser nomeado.");
                // // Nomear o planeta
                // System.out.println("Nome do planeta: ");
                // try (Scanner scanner = new Scanner(System.in)) {
                //     String nomePlaneta = scanner.nextLine(); 
                //     nomearPlaneta(nomePlaneta);              
                // }
            }
        }
        consumirBateria(10);
    }
    // Método para atravessar um portal
    public void atravessarPortal(){
        // Obtém portal pelo sensor de portal
        Portal portal = sensorPortal.checkPortal(this);

        // Verifica se encontrou um portal
        if(portal != null){    
            System.out.println("Portal detectado em (" + portal.getX1() + "," + portal.getY1() + "," + portal.getZ1() + ")");
            System.out.println("Atravessando portal...");

            // Atualiza o mapa que o robo deicou sua posição inicial
            ambiente.atualizarMapa(posicaoX, posicaoY, altitude, TipoEntidade.VAZIO);
            
            // Atualiza a posição do robo para a posição destino do portal
            setPosicaoX(portal.getDestinoX());
            setPosicaoY(portal.getDestinoY());
            setAltitude(portal.getDestinoZ());

            // Atualiza o mapa com a nova posição do robo
            ambiente.atualizarMapa(portal.getDestinoX(), portal.getDestinoY(), portal.getDestinoZ(), TipoEntidade.ROBO);
            System.out.println("Portal atravessado com sucesso! Nova posição: (" + 
                             posicaoX + "," + posicaoY + "," + altitude + ")");
            
            // Verifica se chegou em um novo planeta
            usarSensorPovoamento();
        } else {
            System.out.println("Nenhum portal próximo para atravessar.");
        }
        consumirBateria(10);
    }

    // Método para nomear um planeta
    public void nomearPlaneta(String nome) {
        if (planetaAtual != null) {
            if (planetaAtual.getNome().isEmpty()) {
                planetaAtual.setNome(nome);
                qtdePlanetasDescobertos++;
                System.out.println("Planeta nomeado como: " + nome);
                System.out.println("Total de planetas descobertos: " + qtdePlanetasDescobertos);
                planetaAtual = null; // Reseta após nomear
                consumirBateria(10);
            } else {
                System.out.println("Este planeta já possui um nome: " + planetaAtual.getNome());
            }
        } else {
            System.out.println("Nenhum planeta disponível para nomear. Use o sensor de povoamento primeiro.");
        }
    }

    @Override
    // Método para usar sensores incluindo sensores de portal e povoamento => para isso, é necessário gastar 10% da bateria
    public void acionarSensores() {
        System.out.println("Robo " + getId() + " acionando sensores espaciais...");
        usarSensores();
        consumirBateria(10);
    }

    @Override
    // Método para enviar mensagem para outro robo
    public void enviarMensagem(Comunicavel destinatario, String mensagem) {
        System.out.println("Robo " + getId() + " enviando mensagem: '" + mensagem + "' para robo " + ((Robo) destinatario).getId());
    }

    @Override
    // Método para receber mensagem de outro robo
    public void receberMensagem(String mensagem) {
        System.out.println("Robo " + getId() + " recebeu mensagem: '" + mensagem + "'");
    }

    @Override
    // Método para realizar diagnóstico do robo incluindo a bateria, o estado do sistema e a quantidade de planetas descobertos
    public void realizarDiagnostico() {
        System.out.println("Robo " + getId() + " realizando diagnóstico:");
        System.out.println("- Bateria: " + bateria + "%");
        System.out.println("- Sistema: " + estado);
        System.out.println("- Planetas descobertos: " + qtdePlanetasDescobertos);
    }

    @Override
    // Método para recarregar a bateria do robo, definindo a bateria como 100%
    public void recarregar() {
        this.bateria = 100;
        System.out.println("Robo " + getId() + " recarregou totalmente.");
    }

    // Método para verificar a quantidade de planetas descobertos
    public int getQtdePlanetasDescobertos() {
        System.out.println("Total de planetas descobertos e nomeados: " + qtdePlanetasDescobertos);
        return qtdePlanetasDescobertos;
    }
    
    // Método para verificar se o robo está em um planeta
    public boolean estaEmPlaneta() {
        return planetaAtual != null;
    }
}