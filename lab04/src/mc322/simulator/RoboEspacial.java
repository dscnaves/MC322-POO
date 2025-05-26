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

public class RoboEspacial extends RoboAereo {

    // Atributo para armazenar a quantidade de planetas descobertos
    private int qtdePlanetasDescobertos;
    private SensorPortal sensorPortal;
    private SensorPovoamento sensorPovoamento;
    private Planeta planetaAtual;

    // Construtor da classe RoboEspacial
    public RoboEspacial(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima, altitude, ambiente);
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
    }

    // Método para usar sensor de povoamento
    public void usarSensorPovoamento() {
        sensorPovoamento.monitorar(this);
        planetaAtual = sensorPovoamento.checkPovo(this);
        if (planetaAtual != null) {
            System.out.println("Planeta descoberto e pronto para ser nomeado!");
            System.out.println("Use a opção 14 para nomear este planeta!");
        }
    }

    // Método para atravessar um portal
    public void atravessarPortal(){
        // Obtém portal pelo sensor de portal
        Portal portal = sensorPortal.checkPortal(this);

        // Verifica se encontrou um portal
        if(portal != null){        
            System.out.println("Atravessando portal...");

            // Atualiza o mapa que o robo deicou sua posição inicial
            ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "_");
            
            // Atualiza a posição do robo para a posição destino do portal
            setPosicaoX(portal.getDestinoX());
            setPosicaoY(portal.getDestinoY());
            setAltitude(portal.getDestinoZ());

            // Atualiza o mapa com a nova posição do robo
            ambiente.atualizarMapa(portal.getDestinoX(), portal.getDestinoY(), portal.getDestinoZ(), "🤖");
            System.out.println("Portal atravessado com sucesso! Nova posição: (" + 
                             posicaoX + "," + posicaoY + "," + altitude + ")");
            
            // Verifica se chegou em um novo planeta
            usarSensorPovoamento();
        } else {
            System.out.println("Nenhum portal próximo para atravessar.");
        }
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
            } else {
                System.out.println("Este planeta já possui um nome: " + planetaAtual.getNome());
            }
        } else {
            System.out.println("Nenhum planeta disponível para nomear. Use o sensor de povoamento primeiro.");
        }
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