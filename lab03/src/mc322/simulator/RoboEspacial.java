public class RoboEspacial extends RoboAereo {

    // Atributo para armazenar a quantidade de planetas descobertos
    private int qtdePlanetasDescobertos;

    // Construtor da classe RoboEspacial
    public RoboEspacial(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima, altitude, ambiente);
        this.qtdePlanetasDescobertos = 0;
        
        // Adicionar sensores ao RoboEspacial
        this.adicionarSensor(new SensorPortal(3.0));
        this.adicionarSensor(new SensorPovoamento(3.0));
    }

    // Método para atravessar um portal
    public void atravessarPortal(Portal portal){
        
        System.out.println("🌌 Atravessando portal...");

        // Atualiza o mapa que o robo deicou sua posição inicial
        ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "_");
        
        // Atualiza a posição do robo para a posição destino do portal
        setPosicaoX(portal.getDestinoX());
        setPosicaoY(portal.getDestinoY());
        setAltitude(portal.getDestinoZ());

        // Atualiza o mapa com a nova posição do robo
        ambiente.atualizarMapa(portal.getDestinoX(), portal.getDestinoY(), portal.getDestinoZ(), "🤖");
        System.out.println("🌌 Portal atravessado com sucesso!");
    }

    // Método para nomear um planeta
    public void nomearPlaneta(String nomePlaneta) {
        System.out.println("🌍 Planeta nomeado como: " + nomePlaneta);
        qtdePlanetasDescobertos++;
    }

    // Método para verificar a quantidade de planetas descobertos
    public int getQtdePlanetasDescobertos() {
        return qtdePlanetasDescobertos;
    }
}