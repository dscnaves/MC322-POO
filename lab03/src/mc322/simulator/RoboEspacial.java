public class RoboEspacial extends RoboAereo {

    // Atributo para armazenar a quantidade de planetas descobertos
    private int qtdePlanetasDescobertos;

    private SensorPortal sensorPortal = new SensorPortal(3.0);
    private SensorPovoamento sensorPovoamento = new SensorPovoamento(3.0);

    // Construtor da classe RoboEspacial
    public RoboEspacial(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima, altitude, ambiente);
        this.qtdePlanetasDescobertos = 0;
        
        // Adicionar sensores ao RoboEspacial
        this.adicionarSensor(sensorPortal);
        this.adicionarSensor(sensorPovoamento);
    }

    // Método para atravessar um portal
    public void atravessarPortal(){

        boolean atravessou = false;

        // Obtém portal pelo sensor de portal
        Portal portal = sensorPortal.checkPortal(this);

        // Verifica se encontrou um portal
        if(portal != null){
        
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
        if(!atravessou){
            System.out.println("Nenhum portal para atravessar encontrado.");
        }
    }

    // Método para nomear um planeta
    public void nomearPlaneta() {

        // Obtém planeta pelo sensor de portal
        Planeta planeta = sensorPovoamento.checkPovo(this);

        // Verifica se encontrou um planeta não povoado
        if(planeta != null){
            planeta.setNome("Planeta" + qtdePlanetasDescobertos);
            System.out.println("🌍 Planeta nomeado como: Planeta" + qtdePlanetasDescobertos);
            qtdePlanetasDescobertos++;
        } else {
            System.out.println("Nenhum planeta nomeado! Tente se aproximar de um planeta não povoado!");
        }
    }

    // Método para verificar a quantidade de planetas descobertos
    public int getQtdePlanetasDescobertos() {
        return qtdePlanetasDescobertos;
    }
}