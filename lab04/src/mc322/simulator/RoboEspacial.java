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

    // Método para usar sensores individualmente
    public void usarSensorPortal() {
        sensorPortal.monitorar(this);
        Portal portal = sensorPortal.checkPortal(this);
        if (portal != null) {
            System.out.println("Portal detectado em (" + portal.getX1() + "," + portal.getY1() + "," + portal.getZ1() + ")");
            System.out.println("Use a opção 13 para atravessar este portal!");
        }
        consumirBateria(10);
    }

    // Método para usar sensor de povoamento
    public void usarSensorPovoamento() {
        sensorPovoamento.monitorar(this);
        planetaAtual = sensorPovoamento.checkPovo(this);
        if (planetaAtual != null) {
            System.out.println("Planeta descoberto e pronto para ser nomeado!");
            System.out.println("Use a opção 14 para nomear este planeta!");
        }
        consumirBateria(10);
    }

    // Método para atravessar um portal
    public void atravessarPortal(){
        // Obtém portal pelo sensor de portal
        Portal portal = sensorPortal.checkPortal(this);

        // Verifica se encontrou um portal
        if(portal != null){        
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
    public void acionarSensores() {
        System.out.println("Robo " + getId() + " acionando sensores espaciais...");
        usarSensores();
        consumirBateria(10);
    }

    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem) {
        System.out.println("Robo " + getId() + " enviando mensagem: '" + mensagem + "' para robo " + ((Robo) destinatario).getId());
    }

    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("Robo " + getId() + " recebeu mensagem: '" + mensagem + "'");
    }

    @Override
    public void realizarDiagnostico() {
        System.out.println("Robo " + getId() + " realizando diagnóstico:");
        System.out.println("- Bateria: " + bateria + "%");
        System.out.println("- Sistema: " + estado);
        System.out.println("- Planetas descobertos: " + qtdePlanetasDescobertos);
    }

    @Override
    public void recarregar() {
        this.bateria = 100;
        System.out.println("Robo " + getId() + " recarregou totalmente.");
    }

    // Método para verificar a quantidade de planetas descobertos
    public int getQtdePlanetasDescobertos() {
        System.out.println("Total de planetas descobertos e nomeados: " + qtdePlanetasDescobertos);
        return qtdePlanetasDescobertos;
    }
    
    public boolean estaEmPlaneta() {
        return planetaAtual != null;
    }
}