public class SensorIrrigacao extends Sensor {

    public SensorIrrigacao(double raio) {
        super(raio);
    }

    @Override

    // Método para monitorar o ambiente em busca de plantinhas prontas para Irrigacao
    public void monitorar(Robo robo) {
        System.out.println("🌿 " + robo.getNome() + " ativou o Sensor de Irrigacao.");

        // Obtendo o ambiente e a posição do robo
        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        boolean plantinhaPronta = false;        

        // Verificar se a posição que estamos verificando está dentro dos limites do mapa
        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst instanceof Plantinha && Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                Plantinha plantinha = (Plantinha) obst;
                if (plantinha.getCrescimento() < 100) {
                    System.out.println("🌿 Plantinha " + plantinha.getTipo() + " pronta para irrigacao nas coordenadas (" + obst.x1 + ", " + obst.y1 + ", " + obst.z1 + ")!");
                    plantinhaPronta = true;
                }
            }
        }       

        if (!plantinhaPronta) {
            System.out.println("Nenhuma plantinha pronta para irrigacao dentro do raio.");
        }
    }

    // Método para checar se há uma plantinha pronta para Irrigacao dentro do alcance do sensor
    public Plantinha checarIrrigacao(Robo robo) {

        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst instanceof Plantinha && Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                Plantinha plantinha = (Plantinha) obst;

                // Verifica se a plantinha está pronta para Irrigacao
                if (plantinha.getCrescimento() < 100) {

                    // Retorna a plantinha pronta para Irrigacao
                    return plantinha;
                }
            }
        }
        return null;
    }
}


