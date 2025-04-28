package mc322.simulator;

import mc322.simulator.robos.Robo;

public class SensorColheita extends Sensor {

    public SensorColheita(double raio) {
        super(raio);
    }

    @Override

    // Método para monitorar o ambiente em busca de plantinhas prontas para colheita
    public void monitorar(Robo robo) {
        System.out.println("🌿 " + robo.getNome() + " ativou o Sensor de Colheita.");

        // Obtendo o ambiente e a posição do robo
        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        boolean plantinhaPronta = false;        

        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst instanceof Plantinha && sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                Plantinha plantinha = (Plantinha) obst;
                if (plantinha.podeColher()) {
                    System.out.println("🌿 Plantinha " + plantinha.getTipo() + " pronta para colheita nas coordenadas (" + obst.x1 + ", " + obst.y1 + ", " + obst.z1 + ")!");
                    plantinhaPronta = true;
                }
            }
        }        

        if (!plantinhaPronta) {
            System.out.println("Nenhuma plantinha pronta para colheita dentro do raio.");
        }
    }

    // Método para checar se há uma plantinha pronta para colheita dentro do alcance do sensor
    public Plantinha checarColheita(Robo robo) {

        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        boolean plantinhaPronta = false;

        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst instanceof Plantinha && sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                Plantinha plantinha = (Plantinha) obst;

                // Verifica se a plantinha está pronta para colheita
                if (plantinha.podeColher()) {

                    // Retorna a plantinha pronta para colheita
                    return plantinha;
                }
            }
        }
        return null;
    }
}


